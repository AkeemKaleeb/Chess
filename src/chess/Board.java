package chess;

import java.util.ArrayList;

public class Board {

    private ArrayList<Piece> pieces;

    public Board() {
        pieces = new ArrayList<>();
        initializePieces();
    }

    private void initializePieces() {
        // Initialize pieces and add them to the pieces list
        pieces.add(new Pawn(Chess.Player.white, new Position('a', 2), this));
        pieces.add(new Pawn(Chess.Player.white, new Position('b', 2), this));
        pieces.add(new Pawn(Chess.Player.white, new Position('c', 2), this));
        pieces.add(new Pawn(Chess.Player.white, new Position('d', 2), this));
        pieces.add(new Pawn(Chess.Player.white, new Position('e', 2), this));
        pieces.add(new Pawn(Chess.Player.white, new Position('f', 2), this));
        pieces.add(new Pawn(Chess.Player.white, new Position('g', 2), this));
        pieces.add(new Pawn(Chess.Player.white, new Position('h', 2), this));
        pieces.add(new Rook(Chess.Player.white, new Position('a', 1), this));
        pieces.add(new Rook(Chess.Player.white, new Position('h', 1), this));
        pieces.add(new Knight(Chess.Player.white, new Position('b', 1), this));
        pieces.add(new Knight(Chess.Player.white, new Position('g', 1), this));
        pieces.add(new Bishop(Chess.Player.white, new Position('c', 1), this));
        pieces.add(new Bishop(Chess.Player.white, new Position('f', 1), this));
        pieces.add(new Queen(Chess.Player.white, new Position('d', 1), this));
        pieces.add(new King(Chess.Player.white, new Position('e', 1), this));

        pieces.add(new Pawn(Chess.Player.black, new Position('a', 7), this));
        pieces.add(new Pawn(Chess.Player.black, new Position('b', 7), this));
        pieces.add(new Pawn(Chess.Player.black, new Position('c', 7), this));
        pieces.add(new Pawn(Chess.Player.black, new Position('d', 7), this));
        pieces.add(new Pawn(Chess.Player.black, new Position('e', 7), this));
        pieces.add(new Pawn(Chess.Player.black, new Position('f', 7), this));
        pieces.add(new Pawn(Chess.Player.black, new Position('g', 7), this));
        pieces.add(new Pawn(Chess.Player.black, new Position('h', 7), this));
        pieces.add(new Rook(Chess.Player.black, new Position('a', 8), this));
        pieces.add(new Rook(Chess.Player.black, new Position('h', 8), this));
        pieces.add(new Knight(Chess.Player.black, new Position('b', 8), this));
        pieces.add(new Knight(Chess.Player.black, new Position('g', 8), this));
        pieces.add(new Bishop(Chess.Player.black, new Position('c', 8), this));
        pieces.add(new Bishop(Chess.Player.black, new Position('f', 8), this));
        pieces.add(new Queen(Chess.Player.black, new Position('d', 8), this));
        pieces.add(new King(Chess.Player.black, new Position('e', 8), this));
    }

    public ArrayList<ReturnPiece> getReturnPieces() {
        ArrayList<ReturnPiece> returnPieces = new ArrayList<>();
        for (Piece piece : pieces) {
            ReturnPiece returnPiece = new ReturnPiece();
            returnPiece.pieceType = piece.getPieceType();
            returnPiece.pieceFile = ReturnPiece.PieceFile.values()[piece.getPosition().getFile() - 'a'];
            returnPiece.pieceRank = piece.getPosition().getRank();
            returnPieces.add(returnPiece);
            System.out.println(returnPiece.toString());
        }
        return returnPieces;
    }

    public Piece getPieceAt(Position position) {
        for (Piece piece : pieces) {
            if (piece.getPosition().getFile() == position.getFile() && piece.getPosition().getRank() == position.getRank()) {
                return piece;
            }
        }
        return null;
    }

    public void movePiece(Position from, Position to) {
        Piece piece = getPieceAt(from);
        if (piece != null) {
            piece.setPosition(to);
        }
    }
}