package chess;

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
		/* FILL IN THIS METHOD */
		ReturnPlay result = new ReturnPlay();

        // Read the move input
        String[] moveParts = move.split(" ");
        if(moveParts.length != 2) {
            result.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return result;
        }

        String from = moveParts[0];
        String to = moveParts[1];

        // Make the move
        boolean moveSuccess = board.movePiece(from, to);
        if (!moveSuccess) {
            result.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return result;
        }

        // TODO, Implement Move Processing, Temporary solution
        result.piecesOnBoard = board.getPiecesOnBoard();
        result.message = null;

		return result;
	}
	
	
	//This method should reset the game, and start from scratch.
	public static void start() {
		/* FILL IN THIS METHOD */
        // Reset Board and Player Turn
        board = new Board();
        currentTurn = Player.white;
        PlayChess.printBoard(board.getPiecesOnBoard());
	}
}

