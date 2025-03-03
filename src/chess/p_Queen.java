package chess;

public class p_Queen extends p_Piece {
    // Constructor for the Queen piece
    public p_Queen(Position position, Chess.Player player) {
        // Call the superclass constructor with the appropriate piece type based on the player
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WQ : ReturnPiece.PieceType.BQ, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        Position currentPosition = getPosition();

        // Check Board Boundaries
        if (!toPosition.isValid()) {
            return false;
        }

        // Check if the move is diagonal, horizontal, or vertical
        boolean isDiagonal = Math.abs(toPosition.getFile() - currentPosition.getFile()) == Math.abs(toPosition.getRank() - currentPosition.getRank());
        boolean isStraight = currentPosition.getFile() == toPosition.getFile() || currentPosition.getRank() == toPosition.getRank();
        if (!isDiagonal && !isStraight) {
            return false; // Move is not valid if it's neither diagonal nor straight
        }

        // Determine the direction of the move
        int fileDirection = Integer.compare(toPosition.getFile(), currentPosition.getFile());
        int rankDirection = Integer.compare(toPosition.getRank(), currentPosition.getRank());
        Position nextPosition = new Position(
            currentPosition.getFile() + fileDirection,
            currentPosition.getRank() + rankDirection
        );

        // Check if there are any pieces in the way
        while (!nextPosition.equals(toPosition)) {
            // Check Board Boundaries
            if (!nextPosition.isValid()) {
                return false;
            }
            
            if (board.getPieceAt(nextPosition) != null) {
                return false; // Move is not valid if there is a piece in the way
            }
            nextPosition = new Position(
                nextPosition.getFile() + fileDirection,
                nextPosition.getRank() + rankDirection
            );
        }

        // Check if the destination square is empty or has an opponent's piece
        p_Piece piece = board.getPieceAt(toPosition);
        return piece == null || piece.getPlayer() != getPlayer(); // Move is valid if the destination is empty or has an opponent's piece
    }
}