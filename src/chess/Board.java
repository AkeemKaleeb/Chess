package chess;

import java.util.ArrayList;

public class Board {
    private p_Piece[][] board;

    public Board() {
        board = new p_Piece[8][8];
        resetBoard();
    }

    public void resetBoard() {
        // Clear the board
        for (int file = 0; file < 8; file++) {
            for (int rank = 0; rank < 8; rank++) {
                board[file][rank] = null;
            }
        }

        // Initialize Pawns
        for (int file = 0; file < 8; file++) {
            board[file][1] = new p_Pawn(new Position(file, 1), Chess.Player.white);
            board[file][6] = new p_Pawn(new Position(file, 6), Chess.Player.black);
        }

        // Initialize Other Pieces
        setupBackRank(0, Chess.Player.white);
        setupBackRank(7, Chess.Player.black);
    }

    // Helper method to setup the back rank
    private void setupBackRank(int rank, Chess.Player player) {
        board[0][rank] = new p_Rook(new Position(0, rank), player);
        board[1][rank] = new p_Knight(new Position(1, rank), player);
        board[2][rank] = new p_Bishop(new Position(2, rank), player);
        board[3][rank] = new p_Queen(new Position(3, rank), player);
        board[4][rank] = new p_King(new Position(4, rank), player);
        board[5][rank] = new p_Bishop(new Position(5, rank), player);
        board[6][rank] = new p_Knight(new Position(6, rank), player);
        board[7][rank] = new p_Rook(new Position(7, rank), player);
    }

    public ArrayList<ReturnPiece> getReturnPieces() {
        ArrayList<ReturnPiece> returnPieces = new ArrayList<>();
        for (int file = 0; file < 8; file++) {
            for (int rank = 0; rank < 8; rank++) {
                p_Piece piece = board[file][rank];
                if (piece != null) {
                    ReturnPiece returnPiece = new ReturnPiece();
                    returnPiece.pieceType = piece.getType();
                    returnPiece.pieceFile = ReturnPiece.PieceFile.values()[piece.getPosition().getFile()];
                    returnPiece.pieceRank = piece.getPosition().getRank() + 1;
                    returnPieces.add(returnPiece);
                }
            }
        }
        return returnPieces;
    }

    // Move a piece from one location to another
    public boolean movePiece(Position from, Position to) {
        // Find piece on the board
        p_Piece piece = getPieceAt(from);
        if (piece == null) {
            return false;
        }

        // Check if the move is valid
        if (!piece.isValidMove(to, this)) {
            return false;
        }

        // Move the piece
        board[to.getFile()][to.getRank()] = piece;
        board[from.getFile()][from.getRank()] = null;
        piece.setPosition(to);
        return true;
    }

    public p_Piece getPieceAt(Position position) {
        return board[position.getFile()][position.getRank()];
    }
}