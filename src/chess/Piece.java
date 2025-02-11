package chess;

public abstract class Piece {
    private Chess.Player owner;
    private Position position;   
    private ReturnPiece.PieceType pieceType;
    public Board board;

    public Piece(Chess.Player owner, Position position, ReturnPiece.PieceType pieceType, Board board) {
        this.owner = owner;
        this.position = position;
        this.pieceType = pieceType;
        this.board = board;
    }

    public abstract boolean isValidMove(Position from, Position to);

    public Chess.Player getOwner() {
        return owner;
    }
    public void setOwner(Chess.Player owner) {
        this.owner = owner;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public ReturnPiece.PieceType getPieceType() {
        return pieceType;
    }
    public void setPieceType(ReturnPiece.PieceType pieceType) {
        this.pieceType = pieceType;
    }
}
