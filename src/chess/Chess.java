package chess;

import java.util.ArrayList;

/*
 * TODO: A list of everything left to implement for a presentable project
 * Castling
 * En passant
 * Promotion
 * Checkmate Detection
 * Draw Detection
 * 
 */


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

        // Handle the input processing and moving of the piece
        result = handleInput(move);

        // If an error occurred, return the error message
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
    
    // This method should reset the game, and start from scratch.
    public static void start() {
        // Reset Board and Player Turn
        board = new Board();
        currentTurn = Player.white;
        PlayChess.printBoard(board.getReturnPieces());
    }

    // This method should handle the input processing and moving of the piece.
    private static ReturnPlay handleInput(String move) {
        ReturnPlay result = new ReturnPlay();
        
        // Clean and split the move input
        move = cleanInput(move);

        // Test for Resignation
        if (move.equals("resign")) {
            result.message = (currentTurn == Player.white) ? ReturnPlay.Message.RESIGN_BLACK_WINS : ReturnPlay.Message.RESIGN_WHITE_WINS;
            return result;
        }

        
        // Split the move into parts
        String[] moveParts = move.split(" ");
        
        // Test for Draw "from to draw"
        if (move.toLowerCase().contains("draw") && moveParts.length == 3) {
            result.message = ReturnPlay.Message.DRAW;
        }

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

        return result;
    }

    //  Get the current pieces on the board.
    public ArrayList<ReturnPiece> getReturnPieces() {
        return board.getReturnPieces();
    }

    // Get the current player's turn.
    public Chess.Player getCurrentTurn() {
        return currentTurn;
    }

    // Clean the input string by trimming and converting to lower case.
    private static String cleanInput(String input) {
        input = input.trim().toLowerCase();

        // Remove all extra spaces
        input = input.replaceAll("\\s+", " ");

        return input;
    }
}