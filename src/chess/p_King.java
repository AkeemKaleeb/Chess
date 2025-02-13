package chess;

public class p_King extends p_Piece {
    // Constructor for the King piece
    public p_King(Position position, Chess.Player player) {
        // Call the superclass constructor with the appropriate piece type based on the player
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WK : ReturnPiece.PieceType.BK, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        // Get the current position of the King
        Position currentPosition = getPosition();

        // Calculate the difference in file and rank between the current position and the target position
        int fileDiff = Math.abs(toPosition.getFile() - currentPosition.getFile());
        int rankDiff = Math.abs(toPosition.getRank() - currentPosition.getRank());

        // Check if the move is one square in any direction
        if (fileDiff > 1 || rankDiff > 1) {
            return false; // Move is invalid if it is more than one square away
        }

        // Get the piece at the destination position
        p_Piece piece = board.getPieceAt(toPosition);

        // Check if the destination square is empty or has an opponent's piece
        return piece == null || piece.getPlayer() != getPlayer();
    }
}