package chess;

import java.util.ArrayList;

public class Board {
    private ArrayList<p_Piece> pieces;
    
    public Board() {
        pieces = new ArrayList<>();
        resetBoard();
    }
    
    public void resetBoard() {
        pieces.clear();
        
        // Initialize Pawns
        for (ReturnPiece.PieceFile file : ReturnPiece.PieceFile.values()) {
            pieces.add(new p_Pawn(file, 2, Chess.Player.white));
            pieces.add(new p_Pawn(file, 7, Chess.Player.black));
        }
        
        // Initialize Other Pieces
        setupBackRank(1, Chess.Player.white);
        setupBackRank(8, Chess.Player.black);
    }
        
    // Helper method to setup the back rank
    private void setupBackRank(int rank, Chess.Player player) {
        ReturnPiece.PieceFile[] files = ReturnPiece.PieceFile.values();
        
        p_Piece[] order;
        if (player == Chess.Player.white) {
            order = new p_Piece[]{
                new p_Rook(files[0], rank, player),
                new p_Knight(files[1], rank, player),
                new p_Bishop(files[2], rank, player),
                new p_Queen(files[3], rank, player),
                new p_King(files[4], rank, player),
                new p_Bishop(files[5], rank, player),
                new p_Knight(files[6], rank, player),
                new p_Rook(files[7], rank, player)
            };
        } else {
            order = new p_Piece[]{
                new p_Rook(files[0], rank, player),
                new p_Knight(files[1], rank, player),
                new p_Bishop(files[2], rank, player),
                new p_Queen(files[3], rank, player),
                new p_King(files[4], rank, player),
                new p_Bishop(files[5], rank, player),
                new p_Knight(files[6], rank, player),
                new p_Rook(files[7], rank, player)
            };
        }
        
        for (p_Piece piece : order) {
            pieces.add(piece);
        }
    }

    // Return the pieces on the board
    public ArrayList<ReturnPiece> getReturnPieces() {
        ArrayList<ReturnPiece> returnPieces = new ArrayList<>();
        for (p_Piece piece : pieces) {
            ReturnPiece returnPiece = new ReturnPiece();
            returnPiece.pieceType = piece.getType();
            returnPiece.pieceFile = piece.getFile();
            returnPiece.pieceRank = piece.getrank();
            returnPieces.add(returnPiece);
        }
        return returnPieces;
    }
    
    // Move a piece from one location to another
    public boolean movePiece(String from, String to) {
        // Get From and To locations
        ReturnPiece.PieceFile fromFile = ReturnPiece.PieceFile.valueOf(from.substring(0, 1));
        int fromRank = Integer.parseInt(from.substring(1));

        ReturnPiece.PieceFile toFile = ReturnPiece.PieceFile.valueOf(to.substring(0, 1));
        int toRank = Integer.parseInt(to.substring(1));

        // Find piece on the board, if it exists, move it
        for (p_Piece piece : pieces) {
            if (piece.getFile() == fromFile && piece.getrank() == fromRank) {
                piece.setPosition(toFile, toRank);
                return true;
            }
        }
        return false;
    }

    public p_Piece getPieceAt(ReturnPiece.PieceFile file, int rank) {
        for (p_Piece piece : pieces) {
            if (piece.getFile() == file && piece.getrank() == rank) {
                return piece;
            }
        }
        return null;
    }
}