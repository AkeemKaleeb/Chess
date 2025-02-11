package chess;

public class p_Knight extends p_Piece {
    public p_Knight(ReturnPiece.PieceFile file, int rank, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WP : ReturnPiece.PieceType.BP, file, rank, player);
    }

    @Override
    public boolean isValidMove(ReturnPiece.PieceFile toFile, int toRank, Board board) {
        // Implement pawn movement logic here
        return true;
    }
}
