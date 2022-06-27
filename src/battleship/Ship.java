package battleship;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A single ship in a battleship.Battleship game
 *
 * @author Nicholas Shinn
 */
public class Ship implements Serializable {

    /** Message displayed when ship sinks */
    public static final String SUNK_MESSAGE = "A battleship has been sunk!";

    /** Length of ship */
    public int LENGTH;

    /** Amount of hits the ship has taken */
    public int HITS;

    public ArrayList<Cell> CELLS = new ArrayList<>();


    /**
     * Orientation is a property of a ship.
     * The names of the enum values were chosen because they
     * are descriptive and match the words put in the configuration
     * files.
     *
     * @see Orientation#valueOf(String)
     */
    public enum Orientation {
        HORIZONTAL( 0, 1 ), VERTICAL( 1, 0 );

        /**
         * Use it to loop through all of the cell locations a ship
         * is on, based on the upper left end of the ship.
         */
        public int rDelta, cDelta;

        /**
         * Associate a direction vector with the orientation.
         * @param rDelta how much the row number changes in this direction
         * @param cDelta how much the column number changes
         *               in this direction
         */
        Orientation( int rDelta, int cDelta ) {
            this.rDelta = rDelta;
            this.cDelta = cDelta;
        }
    }

    /**
     * Initialize this new ship's state. Tell the Board object
     * and each involved Cell object about the existence of this
     * ship by trying to put the ship at each applicable Cell.
     * @param board holds a collection of ships
     * @param uRow the uppermost row that the ship is on
     * @param lCol the leftmost column that the ship is on
     * @param ort the ship's orientation
     * @param length how many cells the ship is on
     * @throws OverlapException if this ship would overlap another one
     *              that already exists
     * @throws OutOfBoundsException if this ship would extend beyond
     *              the board
     */
    // TODO Write your code here.
    public Ship(Board board, int uRow, int lCol, Ship.Orientation ort, int length) throws OverlapException, OutOfBoundsException{
        this.HITS = 0;
        this.LENGTH = length;
        if(ort==Orientation.HORIZONTAL){
            for (int i = 0; i < length; i++) {
                if (i + lCol <= board.HEIGHT) {
                    Cell c = board.getCell(uRow, lCol + i);
                    c.putShip(this);
                    this.CELLS.add(c);
                } else{ throw new OutOfBoundsException(uRow, lCol+i); }
            }
        }
        else{
            for (int i = 0; i < length; i++) {
                if (i + lCol <= board.HEIGHT) {
                    Cell c = board.getCell(uRow + i, lCol);
                    c.putShip(this);
                    this.CELLS.add(c);
                } else { throw new OutOfBoundsException(uRow + i, lCol); }
            }
        }
        board.addShip(this);
    }

    /**
     * Tells the ship and user it has been hit
     */
    public void hit(){
        HITS += 1;
        System.out.println("Ship Hit!");
    }

    /**
     * Checks if the ship has sunk
     *
     * @return true if sunk (when hits equal length of ship), false otherwise
     */
    public boolean isSunk(){
        return HITS==LENGTH;
    }

}
