package Pieces;

import Board.ChessBoard;
import Board.Square;

public class King extends Piece {
    public King(int color, Square location) {
        super(color, location);
    }
    public boolean canMove(String to) {

        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColumnDistance(location);
        boolean kingMove = Math.abs(rowDistance) <= 1 && Math.abs(colDistance) <= 1;
        return isCondition(targetLocation, kingMove, color == ChessBoard.WHITE, color == ChessBoard.BLACK);
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
        return color == ChessBoard.WHITE ? "K" : "k";
    }
}