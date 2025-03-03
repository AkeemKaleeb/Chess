// Kaileb Cole KJC265
// Maxime Deperrois MDD182

package chess;

import java.util.ArrayList;

public class Chess {

    public enum Player { white, black }
    
    private static Player currentTurn;
    private static Board board;
    private static boolean drawOffered;

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
        if (result.message == ReturnPlay.Message.ILLEGAL_MOVE) {
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
        board = Board.createBoard();
        currentTurn = Player.white;
        //PlayChess.printBoard(board.getReturnPieces());
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
        
        // Test for Draw "draw"
        if (moveParts.length == 1 && moveParts[0].equals("draw")) {
            if (drawOffered) {
                result.message = ReturnPlay.Message.DRAW;
                return result;
            } else {
                result.message = ReturnPlay.Message.ILLEGAL_MOVE;
                return result;
            }
        }

        // Handle inputs outside of possibilities "from to" or "from to promotion" or "from to draw"
        if(moveParts.length != 2 && moveParts.length != 3) {
            result.message = ReturnPlay.Message.ILLEGAL_MOVE;   
            return result;
        }

        // Parse the positions from the input
        Position from = Position.fromString(moveParts[0]);
        Position to = Position.fromString(moveParts[1]);

        // Find the piece at the 'from' position
        p_Piece piece = board.getPieceAt(from);

        // Test for the correct turn order
        if (piece == null || piece.getPlayer() != currentTurn) {
            result.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return result;
        }

        // Handle Three-Part Move
        if (moveParts.length == 3) {
            // If the move is valid, check for draw offer
            if (result.message != ReturnPlay.Message.ILLEGAL_MOVE && moveParts[2].equals("draw?")) {
                result.message = ReturnPlay.Message.DRAW;
                board.movePiece(from, to);
                return result;
            }

            // Promotions
            String promotionPiece = moveParts[2].trim().toUpperCase();
            if (promotionPiece.equals("Q") || promotionPiece.equals("R") || promotionPiece.equals("B") || promotionPiece.equals("N")) {
                result = board.movePiece(from, to, promotionPiece);
            } else {
                result.message = ReturnPlay.Message.ILLEGAL_MOVE;
                return result;
            }
        } 
        else {
            // Check if the move is a pawn reaching the last rank
            if (piece instanceof p_Pawn && (to.getRank() == 0 || to.getRank() == 7)) {
                result = board.movePiece(from, to, "Q"); // Default to Queen if no promotion piece is specified
            } else {
                // Make the move, if an error occurs, prevent change of turn
                result = board.movePiece(from, to);
            }
        }

        return result;
    }

    //  Get the current pieces on the board.
    public ArrayList<ReturnPiece> getReturnPieces() {
        return board.getReturnPieces();
    }

    // Get the current player's turn.
    public static Chess.Player getCurrentTurn() {
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