package chess;

public class King extends Piece  {
    
    public King(Chess.Player owner, Position position, Board board) {
        super(owner, position, owner == Chess.Player.white ? ReturnPiece.PieceType.WK : ReturnPiece.PieceType.BK, board);
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
