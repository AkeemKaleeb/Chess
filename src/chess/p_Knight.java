package chess;

public class p_Knight extends p_Piece {
    public p_Knight(Position position, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WN : ReturnPiece.PieceType.BN, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        Position currentPosition = getPosition();

        // Check if the move is an L-shape
        int fileDiff = Math.abs(toPosition.getFile() - currentPosition.getFile());
        int rankDiff = Math.abs(toPosition.getRank() - currentPosition.getRank());
        if (!((fileDiff == 2 && rankDiff == 1) || (fileDiff == 1 && rankDiff == 2))) {
            return false;
        }

        // Check if the destination square is empty or has an opponent's piece
        p_Piece piece = board.getPieceAt(toPosition);
        return piece == null || piece.getPlayer() != getPlayer();
    }
}