package chess;

public class Bishop extends Piece {
    


    private Board board;

    public Bishop(Chess.Player owner, Position position, Board board) {
        super(owner, position, owner == Chess.Player.white ? ReturnPiece.PieceType.WB : ReturnPiece.PieceType.BB, board);
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        // Check that the move is diagonal
        if (Math.abs(from.getFile() - to.getFile()) == Math.abs(from.getRank() - to.getRank())) {
            // Check that there are no pieces in the way
            int fileDirection = to.getFile() > from.getFile() ? 1 : -1;
            int rankDirection = to.getRank() > from.getRank() ? 1 : -1;
            for (int i = 1; i < Math.abs(from.getFile() - to.getFile()); i++) {
                if (board.getPieceAt(new Position(from.getFile() + i * fileDirection, from.getRank() + i * rankDirection)) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
}
