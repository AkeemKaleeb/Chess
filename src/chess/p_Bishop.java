package chess;

public class p_Bishop extends p_Piece {
    // Constructor for the Bishop piece
    public p_Bishop(Position position, Chess.Player player) {
        // Call the superclass constructor with the appropriate piece type based on the player
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WB : ReturnPiece.PieceType.BB, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        Position currentPosition = getPosition();

        // Check if the move is diagonal by comparing the absolute difference in file and rank
        if (Math.abs(toPosition.getFile() - currentPosition.getFile()) != Math.abs(toPosition.getRank() - currentPosition.getRank())) {
            return false;
        }

        // Determine the direction of the move in both file and rank
        int fileDirection = toPosition.getFile() > currentPosition.getFile() ? 1 : -1;
        int rankDirection = toPosition.getRank() > currentPosition.getRank() ? 1 : -1;
        Position nextPosition = new Position(
            currentPosition.getFile() + fileDirection,
            currentPosition.getRank() + rankDirection
        );

        // Check each position along the diagonal path to ensure no pieces are in the way
        while (!nextPosition.equals(toPosition)) {
            if (board.getPieceAt(nextPosition) != null) {
                return false; // A piece is blocking the path
            }
            nextPosition = new Position(
                nextPosition.getFile() + fileDirection,
                nextPosition.getRank() + rankDirection
            );
        }

        // Check if the destination square is empty or occupied by an opponent's piece
        p_Piece piece = board.getPieceAt(toPosition);
        return piece == null || piece.getPlayer() != getPlayer();
    }
}
