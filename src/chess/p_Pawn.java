package chess;

public class p_Pawn extends p_Piece {
    private boolean hasMoved = false;

    public p_Pawn(Position position, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WP : ReturnPiece.PieceType.BP, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        int fileDifference = Math.abs(toPosition.getFile() - this.position.getFile());
        int rankDifference = player.equals(Chess.Player.white) ? toPosition.getRank() - position.getRank() : position.getRank() - toPosition.getRank();

        // Check for normal move
        if (fileDifference == 0) {
            if (rankDifference == 1) {
                hasMoved = true;
                return board.getPieceAt(toPosition) == null;
            } else if (rankDifference == 2 && !hasMoved) {
                int intermediateRank = player.equals(Chess.Player.white) ? position.getRank() + 1 : position.getRank() - 1;
                Position intermediatePosition = new Position(position.getFile(), intermediateRank);
                hasMoved = true;
                return board.getPieceAt(toPosition) == null && board.getPieceAt(intermediatePosition) == null;
            }
        }

        // Check for capturing move
        if (fileDifference == 1 && rankDifference == 1) {
            hasMoved = true;
            return board.getPieceAt(toPosition) != null && board.getPieceAt(toPosition).getPlayer() != this.player;
        }

        // Check for en passant (optional, if your game supports it)
        // Add en passant logic here if needed

        return false;
    }
}