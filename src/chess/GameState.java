package chess;

public class GameState {
    public enum State {
        IN_PROGRESS,
        CHECK_WHITE,
        CHECK_BLACK,
        CHECKMATE_WHITE_WINS,
        CHECKMATE_BLACK_WINS,
        DRAW,
    }
}
