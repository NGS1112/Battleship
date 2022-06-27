package battleship;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class to represent the grid of cells (squares).
 * A collection of ships is also kept so the Board
 * can be asked if the game is over.
 * The class is Serializable so that its instance can
 * be saved to a file in binary form using an
 * {@link java.io.ObjectOutputStream} and restored
 * with an {@link java.io.ObjectInputStream}.
 * Because the object holds references to all other
 * objects in the system, no other objects need to
 * be separately saved.
 *
 * @author Nicholas Shinn
 */
public class Board implements Serializable {

    public int HEIGHT;

    public int WIDTH;

    public ArrayList<Ship> SHIPS = new ArrayList<>();

    private Cell[][] cells;
    /** cells = Cell[length][width]*/

    public Board(int height, int width){
        HEIGHT = height;
        WIDTH = width;
        cells = new Cell[height][width];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                cells[x][y] = new Cell(x,y);
            }
        }
    }

    /**
     * Fetch the Cell object at the given location.
     * @param row row number (0-based)
     * @param column column number (0-based)
     * @return the Cell created for this position on the board
     * @throws OutOfBoundsException if either coordinate is negative or too high
     */
    // TODO getCell GOES HERE
    public Cell getCell(int row, int column) throws OutOfBoundsException{
        if(row>=0&&row<=HEIGHT&&column>=0&&column<=WIDTH) { return cells[row][column]; }
        else{ throw new OutOfBoundsException(row, column); }
    }


    /**
     * Add a ship to the board. The only current reason that the
     * board needs direct access to the ships is to poll them
     * to see if they are all sunk and the game is over.
     * @see Cell#putShip(Ship)
     * @param ship the as-yet un-added ship
     * @rit.pre This ship has already informed the Cells of the board
     *    that are involved.
     */
    // TODO addShip GOES HERE
    public void addShip(Ship ship){
        SHIPS.add(ship);
    }

    /**
     * Retrieves board height
     *
     * @return int representing height
     */
    public int getHeight(){
        return HEIGHT;
    }

    /**
     * Retrieves board width
     *
     * @return int representing width
     */
    public int getWidth(){
        return WIDTH;
    }

    /**
     * Displays the board, not revealing hidden ships
     *
     * @param out stream to output to
     *
     * @throws OutOfBoundsException thrown when index goes out of set bounds
     */
    public void display(PrintStream out) throws OutOfBoundsException{
        for (int x = 0; x < WIDTH; x++) {
            out.print("  " + x);
        }
        for (int y = 0; y < HEIGHT; y++) {
            out.println();
            out.print(y + " ");
            for (int z = 0; z < WIDTH; z++) {
                out.print(getCell(y,z).displayHitStatus() + "  ");
            }
        }
        System.out.println();
    }

    /**
     * Displays the board, revealing hidden ships
     *
     * @param out stream to output to
     *
     * @throws OutOfBoundsException thrown when index goes out of set bounds
     */
    public void fullDisplay(PrintStream out) throws OutOfBoundsException{
        for (int x = 0; x < WIDTH; x++) {
            out.print("  " + x);
        }
        for (int y = 0; y < HEIGHT; y++) {
            out.println();
            out.print(y + " ");
            for (int z = 0; z < WIDTH; z++) {
                out.print(getCell(y,z).displayChar() + "  ");
            }
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Checks if all ships have sunk and if the game is over
     *
     * @return true if sunk, false otherwise
     */
    public boolean allSunk(){
        int sunk_count = 0;
        for (int i = 0; i <= SHIPS.size()-1; i++) {
            Ship s = SHIPS.get(i);
            if(s.isSunk()){
                sunk_count += 1;
            }
        }
        return sunk_count == SHIPS.size();
    }

    /**
     * Displays board as a string
     *
     * @return string representing board
     */
    public String toString(){
        return ("Height= " + HEIGHT + ", Width= " + WIDTH);
    }

}
