package chess;

public class Position {
    private int file;
    private int rank;

    // Returns True if the position is the same as a provided position
    public boolean equals(Position other) {
        return this.file == other.file && this.rank == other.rank;
    }

    // Returns True if the position is within the bounds of the board
    public boolean isWithinBounds() {
        return this.file >= 0 && this.file < 8 && this.rank >= 0 && this.rank < 8;
    }

    public int getFile() {
        return this.file;
    }
    public int getRank() {
        return this.rank;
    }
    public void setFile(int file) {
        this.file = file;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
}
