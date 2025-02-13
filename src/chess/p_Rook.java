package chess;

public class p_Rook extends p_Piece {
    // Constructor for the Rook piece
    public p_Rook(Position position, Chess.Player player) {
        // Call the superclass constructor with the appropriate piece type based on the player
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WR : ReturnPiece.PieceType.BR, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        Position currentPosition = getPosition();

        // Check if the move is horizontal or vertical
        if (currentPosition.getFile() != toPosition.getFile() && currentPosition.getRank() != toPosition.getRank()) {
            return false; // Rook can only move in straight lines
        }

        // Determine the direction of movement for both file and rank
        int fileDirection = Integer.compare(toPosition.getFile(), currentPosition.getFile());
        int rankDirection = Integer.compare(toPosition.getRank(), currentPosition.getRank());
        Position nextPosition = new Position(
            currentPosition.getFile() + fileDirection,
            currentPosition.getRank() + rankDirection
        );

        // Check each square along the path to the destination
        while (!nextPosition.equals(toPosition)) {
            if (board.getPieceAt(nextPosition) != null) {
                return false; // There is a piece in the way
            }
            // Move to the next square in the direction of the destination
            nextPosition = new Position(
                nextPosition.getFile() + fileDirection,
                nextPosition.getRank() + rankDirection
            );
        }

        // Check if the destination square is empty or has an opponent's piece
        p_Piece piece = board.getPieceAt(toPosition);
        return piece == null || piece.getPlayer() != getPlayer(); // Valid if empty or opponent's piece
    }
}