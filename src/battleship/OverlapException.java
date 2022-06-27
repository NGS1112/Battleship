package battleship;

/**
 * Exception for when two ships overlap
 *
 * @author Nicholas Shinn
 */
public class OverlapException extends BattleshipException {

    /** Message to display when exception throws */
    public static final String OVERLAP = "Coordinates Overlap";

    /**
     * Exception to throw for overlapping ships
     *
     * @param row row of overlap
     *
     * @param column row of column
     */
    public OverlapException(int row, int column){
        super(row, column, OVERLAP);
    }
}
