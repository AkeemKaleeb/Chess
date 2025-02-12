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

        System.out.println("King Movement Test Passed\n");
    }
}