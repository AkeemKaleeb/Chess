package chess;

public class Pawn extends Piece {
    private boolean isFirstMove = true;

    public Pawn(Chess.Player owner, Position position, Board board) {
        super(owner, position, owner == Chess.Player.white ? ReturnPiece.PieceType.WP : ReturnPiece.PieceType.BP, board);
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        // Check that there are no pieces in the way
        if (from.getFile() == to.getFile()) {
            if (board.getPieceAt(to) != null) {
                return false;
            }
        }

        // Check for the first move of the pawn
        // Forward 2
        if (from.getFile() == to.getFile() && from.getRank() == to.getRank() + (getOwner().equals(Chess.Player.black) ? -2 : 2)
            && board.getPieceAt(to) == null
            && board.getPieceAt(new Position(from.getFile(), from.getRank() + (getOwner().equals(Chess.Player.black) ? -1 : 1))) == null
            && isFirstMove) {
            isFirstMove = false;
            return true;
        }
        // Forward 1
        if (from.getFile() == to.getFile() && from.getRank() == to.getRank() + (getOwner().equals(Chess.Player.black) ? -1 : 1)
                && board.getPieceAt(to) == null) {
            isFirstMove = false;
            return true;
        }
        // Capture
        if (Math.abs(from.getFile() - to.getFile()) == 1 && from.getRank() == to.getRank() + (getOwner().equals(Chess.Player.black) ? -1 : 1)
                && board.getPieceAt(to) != null) {
            isFirstMove = false;
            return true;
        }

        // TODO: En Passant 

        return false;
    }
    
}
