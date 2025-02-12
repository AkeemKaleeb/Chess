package chess;

public class p_King extends p_Piece {
    public p_King(ReturnPiece.PieceFile file, int rank, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WK : ReturnPiece.PieceType.BK, file, rank, player);
    }

    @Override
    public boolean isValidMove(ReturnPiece.PieceFile toFile, int toRank, Board board) {
        // Check if the move is one square in any direction
        int fileDifference = Math.abs(toFile.ordinal() - file.ordinal());
        int rankDifference = Math.abs(toRank - rank);
        return fileDifference <= 1 && rankDifference <= 1;
    }
}
