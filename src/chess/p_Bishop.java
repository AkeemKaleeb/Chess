package chess;

public class p_Bishop extends p_Piece {
    public p_Bishop(Position position, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WB : ReturnPiece.PieceType.BB, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        Position currentPosition = getPosition();

        // Check if the move is diagonal
        if (Math.abs(toPosition.getFile() - currentPosition.getFile()) != Math.abs(toPosition.getRank() - currentPosition.getRank())) {
            return false;
        }

        // Check if there are any pieces in the way
        int fileDirection = toPosition.getFile() > currentPosition.getFile() ? 1 : -1;
        int rankDirection = toPosition.getRank() > currentPosition.getRank() ? 1 : -1;
        Position nextPosition = currentPosition;
        while (!nextPosition.equals(toPosition)) {
            nextPosition = new Position(
                nextPosition.getFile() + fileDirection,
                nextPosition.getRank() + rankDirection
            );
            if (board.getPieceAt(nextPosition) != null) {
                return false;
            }
        }

        // Check if the destination square is empty or has an opponent's piece
        p_Piece piece = board.getPieceAt(toPosition);
        return piece == null || piece.getPlayer() != getPlayer();
    }
}
