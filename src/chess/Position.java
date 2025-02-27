package chess;

public class Position {
    private int rank; // The rank (row) of the position on the chessboard
    private int file; // The file (column) of the position on the chessboard

    // Constructor to initialize the position with file and rank
    public Position(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }
    
    // Getter method for the file
    public int getFile() {
        return file;
    }

    // Getter method for the rank
    public int getRank() {
        return rank;
    }

    // Method to set the position with new file and rank values
    public void setPosition(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isValid() {
        return file >= 0 && file < 8 && rank >= 0 && rank < 8;
    }

    // Override the equals method to compare two Position objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { // Check if the objects are the same instance
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) { // Check if the object is null or not of the same class
            return false;
        }
        Position position = (Position) obj; // Cast the object to Position
        return file == position.file && rank == position.rank; // Compare file and rank
    }

    // Static method to create a Position object from a string (e.g., "a1")
    public static Position fromString(String input) {
        int file = input.charAt(0) - 'a'; // Convert file character to an integer (0-based)
        int rank = Character.getNumericValue(input.charAt(1)) - 1; // Convert rank character to an integer (0-based)
        return new Position(file, rank); // Return a new Position object
    }
}
