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
        move = sanitizeMove(move);
        if (move == null) {
            System.out.println("Invalid move format. Please use the format 'a# a#'.");
            return createReturnPlay(null, ReturnPlay.Message.ILLEGAL_MOVE);
        }

        Position from = new Position(move.charAt(0) - 'a', Character.getNumericValue(move.charAt(1)) - 1);
        Position to = new Position(move.charAt(3) - 'a', Character.getNumericValue(move.charAt(4)) - 1);
        Piece piece = board.getPieceAt(from);

        if (piece != null) {
            switch (piece.getClass().getSimpleName()) {
                case "Pawn":
                    if (piece.isValidMove(from, to)) {
                        // Handle pawn move
                        System.out.println("Pawn move");
                        board.movePiece(from, to);
                        return createReturnPlay(board.getReturnPieces(), null);
                    }
                    break;
                case "Rook":
                    if (piece.isValidMove(from, to)) {
                        // Handle rook move
                        System.out.println("Rook move");
                        board.movePiece(from, to);
                        return createReturnPlay(board.getReturnPieces(), null);
                    }
                    break;
                case "Knight":
                    if (piece.isValidMove(from, to)) {
                        // Handle knight move
                        System.out.println("Knight move");
                        board.movePiece(from, to);
                        return createReturnPlay(board.getReturnPieces(), null);
                    }
                    break;
                case "Bishop":
                    if (piece.isValidMove(from, to)) {
                        // Handle bishop move
                        System.out.println("Bishop move");
                        board.movePiece(from, to);
                        return createReturnPlay(board.getReturnPieces(), null);
                    }
                    break;
                case "Queen":
                    if (piece.isValidMove(from, to)) {
                        // Handle queen move
                        System.out.println("Queen move");
                        board.movePiece(from, to);
                        return createReturnPlay(board.getReturnPieces(), null);
                    }
                    break;
                case "King":
                    if (piece.isValidMove(from, to)) {
                        // Handle king move
                        System.out.println("King move");
                        board.movePiece(from, to);
                        return createReturnPlay(board.getReturnPieces(), null);
                    }
                    break;
                default:
                    // Handle unknown piece type
                    System.out.println("Unknown piece type");
                    break;
            }
        }
        return createReturnPlay(null, ReturnPlay.Message.ILLEGAL_MOVE);
    }
	
	
	// This method should reset the game, and start from scratch.
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

	private static ReturnPlay createReturnPlay(ArrayList<ReturnPiece> pieces, ReturnPlay.Message message) {
		ReturnPlay returnPlay = new ReturnPlay();
		returnPlay.piecesOnBoard = pieces;
		returnPlay.message = message;
		return returnPlay;
	}
}