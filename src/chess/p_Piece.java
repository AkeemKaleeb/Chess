package chess;

public abstract class p_Piece {
    protected ReturnPiece.PieceType type;
    protected ReturnPiece.PieceFile file;
    protected int rank;
    protected Chess.Player player;

    // Constructor
    public p_Piece(ReturnPiece.PieceType type, ReturnPiece.PieceFile file, int rank, Chess.Player player) {
        this.type = type;
        this.file = file;
        this.rank = rank;
        this.player = player;
    }

    // Abstract method to check if a move is valid
    public abstract boolean isValidMove(ReturnPiece.PieceFile toFile, int toRank, Board board);

    // Getters and Setters
    public ReturnPiece.PieceType getType() {
        return type;
    }

    public ReturnPiece.PieceFile getFile() {
        return file;
    }

    public int getrank() {
        return rank;
    }

    public Chess.Player getPlayer() {
        return player;
    }

    public void setPosition(ReturnPiece.PieceFile file, int rank) {
        this.file = file;
        this.rank = rank;
    }
}
