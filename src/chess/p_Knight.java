package chess;

public class p_Knight extends p_Piece {
    public p_Knight(Position position, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WN : ReturnPiece.PieceType.BN, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        // Check if the move is L-shaped
        int fileDifference = Math.abs(toPosition.getFile() - position.getFile());
        int rankDifference = Math.abs(toPosition.getRank() - position.getRank());
        return (fileDifference == 1 && rankDifference == 2) || (fileDifference == 2 && rankDifference == 1);
    }
}
