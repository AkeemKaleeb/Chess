package chess;

public class King extends Piece  {
    
    public King(Chess.Player owner, Position position, ReturnPiece.PieceType pieceType) {
        super(owner, position, pieceType);
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        // Check that the move is only one square in any direction
        if (Math.abs(from.getFile() - to.getFile()) <= 1 && Math.abs(from.getRank() - to.getRank()) <= 1) {
            return true;
        }
        return false;
    }
}
