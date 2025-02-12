package chess;

public class p_Queen extends p_Piece {
    public p_Queen(ReturnPiece.PieceFile file, int rank, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WQ : ReturnPiece.PieceType.BQ, file, rank, player);
    }

    @Override
    public boolean isValidMove(ReturnPiece.PieceFile toFile, int toRank, Board board) {
        // Check if the move is horizontal, vertical, or diagonal
        int fileDifference = Math.abs(toFile.ordinal() - file.ordinal());
        int rankDifference = Math.abs(toRank - rank);
        if (fileDifference != 0 && rankDifference != 0 && fileDifference != rankDifference) {
            return false;
        }

        // Check if there are any pieces in the way
        int fileDirection = toFile.ordinal() > file.ordinal() ? 1 : toFile.ordinal() < file.ordinal() ? -1 : 0;
        int rankDirection = toRank > rank ? 1 : toRank < rank ? -1 : 0;
        ReturnPiece.PieceFile currentFile = file;
        int currentRank = rank;
        while (currentFile != toFile || currentRank != toRank) {
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