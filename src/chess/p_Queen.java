package chess;

public class p_Queen extends p_Piece {
    public p_Queen(Position position, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WQ : ReturnPiece.PieceType.BQ, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        Position fromPosition = this.getPosition();
        
        // Check if the move is horizontal, vertical, or diagonal
        int fileDifference = Math.abs(toPosition.getFile() - fromPosition.getFile());
        int rankDifference = Math.abs(toPosition.getRank() - fromPosition.getRank());
        if (fileDifference != 0 && rankDifference != 0 && fileDifference != rankDifference) {
            return false;
        }

        // Check if there are any pieces in the way
        int fileDirection = Integer.compare(toPosition.getFile(), fromPosition.getFile());
        int rankDirection = Integer.compare(toPosition.getRank(), fromPosition.getRank());
        Position currentPosition = fromPosition;
        while (!currentPosition.equals(toPosition)) {
            currentPosition = new Position(
                currentPosition.getFile() + fileDirection,
                currentPosition.getRank() + rankDirection
            );
            if (!currentPosition.equals(toPosition) && board.getPieceAt(currentPosition) != null) {
                return false;
            }
        }

        // Check if the destination square is empty or has an opponent's piece
        p_Piece piece = board.getPieceAt(toPosition);
        return piece == null || piece.getPlayer() != player;
    }
}