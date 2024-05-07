package Pieces;

import Board.ChessBoard;
import Board.Square;

public class Bishop extends Piece {
    public Bishop(int color, Square location) {
        super(color, location);
    }
    public boolean canMove(String to){
        Square targetLocation = location.getBoard().getSquareAt(to);
        Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
        boolean bishopMove = this.location.isOnSameDiagonal(targetLocation);
        return isCondition(targetLocation, bishopMove, color == ChessBoard.WHITE, color == ChessBoard.BLACK, checkSquareArr(between));
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

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "B" : "b";
    }
}
