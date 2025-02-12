package chess;

public class p_Rook extends p_Piece {
    public p_Rook(ReturnPiece.PieceFile file, int rank, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WR : ReturnPiece.PieceType.BR, file, rank, player);
    }

    @Override
    public boolean isValidMove(ReturnPiece.PieceFile toFile, int toRank, Board board) {
        // Check if the move is horizontal or vertical
        if (toFile != file && toRank != rank) {
            return false;
        }

        // Check if there are any pieces in the way
        int fileDirection = toFile.ordinal() > file.ordinal() ? 1 : toFile.ordinal() < file.ordinal() ? -1 : 0;
        int rankDirection = toRank > rank ? 1 : toRank < rank ? -1 : 0;
        ReturnPiece.PieceFile currentFile = file;
        int currentRank = rank;
        while (currentFile != toFile || currentRank != toRank) {
            if (currentFile != toFile) {
                currentFile = ReturnPiece.PieceFile.values()[currentFile.ordinal() + fileDirection];
            }
            if (currentRank != toRank) {
                currentRank += rankDirection;
            }
            if (board.getPieceAt(currentFile, currentRank) != null) {
                return false;
            }
        }

        // Check if the destination square is empty or has an opponent's piece
        p_Piece piece = board.getPieceAt(toFile, toRank);
        return piece == null || piece.getPlayer() != player;
    }
}
