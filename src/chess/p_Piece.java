package chess;

public abstract class p_Piece {
    protected ReturnPiece.PieceType type;
    protected Position position;
    protected Position lastPosition;
    protected Chess.Player player;
    protected boolean isAlive;

    // Constructor
    public p_Piece(ReturnPiece.PieceType type, Position position, Chess.Player player) {
        this.type = type;
        this.position = position;
        this.lastPosition = position;
        this.player = player;
        this.isAlive = true;
    }

    // Abstract method to check if a move is valid
    public abstract boolean isValidMove(Position position, Board board);

    // Getters and Setters
    public ReturnPiece.PieceType getType() {
        return type;
    }

    public Chess.Player getPlayer() {
        return player;
    }
    
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        // Set last position
        this.lastPosition = this.position;
        
        // Set new position
        this.position = position;
    }
}
