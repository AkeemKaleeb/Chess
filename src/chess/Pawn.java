package chess;

public class Pawn extends Piece {
    private boolean isFirstMove = true;

    public Pawn(Chess.Player owner, Position position) {
        super(owner, position);
    }

    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        if (isFirstMove) {
            if (from.getFile() == to.getFile() && from.getRank() == to.getRank() + (owner.equals(Chess.Player.black) ? -2 : 2)) {
                isFirstMove = false;
                return true;
            }
            if (from.getFile() == to.getFile() && from.getRank() == to.getRank() + (owner.equals(Chess.Player.black) ? -1 : 1)) {
                isFirstMove = false;
                return true;
            }
        }
        else if (from.getFile() == to.getFile() && from.getRank() == to.getRank() + (owner.equals(Chess.Player.black) ? -1 : 1)) {
            return true;
        }

        return false;
    }
    
}
