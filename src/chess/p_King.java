package chess;

public class p_King extends p_Piece {
    public p_King(Position position, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WK : ReturnPiece.PieceType.BK, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        Position currentPosition = getPosition();

        // Check if the move is one square in any direction
        int fileDiff = Math.abs(toPosition.getFile() - currentPosition.getFile());
        int rankDiff = Math.abs(toPosition.getRank() - currentPosition.getRank());
        if (fileDiff > 1 || rankDiff > 1) {
            return false;
        }

        // Check if the destination square is empty or has an opponent's piece
        p_Piece piece = board.getPieceAt(toPosition);
        return piece == null || piece.getPlayer() != getPlayer();
    }
}