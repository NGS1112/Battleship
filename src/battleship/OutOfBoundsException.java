package battleship;

/**
 * Exception thrown if index goes out of bounds
 *
 * @author Nicholas Shinn
 */
public class OutOfBoundsException extends BattleshipException {

    /** Message to display when exception throws */
    public static final String PAST_EDGE = "Coordinates Extend Past Edge";

    /**
     * Exception thrown when index out of bounds
     *
     * @param row row of out of bounds cell
     *
     * @param column column of out of bounds cell
     */
    public OutOfBoundsException(int row, int column){
        super(row, column, PAST_EDGE);
    }
}
