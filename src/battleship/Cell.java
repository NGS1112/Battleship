package battleship;

import java.io.Serializable;

/**
 * A single spot on the battleship.Battleship game board.
 * A cell knows if there is a ship on it, and it remember
 * if it has been hit.
 *
 * @author Nicholas Shinn
 */
public class Cell implements Serializable {

    /** Character to display for a ship that has been entirely sunk */
    public static final char SUNK_SHIP_SECTION = '*';

    /** Character to display for a ship that has been hit but not sunk */
    public static final char HIT_SHIP_SECTION = '☐';

    /** Character to display for a water cell that has been hit */
    public static final char HIT_WATER = '.';

    /**
     * Character to display for a water cell that has not been hit.
     * This character is also used for an unhit ship segment.
     */
    public static final char PRISTINE_WATER = '_';

    /**
     * Character to display for a ship section that has not been
     * sunk, when revealing the hidden locations of ships
     */
    public static final char HIDDEN_SHIP_SECTION = 'S';

    /** Cell row */
    public int ROW;

    /** Cell column */
    public int COLUMN;

    /** Current status of cell */
    public char STATUS;

    /** Checks if their is a ship on the cell or not */
    public boolean IS_SHIP;

    /** Current ship on the cell */
    public Ship CURRENT;

    /**
     * Cell object that combines with other cells to make a board
     *
     * @param row cell row
     *
     * @param column cell column
     */
    public Cell(int row, int column){
        this.ROW = row;
        this.COLUMN = column;
        this.STATUS = PRISTINE_WATER;
        this.IS_SHIP = false;
    }


    /**
     * Place a ship on this cell. Of course, ships typically cover
     * more than one Cell, so the same ship will usually be passed
     * to more than one Cell's putShip method.
     * @param ship the ship that is to be on this Cell
     * @throws OverlapException if there is already a ship here.
     */
    // TODO putShip GOES HERE
    public void putShip(Ship ship) throws OverlapException{
        if(!IS_SHIP){
            this.CURRENT = ship;
            IS_SHIP = true;
        }
        else{
            throw new OverlapException(ROW, COLUMN);
        }
    }

    /**
     * Changes a cell status to hit
     *
     * @throws CellPlayedException thrown if a cell has already been played
     */
    public void hit() throws CellPlayedException{
        if(STATUS==PRISTINE_WATER){
            if(!IS_SHIP){ STATUS=HIT_WATER; }
            else{
                STATUS = HIT_SHIP_SECTION;
                CURRENT.hit();
                if(CURRENT.isSunk()){
                    for (int i = 0; i <= CURRENT.CELLS.size()-1; i++) {
                        Cell c = CURRENT.CELLS.get(i);
                        c.STATUS = SUNK_SHIP_SECTION;
                    }
                }
            }
        }
        else{ throw new CellPlayedException(ROW, COLUMN);}
    }

    /**
     * Checks the current status of the cell and returns it
     *
     * @return current status of cell
     */
    public char displayHitStatus(){
        return STATUS;
    }

    /**
     * Checks the current status of the cell and returns it even if it's a hidden ship
     *
     * @return current status of cell even if it's hidden
     */
    public char displayChar(){
        if(STATUS==PRISTINE_WATER&&IS_SHIP){ return HIDDEN_SHIP_SECTION; }
        else{ return STATUS; }
    }

}
