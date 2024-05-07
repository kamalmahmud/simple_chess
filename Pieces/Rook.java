package Pieces;

import Board.ChessBoard;
import Board.Square;

public class Rook extends Piece {
    public Rook(int color, Square location) {
        super(color, location);
    }

    // Method to check if the rook can move to a specified location
    public boolean canMove(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
        // Check if the rook can move to the target location horizontally or vertically
        boolean isHorizontalOrVertical = this.location.isAtSameRow(targetLocation) || this.location.isAtSameColumn(targetLocation); // rook can only move horizontally or vertically
        // Check if the target location is empty or occupied by an enemy piece
        return isCondition(targetLocation, isHorizontalOrVertical, color == ChessBoard.WHITE, color == ChessBoard.BLACK, checkSquareArr(between));
    }

    public void move(String to){
        Square targetLocation = location.getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        //clear previous location
        location.clear();
        //update current location
        location = targetLocation;
        location.getBoard().nextPlayer();
    }

    // Method to return a string representation of the rook
    public String toString() {
        return color == ChessBoard.WHITE ? "R" : "r";
    }
}