package chess;

public class Coordinate {
    int rank;
    int file;

    public Coordinate(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public Coordinate(Coordinate coordinate) {
        rank = coordinate.getRank();
        file = coordinate.getFile();
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }
}