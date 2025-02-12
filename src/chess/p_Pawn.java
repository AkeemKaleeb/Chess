package chess;

public class p_Pawn extends p_Piece {
    private boolean hasMoved = false;

    public p_Pawn(ReturnPiece.PieceFile file, int rank, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WP : ReturnPiece.PieceType.BP, file, rank, player);
    }

    @Override
    public boolean isValidMove(ReturnPiece.PieceFile toFile, int toRank, Board board) {
        // Check if the move is one square forward
        if (toFile != file) {
            return false;
        }

        int rankDifference = player.equals(Chess.Player.white) ? toRank - rank : rank - toRank;
        if (rankDifference == 1) {
            hasMoved = true;
            return board.getPieceAt(toFile, toRank) == null;
        } else if (rankDifference == 2 && !hasMoved) {
            hasMoved = true;
            return board.getPieceAt(toFile, toRank) == null && board.getPieceAt(toFile, player.equals(Chess.Player.white) ? 4 : 5) == null;
        }

        return false;
    }
}