package chess;

public class p_Bishop extends p_Piece {
    public p_Bishop(ReturnPiece.PieceFile file, int rank, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WB : ReturnPiece.PieceType.BB, file, rank, player);
    }

    @Override
    public boolean isValidMove(ReturnPiece.PieceFile toFile, int toRank, Board board) {
        // Check if the move is diagonal
        if (Math.abs(toFile.ordinal() - file.ordinal()) != Math.abs(toRank - rank)) {
            return false;
        }

        // Check if there are any pieces in the way
        int fileDirection = toFile.ordinal() > file.ordinal() ? 1 : -1;
        int rankDirection = toRank > rank ? 1 : -1;
        ReturnPiece.PieceFile currentFile = file;
        int currentRank = rank;
        while (currentFile != toFile && currentRank != toRank) {
            currentFile = ReturnPiece.PieceFile.values()[currentFile.ordinal() + fileDirection];
            currentRank += rankDirection;
            if (board.getPieceAt(currentFile, currentRank) != null) {
                return false;
            }
        }

        // Check if the destination square is empty or has an opponent's piece
        p_Piece piece = board.getPieceAt(toFile, toRank);
        return piece == null || piece.getPlayer() != player;
    }
}
