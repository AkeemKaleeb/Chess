package chess;

public class p_Knight extends p_Piece {
    // Constructor for the Knight piece, initializing its position and player
    public p_Knight(Position position, Chess.Player player) {
        // Call the superclass constructor with the appropriate piece type based on the player
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WN : ReturnPiece.PieceType.BN, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        // Get the current position of the Knight
        Position currentPosition = getPosition();

        // Calculate the difference in file and rank between the current position and the target position
        int fileDiff = Math.abs(toPosition.getFile() - currentPosition.getFile());
        int rankDiff = Math.abs(toPosition.getRank() - currentPosition.getRank());

        // Check if the move is an L-shape (2 squares in one direction and 1 square in the other)
        if (!((fileDiff == 2 && rankDiff == 1) || (fileDiff == 1 && rankDiff == 2))) {
            return false; // If not an L-shape, the move is invalid
        }

        // Get the piece at the destination position
        p_Piece piece = board.getPieceAt(toPosition);

        // Check if the destination square is empty or has an opponent's piece
        return piece == null || piece.getPlayer() != getPlayer();
    }
}