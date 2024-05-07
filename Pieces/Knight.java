package Pieces;

import Board.ChessBoard;
import Board.Square;

public class Knight extends Piece {
    public Knight(int color, Square location) {
        super(color, location);
    }

    public boolean canMove(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = Math.abs(targetLocation.getRowDistance(location));
        int colDistance = Math.abs(targetLocation.getColumnDistance(location));
        boolean knightMove = (rowDistance == 1 && colDistance == 2) || (rowDistance == 2 && colDistance == 1);

        return isCondition(targetLocation, knightMove, color == ChessBoard.WHITE, color == ChessBoard.BLACK);
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

    public String toString() {
        return color == ChessBoard.WHITE ? "N" : "n";
    }
}
