package chess;

public abstract class Piece {
    public Chess.Player owner;
    public Position position;   
    public ReturnPiece.PieceType pieceType;

    public Piece(Chess.Player owner, Position position, ReturnPiece.PieceType pieceType) {
        this.owner = owner;
        this.position = position;
        this.pieceType = pieceType;
    }

    public abstract boolean isValidMove(Position from, Position to);
}
