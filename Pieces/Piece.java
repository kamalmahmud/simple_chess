package Pieces;
import Board.*;
public abstract class Piece {
    protected int color;
    protected Square location;
    // Constructor
    public Piece(int color, Square location) {
        // Set the color and location of the piece
        this.color = color;
        this.location = location;
        this.location.setPiece(this);
    }

    // Getters and setters for the color and location of the piece
    public int getColor() {
        return color;
    }

    // Static method to check if all squares in an array are empty
    public static boolean checkSquareArr(Square[] squares){
        for (Square square : squares) {
            if (!square.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Static method to check if a move is valid for a piece
    public static boolean isCondition(Square targetLocation, boolean pieceMove, boolean condition1, boolean condition2) {
        boolean validMove = false;
        if (pieceMove){
            if (condition1){
                validMove =  (targetLocation.isEmpty() || targetLocation.getPiece().getColor() == ChessBoard.BLACK);
            }
            else if (condition2){
                validMove =  (targetLocation.isEmpty() || targetLocation.getPiece().getColor() == ChessBoard.WHITE);
            }
        }
        return validMove;
    }

    // Static method to check if a move is valid for a piece with additional conditions
    public static boolean isCondition(Square targetLocation, boolean pieceMove, boolean condition1, boolean condition2, boolean condition3) {
        return isCondition(targetLocation,pieceMove, condition1, condition2) && condition3;
    }

    // Abstract method to check if a piece can move to a specified location
    public abstract boolean canMove(String to);

    // Method to move a piece to a specified location
    public abstract void move(String to);

    // Abstract method to return a string representation of the piece
    public abstract String toString();
}

    // I can implement move method like that, but I put it abstract cause of the uml
//    public void move(String to){
//        Square targetLocation = location.getBoard().getSquareAt(to);
//        // Set the piece on the target location
//        targetLocation.setPiece(this);
//        // Clear the piece from its previous location
//        location.clear();
//        // Update the piece's location
//        location = targetLocation;
//        // Switch the current player
//        location.getBoard().nextPlayer();
//    }


