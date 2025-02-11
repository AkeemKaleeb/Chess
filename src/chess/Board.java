package chess;

import java.util.ArrayList;

public class Board {
    private ArrayList<ReturnPiece> piecesOnBoard;
    
    public Board() {
        resetBoard();
    }
    
    public void resetBoard() {
        piecesOnBoard = new ArrayList<>();
        
        // Initialize Pawns
        for (ReturnPiece.PieceFile file : ReturnPiece.PieceFile.values()) {
            piecesOnBoard.add(createPiece(file, 2, ReturnPiece.PieceType.WP));
            piecesOnBoard.add(createPiece(file, 7, ReturnPiece.PieceType.BP));
        }
        
        // Initialize Other Pieces
        setupBackRank(1, Chess.Player.white);
        setupBackRank(8, Chess.Player.black);
        }
        
    // Helper method to setup the back rank
    private void setupBackRank(int rank, Chess.Player player) {
        ReturnPiece.PieceFile[] files = ReturnPiece.PieceFile.values();
        
        ReturnPiece.PieceType[] order;
        if (player == Chess.Player.white) {
            order = new ReturnPiece.PieceType[]{ReturnPiece.PieceType.WR, ReturnPiece.PieceType.WN, 
                            ReturnPiece.PieceType.WB, ReturnPiece.PieceType.WQ, 
                            ReturnPiece.PieceType.WK, ReturnPiece.PieceType.WB, 
                            ReturnPiece.PieceType.WN, ReturnPiece.PieceType.WR};
        } else {
            order = new ReturnPiece.PieceType[]{ReturnPiece.PieceType.BR, ReturnPiece.PieceType.BN, 
                            ReturnPiece.PieceType.BB, ReturnPiece.PieceType.BQ, 
                            ReturnPiece.PieceType.BK, ReturnPiece.PieceType.BB, 
                            ReturnPiece.PieceType.BN, ReturnPiece.PieceType.BR};
        }
        
        for (int i = 0; i < 8; i++) {
            piecesOnBoard.add(createPiece(files[i], rank, order[i]));
        }
    }

    // Return the pieces on the board
    public ArrayList<ReturnPiece> getPiecesOnBoard() {
        return piecesOnBoard;
    }
    
    // Move a piece from one location to another
    public boolean movePiece(String from, String to) {
        ReturnPiece.PieceFile fromFile = ReturnPiece.PieceFile.valueOf(from.substring(0, 1));
        int fromRank = Integer.parseInt(from.substring(1));

        ReturnPiece.PieceFile toFile = ReturnPiece.PieceFile.valueOf(to.substring(0, 1));
        int toRank = Integer.parseInt(to.substring(1));

        for (ReturnPiece piece : piecesOnBoard) {
            if (piece.pieceFile == fromFile && piece.pieceRank == fromRank) {
                piece.pieceFile = toFile;
                piece.pieceRank = toRank;
                return true;
            }
        }
        return false;
    }

    // Helper method to create a piece
    private ReturnPiece createPiece(ReturnPiece.PieceFile file, int rank, ReturnPiece.PieceType type) {
        ReturnPiece piece = new ReturnPiece();
        piece.pieceFile = file;
        piece.pieceRank = rank;
        piece.pieceType = type;
        return piece;
    }
}