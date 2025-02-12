package chess;

public class p_King extends p_Piece {
    public p_King(Position position, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WK : ReturnPiece.PieceType.BK, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        // Check if the move is one square in any direction
        int fileDifference = Math.abs(toPosition.getFile() - position.getFile());
        int rankDifference = Math.abs(toPosition.getRank() - position.getRank());
        return fileDifference <= 1 && rankDifference <= 1;
    }
}
