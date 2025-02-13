package chess;

public abstract class p_Piece {
    // Enum representing the type of the piece (e.g., King, Queen, etc.)
    protected ReturnPiece.PieceType type;
    
    // Current position of the piece on the board
    protected Position position;
    
    // Last position of the piece before the current move
    protected Position lastPosition;
    
    // Player to whom the piece belongs
    protected Chess.Player player;
    
    // Flag indicating if the piece is still in the game
    protected boolean isAlive;

    // Constructor to initialize the piece with its type, position, and player
    public p_Piece(ReturnPiece.PieceType type, Position position, Chess.Player player) {
        this.type = type;
        this.position = position;
        this.lastPosition = position;
        this.player = player;
        this.isAlive = true;
    }

    // Abstract method to check if a move is valid for the piece
    public abstract boolean isValidMove(Position position, Board board);

    // Getter for the type of the piece
    public ReturnPiece.PieceType getType() {
        return type;
    }

    // Getter for the player to whom the piece belongs
    public Chess.Player getPlayer() {
        return player;
    }
    
    // Getter for the current position of the piece
    public Position getPosition() {
        return position;
    }

    // Setter for the position of the piece
    public void setPosition(Position position) {
        // Set last position to the current position before updating
        this.lastPosition = this.position;
        
        // Update the current position to the new position
        this.position = position;
    }
}
