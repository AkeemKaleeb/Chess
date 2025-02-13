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
    public ReturnPlay movePiece(Position from, Position to) {
        ReturnPlay returnPlay = new ReturnPlay();

        // Find piece on the board
        p_Piece piece = getPieceAt(from);
        p_Piece target = getPieceAt(to);

        // If there is no piece or if the move is not an option, return an error
        if (piece == null || !piece.isValidMove(to, this)) {
            returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return returnPlay;
        }

        // Move the piece
        board[to.getFile()][to.getRank()] = piece;
        board[from.getFile()][from.getRank()] = null;
        piece.setPosition(to);

        // Test if this puts the current player in check
        if(isCheck(piece.getPlayer())) {
            // Undo the move
            board[from.getFile()][from.getRank()] = piece;
            board[to.getFile()][to.getRank()] = target;
            piece.setPosition(from);
            returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return returnPlay;
        }

        return returnPlay;
    }

    public p_Piece getPieceAt(Position position) {
        return board[position.getFile()][position.getRank()];
    }

    // Test for checks
    public boolean isCheck(Chess.Player player) {
        // Find the king's position
        Position kingPosition = null;
        for (int file = 0; file < 8; file++) {
            for (int rank = 0; rank < 8; rank++) {
                p_Piece piece = board[file][rank];
                if (piece != null && piece.getType() == (player.equals(Chess.Player.white) ? ReturnPiece.PieceType.WK : ReturnPiece.PieceType.BK)) {
                    kingPosition = new Position(file, rank);
                    break;
                }
            }
        }

        // Check if any opponent's piece can attack the king
        for (int file = 0; file < 8; file++) {
            for (int rank = 0; rank < 8; rank++) {
                p_Piece piece = board[file][rank];
                if (piece != null && piece.getPlayer() != player && piece.isValidMove(kingPosition, this)) {
                    return true;
                }
            }
        }

        return false;
    }
}