package chess;

public abstract class Piece {
    public Chess.Player owner;
    public Position position;   

    public Piece(Chess.Player owner, Position position) {
        this.owner = owner;
        this.position = position;
    }

    public abstract boolean isValidMove(Position from, Position to, Board board);
}
