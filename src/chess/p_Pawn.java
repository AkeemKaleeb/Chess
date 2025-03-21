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

        // Check Board Boundaries
        if (!toPosition.isValid()) {
            return false;
        }

        int fileDiff = toPosition.getFile() - currentPosition.getFile();
        int rankDiff = toPosition.getRank() - currentPosition.getRank();

        // Check if the move is forward for white player
        int direction = (getPlayer() == Chess.Player.white) ? 1 : -1;

        // Single step forward
        if (fileDiff == 0 && rankDiff == direction && board.getPieceAt(toPosition) == null) {
            return true;
        }
        // Double step forward from the initial position
        if (fileDiff == 0 && rankDiff == 2 * direction 
                        && currentPosition.getRank() == (getPlayer() == Chess.Player.white ? 1 : 6) 
                        && board.getPieceAt(toPosition) == null
                        && board.getPieceAt(new Position(currentPosition.getFile(), currentPosition.getRank() + direction)) == null) {

            return true;
        }
        // Capture move (diagonal)
        if (Math.abs(fileDiff) == 1 && rankDiff == direction) {
            p_Piece targetPiece = board.getPieceAt(toPosition);
            if (targetPiece != null && targetPiece.getPlayer() != getPlayer()) {
                return true;
            }
        }
        // En Passant
        if (Math.abs(fileDiff) == 1 
                && rankDiff == direction 
                && board.getLastPiece() != null 
                && board.getLastPiece().getType() == (Chess.getCurrentTurn() == Chess.Player.white 
                    ? ReturnPiece.PieceType.BP 
                    : ReturnPiece.PieceType.WP) 
                && board.getLastMoveDistance() == 2) {

                
            Position lastPiecePosition = board.getLastPiece().getPosition();

            // Check that the last piece is adjacent in file and on the same rank we are leaving
            if(lastPiecePosition.getRank() == currentPosition.getRank() && lastPiecePosition.getFile() == toPosition.getFile()) {
                board.removePieceAt(board.getLastPiece().getPosition());
                return true;
            }
        }

        // If none of the valid moves are satisfied, return false
        return false;
    }
}