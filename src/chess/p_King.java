package chess;

public class p_King extends p_Piece {
    private boolean hasMoved = false;

    // Constructor for the King piece
    public p_King(Position position, Chess.Player player) {
        // Call the superclass constructor with the appropriate piece type based on the player
        super(player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WK : ReturnPiece.PieceType.BK, position, player);
    }

    @Override
    public boolean isValidMove(Position toPosition, Board board) {
        // Get the current position of the King
        Position currentPosition = getPosition();

        // Calculate the difference in file and rank between the current position and the target position
        int fileDiff = Math.abs(toPosition.getFile() - currentPosition.getFile());
        int rankDiff = Math.abs(toPosition.getRank() - currentPosition.getRank());

        // Check if the move is one square in any direction
        if (rankDiff > 1 || (fileDiff > 1 && hasMoved)) {
            return false; // Move is invalid if it is more than one square away
        }
        else if (fileDiff == 2 && !hasMoved && rankDiff == 0) {  // Handle Castling
            boolean direction = (toPosition.getFile() - currentPosition.getFile()) > 0;     // Gets the direction of castle, true = right, false = left
            // Test Right Castle
            if(direction) {
                // Check if the squares between the King and Rook are empty and doesn't put the king in check
                for(int i = currentPosition.getFile() + 1; i < 7; i++) {
                    if(board.getPieceAt(new Position(i, currentPosition.getRank())) != null) {
                        return false;
                    }

                    // Check if it puts king in check
                    if(board.movePiece(currentPosition, new Position(i, currentPosition.getRank())).message == ReturnPlay.Message.ILLEGAL_MOVE) {
                        board.movePiece(new Position(i, currentPosition.getRank()), currentPosition);
                        return false;
                    }
                }
                // Check if the Rook is in the correct position
                if(board.getPieceAt(new Position(7, currentPosition.getRank())) instanceof p_Rook rook) {
                    if(rook.hasMoved) {
                        return false;
                    }
                    else {
                        rook.setPosition(new Position(currentPosition.getFile() + 1, currentPosition.getRank()));
                        rook.hasMoved = true;
                    }
                }
                else {
                    return false;
                }
            }
            // Test Left Castle
            else {
                // Check if the squares between the King and Rook are empty and doesn't put the king in check
                for(int i = currentPosition.getFile() - 1; i > 0; i--) {
                    // Check if a piece is in the way
                    if(board.getPieceAt(new Position(i, currentPosition.getRank())) != null) {
                        return false;
                    }

                    // Check if it puts king in check, only test the first two positions to the left
                    if(i > 1 && board.movePiece(currentPosition, new Position(i, currentPosition.getRank())).message == ReturnPlay.Message.ILLEGAL_MOVE) {
                        board.movePiece(new Position(i, currentPosition.getRank()), currentPosition);
                        return false;
                    }
                }
                // Check if the Rook is in the correct position
                if(board.getPieceAt(new Position(0, currentPosition.getRank())) instanceof p_Rook rook) {
                    if(rook.hasMoved) {
                        return false;
                    }
                    else {
                        rook.setPosition(new Position(currentPosition.getFile() - 1, currentPosition.getRank()));
                        rook.hasMoved = true;
                    }
                }
                else {
                    return false;
                }
            }
        }
        // Get the piece at the destination position
        p_Piece piece = board.getPieceAt(toPosition);

        if(piece == null || piece.getPlayer() != getPlayer()) {
            hasMoved = true;
            return true;
        }

        return false;
    }
}