package Board;

import Pieces.*;

public class Square {
    private int row;
    private int column;
    public Piece piece;
    private ChessBoard board;

    // Constructor
    public Square(int row, int column, ChessBoard board) {
        // Set the row and column of the square
        this.row = row;
        this.column = column;

        // Set the board of the square
        this.board = board;
    }

    // Methods to check if the square is on the same row, column, or diagonal as another square
    public boolean isAtSameRow(Square targetLocation){
        return this.row == targetLocation.row;
    }
    public boolean isAtSameColumn(Square targetLocation) {
        return this.column == targetLocation.column;
    }
    public boolean isOnSameDiagonal(Square targetLocation){
        return Math.abs(this.getRowDistance(targetLocation)) == Math.abs(this.getColumnDistance(targetLocation));
    }

    // Methods to get the row and column distances between this square and another square
    public int getRowDistance(Square location) {
        return location.row-this.row;
    }

    public int getColumnDistance(Square location) {
        return  location.column - this.column ;
    }

    // Getters and setters for the row, column, and piece of the square
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    // Methods to check if the square is empty, a neighbor of another square, or on the last row of the board
    public boolean isEmpty() {
        return this.piece == null;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public boolean isNeighborColumn(Square targetLocation) {
        return Math.abs(column - targetLocation.getColumn()) == 1;
    }

    public boolean isAtLastRow(int color) {
        return ((color == ChessBoard.WHITE) ? (this.row == 0) : (this.row == 7));
    }

    // Method to clear the square of its piece
    public void clear() {
        this.piece = null;
    }

    // Methods to add a new piece to the square of a specified color
    public void putNewQueen(int color) {
        this.piece = new Queen(color,this);
    }

    // Override the toString() method to return a string representation of the square
    @Override
    public String toString() {
        return this.piece == null ? " ": this.piece.toString();
    }
}
