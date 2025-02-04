package core;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameDriver {
    private final Presenter presenter;
    private final Scanner scanner; // Use injected scanner

    public GameDriver(Presenter presenter, InputStream input) {
        this.presenter = presenter;
        this.scanner = new Scanner(input); // Inject Scanner from input stream
    }

    public void start() {
        presenter.displayMessage("Welcome to Battleship!");
        presenter.displayMessage("Choose an action: start, instructions, back, setup, or stop");

        boolean running = true;

        while (running) {
            if (!scanner.hasNextLine()) {
                presenter.displayMessage("No more input. Exiting...");
                break; // Avoid infinite loop in tests
            }

            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "start":
                    startGame();
                    break;
                case "instructions":
                    displayInstructions();
                    break;
                case "back":
                    goBackToMenu();
                    break;
                case "setup":
                    setupShips();
                    break;
                case "stop":
                    stopGame();
                    running = false;
                    break;
                default:
                    presenter.displayMessage("Invalid input. Please try again.");
            }
        }
    }

    private void startGame() {
        presenter.displayMessage("Game is starting...");
        Grid grid = Grid.defaultGrid(); // Temporary grid
        while (!grid.allShipsAreSunk()) {
            presenter.displayGrid(grid);
            presenter.displayMessage("Insert a coordinate to shoot!");
            Coord playerInputCoord = presenter.askForCoordinate(grid);
            grid.shoot(playerInputCoord);
            reportIfShipSunk(grid, playerInputCoord);
        }
    }

    private void reportIfShipSunk(Grid grid, Coord playerInputCoord) {
        for (Ship ship : grid.getShipList()) {
            for (Coord c : ship.getCoordList()) {
                if (c.isEqual(playerInputCoord) && grid.isShipSunk(ship)) {
                    presenter.displayMessage("You sunk your opponent’s " + ship.getName() + "!");
                }
            }
        }
    }

    private void displayInstructions() {
        presenter.displayMessage(
                "Instructions: Place your ships carefully to sink all enemy ships.");
    }

    private void goBackToMenu() {
        presenter.displayMessage("Returning to the main menu...");
        start();
    }

    private void stopGame() {
        presenter.displayMessage("Game is stopping...");
    }

    private void setupShips() {
        presenter.displayMessage("Setup your ships before the game begins");
    }
}
