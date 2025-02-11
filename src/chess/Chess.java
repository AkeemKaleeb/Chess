// Kaileb Cole (KJC265), Maxime Deperrois (MDD182)

package chess;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chess {

        enum Player { white, black }

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

		move = sanitizeMove(move);

		Position from = new Position(move.charAt(0), Character.getNumericValue(move.charAt(1)));
		Position to = new Position(move.charAt(3), Character.getNumericValue(move.charAt(4)));
		Piece piece = board.getPieceAt(from);
		if (piece != null) {
			switch (piece.getClass().getSimpleName()) {
				case "Pawn":
					if (piece.isValidMove(from, to)) {
						// Handle pawn move
						return new ReturnPlay();
					}
					break;
				case "Rook":
					if (piece.isValidMove(from, to)) {
						// Handle rook move
					}
					break;
				case "Knight":
					if (piece.isValidMove(from, to)) {
						// Handle knight move
					}
					break;
				case "Bishop":
					if (piece.isValidMove(from, to)) {
						// Handle bishop move
					}
					break;
				case "Queen":
					if (piece.isValidMove(from, to)) {
						// Handle queen move
					}
					break;
				case "King":
					if (piece.isValidMove(from, to)) {
						// Handle king move
					}
					break;
				default:
					// Handle unknown piece type
					break;
			}
		}
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		return null;
	}
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		/* FILL IN THIS METHOD */
		board = new Board();
		PlayChess.printBoard(board.getReturnPieces());
		
	}

	private static String sanitizeMove(String move) {
		move = move.trim().toLowerCase();
		move = move.replaceAll("\\s+", " ");

		// RegEx to match format
		Pattern pattern = Pattern.compile("^[a-h][1-8] [a-h][1-8]( [BNRQ])?$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(move);
		if (matcher.matches()) {
			// Ensure the optional third section remains in uppercase
			if (move.length() > 5) {
				return move.substring(0, 5) + move.substring(5).toUpperCase();
			}
			return move;
		} else {
			return null;
		}
	}
}