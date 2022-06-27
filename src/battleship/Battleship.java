package battleship;

import java.io.*;

/**
 * Main class for the Battleship game
 *
 * @author Nicholas Shinn
 */
public class Battleship {
    /** Declares when all ships have been sunk and the game is over*/
    public static final String ALL_SHIPS_SUNK = "All Ships Have Been Sunk";

    /** Declares when the wrong number of arguments has been provided*/
    public static final String BAD_ARG_COUNT = "Wrong Number of Arguments Provided";

    /** Declares when a setup file could not be found */
    public static final String MISSING_SETUP_FILE = "No Setup File Found";

    /** Sets the maximum allowed dimensions for board setup */
    public static final int MAX_DIM = 20;

    /** Declares when dimensions exceed MAX_DIM */
    public static final String DIM_TOO_BIG = "Dimensions Beyond Acceptable Limit";

    /** Declares when any error occurs while reading a config file */
    public static final String BAD_CONFIG_FILE = "Unreadable Board Text File";

		public static final String SAVED_PATH = "savedGames";

		public static final String DATA_PATH = "data";

    /** Prompts the user for input */
    public static final String PROMPT = "> ";

    /** Standard white spacte */
    public static final String WHITESPACE = "\\s+";

    /** The current game board */
    public static Board GAME;

    /**
     * Takes user input and decides how to handle it for the base game
     *
     * @param command user input
     *
     * @throws OutOfBoundsException declares when a given command calls an index that is out of bounds
     *
     * @throws CellPlayedException declares when a command is called on a cell that has already been played
     */
    private static void inputCommand(String command) throws OutOfBoundsException, CellPlayedException {
        String[] argument = command.split(WHITESPACE);
				switch(argument.length){
					case 2:
            GAME.getCell(Integer.parseInt(argument[0]), Integer.parseInt(argument[1])).hit();
						GAME.display(System.out);
						break;
					case 1:
						if ( argument[0].equals("!") ) {
                GAME.fullDisplay(System.out);
            } else if ( argument[0].equals("q") || argument[0].equals("quit")) {
								System.exit(0);
						} else {
								File saves = new File(SAVED_PATH);
								saves.mkdir();
								System.out.print("Saving game...");
								try{FileOutputStream outputFile = new FileOutputStream(SAVED_PATH + "/" + argument[0]);
                	ObjectOutputStream out = new ObjectOutputStream(outputFile);
                	out.writeObject(GAME);
                	out.close();
                	outputFile.close();
            		} catch(IOException x){
									System.out.println("Failed to save game!");
                	System.exit(1);
            		}
								System.out.println("Game saved to " + SAVED_PATH + " as " + argument[0] + "!");
								System.exit(0);
						}
						break;
					default:
						System.out.println(BAD_ARG_COUNT);
				}
    }

    /**
     * Main method for playing the Battleship game. Takes a config file and uses it to create the game
     *
     * @param args input file
     *
     * @throws IOException Any error involving input
     *
     * @throws OverlapException thrown when two ships overlap on a cell
     *
     * @throws OutOfBoundsException thrown when an index exceeds the bounds of the board
     *
     * @throws CellPlayedException thrown when a cell has already been played
     */
    public static void main(String[] args) throws IOException, OverlapException, OutOfBoundsException, CellPlayedException{
        if(args.length==1){
            try{
                System.out.print("Checking if " + args[0] + " is a saved game file...");
                FileInputStream inputFile = new FileInputStream(SAVED_PATH + "/" + args[0]);
                ObjectInputStream in = new ObjectInputStream(inputFile);
                Board game = (Board) in.readObject();
                in.close();
                inputFile.close();
                System.out.println("yes; loading file.");
                GAME = game;
            } catch (ClassNotFoundException|FileNotFoundException|StreamCorruptedException x){
                try {
                    System.out.println("no; Will read as setup file.");
                    BufferedReader set = new BufferedReader(new FileReader( DATA_PATH + "/" + args[0]));
                    String dim = set.readLine();
                    String[] dim2 = dim.split(WHITESPACE);
                    if(Integer.parseInt(dim2[0])>MAX_DIM||Integer.parseInt(dim2[1])>MAX_DIM){
                        System.out.println(DIM_TOO_BIG);
                        System.exit(1);
                    }
                    Board game = new Board(Integer.parseInt(dim2[0]), Integer.parseInt(dim2[1]));
                    String create = set.readLine();
                    while(create!=null) {
                        String[] create2 = create.split(WHITESPACE);
                        new Ship(game, Integer.parseInt(create2[0]), Integer.parseInt(create2[1]), Ship.Orientation.valueOf(create2[2]), Integer.parseInt(create2[3]));
                        create = set.readLine();
                    }
                    set.close();
                    GAME = game;
                } catch (FileNotFoundException a){
                    System.out.println(MISSING_SETUP_FILE);
                    System.exit(1);
                }
            }
            GAME.display(System.out);
            BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(PROMPT);
            String userInput = userReader.readLine();
            while(!userInput.equals("q") || !userInput.equals("quit")){
                inputCommand(userInput);

                if(GAME.allSunk()){
                    System.out.println(ALL_SHIPS_SUNK);
                    System.exit(0);
                }
                System.out.print(PROMPT);
                userInput = userReader.readLine();
            }
            userReader.close();
        }else { System.out.println(BAD_ARG_COUNT); }
    }

}
