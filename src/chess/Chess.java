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

        // Read the move input
        String[] moveParts = move.split(" ");
        if(moveParts.length != 2) {
            result.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return result;
        }

        Position from = Position.fromString(moveParts[0]);
        Position to = Position.fromString(moveParts[1]);

        // Make the move
        boolean moveSuccess = board.movePiece(from, to);
        if (!moveSuccess) {
            result.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return result;
        }

        // Update the turn
        currentTurn = (currentTurn == Chess.Player.white) ? Chess.Player.black : Chess.Player.white;

        // Prepare the result
        result.piecesOnBoard = board.getReturnPieces();
        result.message = null;

        //clearConsole();
        return result;
    }
    
    
    //This method should reset the game, and start from scratch.
    public static void start() {
        // Reset Board and Player Turn
        board = new Board();
        currentTurn = Player.white;
        //clearConsole();
        PlayChess.printBoard(board.getReturnPieces());
    }

    public ArrayList<ReturnPiece> getReturnPieces() {
        return board.getReturnPieces();
    }

    public Chess.Player getCurrentTurn() {
        return currentTurn;
    }

    // TODO: Remove the following methods, for Debugging purposes only
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