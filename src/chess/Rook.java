package chess;

public class Rook extends Piece {
    
    public Rook(Chess.Player owner, Position position, Board board) {
        super(owner, position, owner == Chess.Player.white ? ReturnPiece.PieceType.WR : ReturnPiece.PieceType.BR, board);
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        // Check that the move is either horizontal or vertical
        if (from.getFile() == to.getFile() ^ from.getRank() == to.getRank()) {
            // Check that there are no pieces in the way
            if (from.getFile() == to.getFile()) {
                int direction = to.getRank() > from.getRank() ? 1 : -1;
                for (int i = 1; i < Math.abs(from.getRank() - to.getRank()); i++) {
                    if (board.getPieceAt(new Position(from.getFile(), from.getRank() + i * direction)) != null) {
                        return false;
                    }
                }
            }
            else {
                int direction = to.getFile() > from.getFile() ? 1 : -1;
                for (int i = 1; i < Math.abs(from.getFile() - to.getFile()); i++) {
                    if (board.getPieceAt(new Position(from.getFile() + i * direction, from.getRank())) != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
}
