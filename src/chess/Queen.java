package chess;

public class Queen extends Piece {
    
    public Queen(Chess.Player owner, Position position) {
        super(owner, position);
    }

    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        // Check that the move is either horizontal, vertical, or diagonal
        if (from.getFile() == to.getFile() || from.getRank() == to.getRank() || Math.abs(from.getFile() - to.getFile()) == Math.abs(from.getRank() - to.getRank())) {
            // Check that there are no pieces in the way
            if (from.getFile() == to.getFile()) {
                int direction = to.getRank() > from.getRank() ? 1 : -1;
                for (int i = 1; i < Math.abs(from.getRank() - to.getRank()); i++) {
                    if (board.getPieceAt(new Position(from.getFile(), from.getRank() + i * direction)) != null) {
                        return false;
                    }
                }
            }
            else if (from.getRank() == to.getRank()) {
                int direction = to.getFile() > from.getFile() ? 1 : -1;
                for (int i = 1; i < Math.abs(from.getFile() - to.getFile()); i++) {
                    if (board.getPieceAt(new Position(from.getFile() + i * direction, from.getRank())) != null) {
                        return false;
                    }
                }
            }
            else {
                int fileDirection = to.getFile() > from.getFile() ? 1 : -1;
                int rankDirection = to.getRank() > from.getRank() ? 1 : -1;
                for (int i = 1; i < Math.abs(from.getFile() - to.getFile()); i++) {
                    if (board.getPieceAt(new Position(from.getFile() + i * fileDirection, from.getRank() + i * rankDirection)) != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
}
