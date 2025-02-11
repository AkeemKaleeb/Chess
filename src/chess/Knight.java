package chess;

public class Knight extends Piece {
    
    public Knight(Chess.Player owner, Position position) {
        super(owner, position);
    }

    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        // Check that the move is L-shaped
        if (Math.abs(from.getFile() - to.getFile()) == 2 && Math.abs(from.getRank() - to.getRank()) == 1) {
            return true;
        }
        else if (Math.abs(from.getFile() - to.getFile()) == 1 && Math.abs(from.getRank() - to.getRank()) == 2) {
            return true;
        }
        return false;
    }
}
