package chess;

public class Position {
    private int rank;
    private int file;

    public Position(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }
    
    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public void setPosition(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position position = (Position) obj;
        return file == position.file && rank == position.rank;
    }

    public static Position fromString(String input) {
        int file = input.charAt(0) - 'a';
        int rank = Character.getNumericValue(input.charAt(1)) - 1;
        return new Position(file, rank);
    }
}
