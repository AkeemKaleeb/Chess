package chess;

import java.util.ArrayList;

public class Chess {

    enum Player { white, black }
    
    private static Player currentTurn;
    private static Board board;

    /**
     * Plays the next move for whichever player has the turn.
     * 
     * @param move String for next move, e.g. "a2 a3"
     * 
     * @return A ReturnPlay instance that contains the result of the move.
     *         See the section "The Chess class" in the assignment description for details of
     *         the contents of the returned ReturnPlay instance.
     */
    public static ReturnPlay play(String move) {
        ReturnPlay result = new ReturnPlay();
        result.piecesOnBoard = board.getReturnPieces();

        // Clean and split the move input
        move = cleanInput(move);
        String[] moveParts = move.split(" ");

        // Handle inputs outside of possibilities "from to" or "from to promotion"
        if(moveParts.length != 2 && moveParts.length != 3) {
            result.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return result;
        }

        // Parse the positions from the input
        Position from = Position.fromString(moveParts[0]);
        Position to = Position.fromString(moveParts[1]);

        // Make the move, if an error occurs, prevent change of turn
        result = board.movePiece(from, to);
        if (result.message != null) {
            result.piecesOnBoard = board.getReturnPieces();
            return result;
        }

        // Update the turn to the other player
        currentTurn = (currentTurn == Chess.Player.white) ? Chess.Player.black : Chess.Player.white;

        // Prepare the result with updated board state
        result.piecesOnBoard = board.getReturnPieces();

        return result;
    }
    
    /**
     * This method should reset the game, and start from scratch.
     */
    public static void start() {
        // Reset Board and Player Turn
        board = new Board();
        currentTurn = Player.white;
        PlayChess.printBoard(board.getReturnPieces());
    }

    /**
     * Get the current pieces on the board.
     * 
     * @return ArrayList of ReturnPiece representing the current board state.
     */
    public ArrayList<ReturnPiece> getReturnPieces() {
        return board.getReturnPieces();
    }

    /**
     * Get the current player's turn.
     * 
     * @return The current player's turn.
     */
    public Chess.Player getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Clean the input string by trimming and converting to lower case.
     * 
     * @param input The input string to clean.
     * @return The cleaned input string.
     */
    private static String cleanInput(String input) {
        input = input.trim().toLowerCase();

        // Remove all extra spaces
        input = input.replaceAll("\\s+", " ");

        return input;
    }

    /**
     * Clear the console screen. This method is for debugging purposes only.
     */
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}