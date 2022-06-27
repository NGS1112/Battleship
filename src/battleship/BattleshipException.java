package battleship;

/**
 * Handles exceptions specific to Battleship program
 *
 * @author Nicholas Shinn
 */
public class BattleshipException extends Exception {

    /** Value to be assigned to Unset dimensions */
    public static final int UNSET = -1;

    // TODO Create public integer fields row and column.
    // Make them so they cannot be changed, and non-static.
    /** Rows for exception */
    public int ROW;

    /** Column for exception */
    public int COLUMN;

    // TODO Complete this constructor so that the row and column
    // are stored in the exception instance.
    //

    /**
     * Constructor for the battleship exception
     *
     * @param row rows where exception occurs
     *
     * @param column column where exception occurs
     *
     * @param message exception message to display
     */
    public BattleshipException( int row, int column, String message ) {
        super( message + ", row=" + row + ", column=" + column );
        ROW = row;
        COLUMN = column;
    }

    // TODO Add a second (overloading) constructor that only takes a
    // message string. It should pass the string up to its superclass
    // and set row and column to UNSET.

    /**
     * Constructor for battleship exception when a row and column aren't provided
     *
     * @param message exception message to display
     */
    public BattleshipException(String message){
        super( message + ", row=" + UNSET + ", column=" + UNSET );
        ROW = UNSET;
        COLUMN = UNSET;
    }
}
