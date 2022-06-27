package battleship;

/**
 * Exception to be thrown if a cell has been already played
 *
 * @author Nicholas Shinn
 */
public class CellPlayedException extends BattleshipException {

    /** Displays if cell has been hit */
    public static final String ALREADY_HIT = "Cell Already Played";

    /**
     * Exception thrown if cell is played
     *
     * @param row Row of cell
     *
     * @param column Column of cell
     */
    public CellPlayedException(int row, int column){
        super(row, column, ALREADY_HIT);
    }
}
