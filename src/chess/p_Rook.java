package chess;

public class p_Rook extends p_Piece {
    public p_Rook(Position position, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WR : ReturnPiece.PieceType.BR, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        Position currentPosition = getPosition();

        // Check if the move is horizontal or vertical
        if (currentPosition.getFile() != toPosition.getFile() && currentPosition.getRank() != toPosition.getRank()) {
            return false;
        }

        // Check if there are any pieces in the way
        int fileDirection = Integer.compare(toPosition.getFile(), currentPosition.getFile());
        int rankDirection = Integer.compare(toPosition.getRank(), currentPosition.getRank());
        Position nextPosition = new Position(
            currentPosition.getFile() + fileDirection,
            currentPosition.getRank() + rankDirection
        );

        while (!nextPosition.equals(toPosition)) {
            if (board.getPieceAt(nextPosition) != null) {
                return false;
            }
            nextPosition = new Position(
                nextPosition.getFile() + fileDirection,
                nextPosition.getRank() + rankDirection
            );
        }

        // Check if the destination square is empty or has an opponent's piece
        p_Piece piece = board.getPieceAt(toPosition);
        return piece == null || piece.getPlayer() != getPlayer();
    }
}