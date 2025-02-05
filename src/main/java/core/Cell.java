// Raihan and Jonah
package core;


/**
 *Creates object which are used to fill grid
 * Represents a single cell in the Battleship grid.
 * A cell can be empty or contain a ship and can be marked as shot.
 * @author mdhamimraihan
 */


public class Cell {
    private boolean shot;
    private boolean ship;
    /**
     ** Creates an empty cell with no ship and not shot.
     */


    public Cell() {
        this.shot = false;
        this.ship = false;
    }

    /**
     * Creates a cell with the specified ship presence.
     *  @param ship {@code true} if the cell contains a ship, {@code false} otherwise.
     * ref #94   https://github.com/skiadas/battleship/issues/94#issue-2833525080
     * @author mdhamimraihan
     */


    public Cell(boolean ship) {
        this.ship = ship;
        this.shot = false;
    }

    public boolean isEmpty() {
        return !this.hasShip();
    }

    /**
     * Checks if the cell has been shot.
     *
     * @return {@code true} if the cell has been shot, {@code false} otherwise.
     * * ref #94   https://github.com/skiadas/battleship/issues/94#issue-2833525080
     * @author mdhamimraihan
     */
    public boolean hasBeenShot() {
        return this.shot;
    }

    // the cell contains a ship
    public boolean hasShip() {
        return this.ship;
    }

    /**
     * Determines if the shot on this cell resulted in a hit.
     *
     * @return {@code true} if the cell has been shot and contains a ship, {@code false} otherwise.
     * ref #94   https://github.com/skiadas/battleship/issues/94#issue-2833525080
     *@author mdhamimraihan
     */
    public boolean cellIsHit() {
        return this.hasBeenShot() && this.hasShip();
    }

    // shot on this cell resulted in a miss
    public boolean cellIsMiss() {
        return this.hasBeenShot() && !this.hasShip();
    }


    /** Mark the cell as shot

    * ref #94   https://github.com/skiadas/battleship/issues/94#issue-2833525080
            *@author mdhamimraihan
     */
    public void setAsShot() {
        this.shot = true;
    }

    // Place a ship in the cell
    public void setAsShip() {
        this.ship = true;
    }

    public void setAsHit() {
        this.ship = true;
        this.shot = true;
    }

    public void setAsMiss() {
        this.shot = true;
        this.ship = false;
    }

    // Reset the cell to its initial state
    public void reset() {
        this.shot = false;
        this.ship = false;
    }
}
