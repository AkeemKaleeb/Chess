package chess;

import java.util.ArrayList;

public class Board {
    private p_Piece[][] board; // 2D array representing the chess board

    // Constructor to initialize the board and reset it to the starting position
    public Board() {
        board = new p_Piece[8][8];
        resetBoard();
    }

    // Method to reset the board to the initial chess position
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
        setupBackRank(0, Chess.Player.white); // Setup white pieces on the back rank
        setupBackRank(7, Chess.Player.black); // Setup black pieces on the back rank
    }

    // Helper method to setup the back rank with Rooks, Knights, Bishops, Queen, and King
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

    // Method to get a list of pieces on the board with their positions
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

    // Method to move a piece from one position to another
    public ReturnPlay movePiece(Position from, Position to) {
        ReturnPlay returnPlay = new ReturnPlay();

        // Find piece on the board
        p_Piece piece = getPieceAt(from);
        p_Piece target = getPieceAt(to);

        // If there is no piece or if the move is not valid, return an error
        if (piece == null || !piece.isValidMove(to, this)) {
            returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return returnPlay;
        }

        // Move the piece
        board[to.getFile()][to.getRank()] = piece;
        board[from.getFile()][from.getRank()] = null;
        piece.setPosition(to);

        // Test if this move puts the current player in check
        if (isCheck(piece.getPlayer())) {
            // Undo the move if it puts the player in check
            board[from.getFile()][from.getRank()] = piece;
            board[to.getFile()][to.getRank()] = target;
            piece.setPosition(from);
            returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return returnPlay;
        }

        // Test if this move puts the opponent in check
        if (isCheck(piece.getPlayer().equals(Chess.Player.white) ? Chess.Player.black : Chess.Player.white)) {
            returnPlay.message = ReturnPlay.Message.CHECK;
        }

        return returnPlay;
    }

    // Method to get the piece at a specific position
    public p_Piece getPieceAt(Position position) {
        return board[position.getFile()][position.getRank()];
    }

    // Method to check if a player is in check
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