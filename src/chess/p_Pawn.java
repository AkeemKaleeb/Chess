package chess;

public class p_Pawn extends p_Piece {
    // Constructor for the Pawn piece
    public p_Pawn(Position position, Chess.Player player) {
        // Call the superclass constructor with the appropriate piece type based on the player
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WP : ReturnPiece.PieceType.BP, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        Position currentPosition = getPosition();
        int fileDiff = toPosition.getFile() - currentPosition.getFile();
        int rankDiff = toPosition.getRank() - currentPosition.getRank();

        // Check if the move is forward for white player
        if (getPlayer() == Chess.Player.white) {
            // Single step forward
            if (fileDiff == 0 && rankDiff == 1 && board.getPieceAt(toPosition) == null) {
                return true;
            }
            // Double step forward from the initial position
            if (fileDiff == 0 && rankDiff == 2 && currentPosition.getRank() == 1 && board.getPieceAt(toPosition) == null) {
                return true;
            }
            // Capture move (diagonal)
            if (Math.abs(fileDiff) == 1 && rankDiff == 1 && board.getPieceAt(toPosition) != null && board.getPieceAt(toPosition).getPlayer() != getPlayer()) {
                return true;
            }
        } else {
            // Check if the move is forward for black player
            // Single step forward
            if (fileDiff == 0 && rankDiff == -1 && board.getPieceAt(toPosition) == null) {
                return true;
            }
            // Double step forward from the initial position
            if (fileDiff == 0 && rankDiff == -2 && currentPosition.getRank() == 6 && board.getPieceAt(toPosition) == null) {
                return true;
            }
            // Capture move (diagonal)
            if (Math.abs(fileDiff) == 1 && rankDiff == -1 && board.getPieceAt(toPosition) != null && board.getPieceAt(toPosition).getPlayer() != getPlayer()) {
                return true;
            }
        }

        // If none of the valid moves are satisfied, return false
        return false;
    }
}