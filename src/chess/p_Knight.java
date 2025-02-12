package chess;

public class p_Knight extends p_Piece {
    public p_Knight(ReturnPiece.PieceFile file, int rank, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WN : ReturnPiece.PieceType.BN, file, rank, player);
    }

    @Override
    public boolean isValidMove(ReturnPiece.PieceFile toFile, int toRank, Board board) {
        // Check if the move is L-shaped
        int fileDifference = Math.abs(toFile.ordinal() - file.ordinal());
        int rankDifference = Math.abs(toRank - rank);
        return (fileDifference == 1 && rankDifference == 2) || (fileDifference == 2 && rankDifference == 1);
    }
}
