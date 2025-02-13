package chess;

public class p_Pawn extends p_Piece {
    public p_Pawn(Position position, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WP : ReturnPiece.PieceType.BP, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        Position currentPosition = getPosition();
        int fileDiff = toPosition.getFile() - currentPosition.getFile();
        int rankDiff = toPosition.getRank() - currentPosition.getRank();

        // Check if the move is forward
        if (getPlayer() == Chess.Player.white) {
            if (fileDiff == 0 && rankDiff == 1 && board.getPieceAt(toPosition) == null) {
                return true;
            }
            if (fileDiff == 0 && rankDiff == 2 && currentPosition.getRank() == 1 && board.getPieceAt(toPosition) == null) {
                return true;
            }
            if (Math.abs(fileDiff) == 1 && rankDiff == 1 && board.getPieceAt(toPosition) != null && board.getPieceAt(toPosition).getPlayer() != getPlayer()) {
                return true;
            }
        } else {
            if (fileDiff == 0 && rankDiff == -1 && board.getPieceAt(toPosition) == null) {
                return true;
            }
            if (fileDiff == 0 && rankDiff == -2 && currentPosition.getRank() == 6 && board.getPieceAt(toPosition) == null) {
                return true;
            }
            if (Math.abs(fileDiff) == 1 && rankDiff == -1 && board.getPieceAt(toPosition) != null && board.getPieceAt(toPosition).getPlayer() != getPlayer()) {
                return true;
            }
        }

        return false;
    }
}