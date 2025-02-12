package chess;

public class p_Rook extends p_Piece {
    public p_Rook(Position position, Chess.Player player) {
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WR : ReturnPiece.PieceType.BR, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        Position fromPosition = this.getPosition();
        int fromFile = fromPosition.getFile();
        int fromRank = fromPosition.getRank();
        int toFile = toPosition.getFile();
        int toRank = toPosition.getRank();

        // Check if the move is horizontal or vertical
        if (fromFile != toFile && fromRank != toRank) {
            return false;
        }

        // Check if there are any pieces in the way
        int fileDirection = Integer.compare(toFile, fromFile);
        int rankDirection = Integer.compare(toRank, fromRank);
        int currentFile = fromFile;
        int currentRank = fromRank;
        while (currentFile != toFile || currentRank != toRank) {
            if (currentFile != toFile) {
                currentFile += fileDirection;
            }
            if (currentRank != toRank) {
                currentRank += rankDirection;
            }
            if (currentFile != toFile || currentRank != toRank) {
                Position currentPosition = new Position(currentFile, currentRank);
                if (board.getPieceAt(currentPosition) != null) {
                    return false;
                }
            }
        }

        // Check if the destination square is empty or has an opponent's piece
        p_Piece piece = board.getPieceAt(toPosition);
        return piece == null || piece.getPlayer() != player;
    }
}
