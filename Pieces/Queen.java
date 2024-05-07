package Pieces;

import Board.ChessBoard;
import Board.Square;

public class Queen extends Piece {
    public Queen(int color, Square location) {
        super(color, location);
    }
    public boolean canMove(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
        // Check if the queen can move to the target location in a straight line or diagonally
        boolean queenMove = (this.location.isAtSameRow(targetLocation) || this.location.isAtSameColumn(targetLocation) || this.location.isOnSameDiagonal(targetLocation));
        // Check if the target location is empty or occupied by an enemy piece
        return isCondition(targetLocation, queenMove, color == ChessBoard.WHITE, color == ChessBoard.BLACK, checkSquareArr(between));
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

    // Method to return a string representation of the queen
    public String toString() {
        return color == ChessBoard.WHITE ? "Q" : "q";
    }
}
