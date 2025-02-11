package chess;

public abstract class Piece {
    public Chess.Player owner;
    public Position position;   
    public ReturnPiece.PieceType pieceType;
    public Board board;

    public Piece(Chess.Player owner, Position position, ReturnPiece.PieceType pieceType, Board board) {
        this.owner = owner;
        this.position = position;
        this.pieceType = pieceType;
        this.board = board;
    }

    public abstract boolean isValidMove(Position from, Position to);
}
