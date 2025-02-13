package chess;

public class ChessTest {
    public static void testPawnMovement() {
        Chess.start();
        System.out.println("Testing Pawn Movement:");

        // Move white pawn from a2 to a4
        ReturnPlay result = Chess.play("a2 a4");
        assert result.message == null : "Pawn move a2 to a4 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move black pawn from b7 to b5
        result = Chess.play("b7 b5");
        assert result.message == null : "Pawn move b7 to b5 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move white pawn from a4 to b5 (capture black pawn)
        result = Chess.play("a4 b5");
        assert result.message == null : "Pawn capture move a4 to b5 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Verify that the black pawn is removed from play
        boolean blackPawnCaptured = true;
        for (ReturnPiece piece : result.piecesOnBoard) {
            if (piece.pieceType == ReturnPiece.PieceType.BP && piece.pieceFile == ReturnPiece.PieceFile.b && piece.pieceRank == 5) {
                blackPawnCaptured = false;
                break;
            }
        }
        assert blackPawnCaptured : "Black pawn was not captured";

        System.out.println("Pawn Movement Test Passed\n");
    }

    public static void testRookMovement() {
        Chess.start();
        System.out.println("Testing Rook Movement:");

        // Move white pawn from a2 to a4 to free rook
        Chess.play("a2 a4");

        // Move white rook from a1 to a3 (illegal move)
        ReturnPlay result = Chess.play("a1 a3");
        assert result.message == ReturnPlay.Message.ILLEGAL_MOVE : "Rook illegal move a1 to a3 should fail";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move white rook from a1 to a2 (legal move)
        result = Chess.play("a1 a2");
        assert result.message == null : "Rook move a1 to a2 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move black pawn from a7 to a5 to free rook
        Chess.play("a7 a5");

        // Move white rook from a2 to a5 (capture black pawn)
        result = Chess.play("a2 a5");
        assert result.message == null : "Rook capture move a2 to a5 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Verify that the black pawn is removed from play
        boolean blackPawnCaptured = true;
        for (ReturnPiece piece : result.piecesOnBoard) {
            if (piece.pieceType == ReturnPiece.PieceType.BP && piece.pieceFile == ReturnPiece.PieceFile.a && piece.pieceRank == 5) {
                blackPawnCaptured = false;
                break;
            }
        }
        assert blackPawnCaptured : "Black pawn was not captured";

        // Additional rook movement tests
        // Move white rook from a5 to a8 (capture black rook)
        result = Chess.play("a5 a8");
        assert result.message == null : "Rook capture move a5 to a8 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move black rook from h8 to h5
        result = Chess.play("h8 h5");
        assert result.message == null : "Rook move h8 to h5 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move white rook from a8 to h8 (capture black rook)
        result = Chess.play("a8 h8");
        assert result.message == null : "Rook capture move a8 to h8 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        System.out.println("Rook Movement Test Passed\n");
    }

    public static void testKnightMovement() {
        Chess.start();
        System.out.println("Testing Knight Movement:");

        // Move white knight from b1 to c3
        ReturnPlay result = Chess.play("b1 c3");
        assert result.message == null : "Knight move b1 to c3 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move black knight from g8 to f6
        result = Chess.play("g8 f6");
        assert result.message == null : "Knight move g8 to f6 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move white knight from c3 to d5 (capture black pawn)
        Chess.play("e7 e5"); // Move black pawn to d5
        result = Chess.play("c3 d5");
        assert result.message == null : "Knight capture move c3 to d5 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Verify that the black pawn is removed from play
        boolean blackPawnCaptured = true;
        for (ReturnPiece piece : result.piecesOnBoard) {
            if (piece.pieceType == ReturnPiece.PieceType.BP && piece.pieceFile == ReturnPiece.PieceFile.d && piece.pieceRank == 5) {
                blackPawnCaptured = false;
                break;
            }
        }
        assert blackPawnCaptured : "Black pawn was not captured";

        // Additional knight movement tests
        // Move white knight from d5 to f6 (capture black knight)
        result = Chess.play("d5 f6");
        assert result.message == null : "Knight capture move d5 to f6 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move black knight from b8 to c6
        result = Chess.play("b8 c6");
        assert result.message == null : "Knight move b8 to c6 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move white knight from f6 to e4
        result = Chess.play("f6 e4");
        assert result.message == null : "Knight move f6 to e4 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        System.out.println("Knight Movement Test Passed\n");
    }

    public static void testBishopMovement() {
        Chess.start();
        System.out.println("Testing Bishop Movement:");

        // Move white pawn from e2 to e4 to free bishop
        Chess.play("e2 e4");

        // Move white bishop from f1 to c4
        ReturnPlay result = Chess.play("f1 c4");
        assert result.message == null : "Bishop move f1 to c4 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move black pawn from d7 to d5 to free bishop
        Chess.play("d7 d5");

        // Move black bishop from c8 to f5
        result = Chess.play("c8 f5");
        assert result.message == null : "Bishop move c8 to f5 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move white bishop from c4 to f7 (capture black pawn)
        result = Chess.play("c4 f7");
        assert result.message == null : "Bishop capture move c4 to f7 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Verify that the black pawn is removed from play
        boolean blackPawnCaptured = true;
        for (ReturnPiece piece : result.piecesOnBoard) {
            if (piece.pieceType == ReturnPiece.PieceType.BP && piece.pieceFile == ReturnPiece.PieceFile.f && piece.pieceRank == 7) {
                blackPawnCaptured = false;
                break;
            }
        }
        assert blackPawnCaptured : "Black pawn was not captured";

        // Additional bishop movement tests
        // Move white bishop from f7 to h5
        result = Chess.play("f7 h5");
        assert result.message == null : "Bishop move f7 to h5 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move black bishop from f5 to e4 (capture white pawn)
        result = Chess.play("f5 e4");
        assert result.message == null : "Bishop capture move f5 to e4 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        System.out.println("Bishop Movement Test Passed\n");
    }

    public static void testQueenMovement() {
        Chess.start();
        System.out.println("Testing Queen Movement:");

        // Move white pawn from d2 to d4 to free queen
        Chess.play("d2 d4");

        // Move white queen from d1 to h5
        ReturnPlay result = Chess.play("d1 h5");
        assert result.message == null : "Queen move d1 to h5 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move black pawn from e7 to e5 to free queen
        Chess.play("e7 e5");

        // Move black queen from d8 to h4
        result = Chess.play("d8 h4");
        assert result.message == null : "Queen move d8 to h4 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move white queen from h5 to e5 (capture black pawn)
        result = Chess.play("h5 e5");
        assert result.message == null : "Queen capture move h5 to e5 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Verify that the black pawn is removed from play
        boolean blackPawnCaptured = true;
        for (ReturnPiece piece : result.piecesOnBoard) {
            if (piece.pieceType == ReturnPiece.PieceType.BP && piece.pieceFile == ReturnPiece.PieceFile.e && piece.pieceRank == 5) {
                blackPawnCaptured = false;
                break;
            }
        }
        assert blackPawnCaptured : "Black pawn was not captured";

        // Additional queen movement tests
        // Move white queen from e5 to e8 (capture black rook)
        result = Chess.play("e5 e8");
        assert result.message == null : "Queen capture move e5 to e8 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move black queen from h4 to h7
        result = Chess.play("h4 h7");
        assert result.message == null : "Queen move h4 to h7 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move white queen from e8 to h8 (capture black rook)
        result = Chess.play("e8 h8");
        assert result.message == null : "Queen capture move e8 to h8 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        System.out.println("Queen Movement Test Passed\n");
    }

    public static void testKingMovement() {
        Chess.start();
        System.out.println("Testing King Movement:");

        // Move white pawn from e2 to e4 to free king
        Chess.play("e2 e4");

        // Move white king from e1 to e2
        ReturnPlay result = Chess.play("e1 e2");
        assert result.message == null : "King move e1 to e2 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move black pawn from e7 to e5 to free king
        Chess.play("e7 e5");

        // Move black king from e8 to e7
        result = Chess.play("e8 e7");
        assert result.message == null : "King move e8 to e7 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move white king from e2 to e5 (capture black pawn)
        result = Chess.play("e2 e5");
        assert result.message == null : "King capture move e2 to e5 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Verify that the black pawn is removed from play
        boolean blackPawnCaptured = true;
        for (ReturnPiece piece : result.piecesOnBoard) {
            if (piece.pieceType == ReturnPiece.PieceType.BP && piece.pieceFile == ReturnPiece.PieceFile.e && piece.pieceRank == 5) {
                blackPawnCaptured = false;
                break;
            }
        }
        assert blackPawnCaptured : "Black pawn was not captured";

        // Additional king movement tests
        // Move white king from e5 to f6
        result = Chess.play("e5 f6");
        assert result.message == null : "King move e5 to f6 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move black king from e7 to e6
        result = Chess.play("e7 e6");
        assert result.message == null : "King move e7 to e6 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        // Move white king from f6 to e6 (capture black king)
        result = Chess.play("f6 e6");
        assert result.message == null : "King capture move f6 to e6 failed";
        PlayChess.printBoard(result.piecesOnBoard);

        System.out.println("King Movement Test Passed\n");
    }
}