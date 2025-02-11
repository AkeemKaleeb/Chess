package chess;

public class Bishop extends Piece {
    
    public Bishop(Chess.Player owner, Position position, ReturnPiece.PieceType pieceType) {
        super(owner, position, pieceType);
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        // Check that the move is diagonal
        if (Math.abs(from.getFile() - to.getFile()) == Math.abs(from.getRank() - to.getRank())) {
            // Check that there are no pieces in the way
            int fileDirection = to.getFile() > from.getFile() ? 1 : -1;
            int rankDirection = to.getRank() > from.getRank() ? 1 : -1;
            for (int i = 1; i < Math.abs(from.getFile() - to.getFile()); i++) {
                if (Board.getPieceAt(new Position(from.getFile() + i * fileDirection, from.getRank() + i * rankDirection)) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
}
