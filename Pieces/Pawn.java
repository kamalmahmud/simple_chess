package Pieces;

import Board.*;
public class Pawn extends Piece {
    public static boolean initialLocation = true;
    private int count = 0;
    public Pawn(int color, Square location) {
            super(color, location);
        }
        public boolean canMove(String to) {
            boolean validMove = false;
            Square targetLocation = location.getBoard().getSquareAt(to);
            int rowDistance = targetLocation.getRowDistance(location);
            if (this.location.isAtSameColumn(targetLocation)) {
                if (color == ChessBoard.WHITE && rowDistance > 0 && rowDistance <= 2) {
                    if (rowDistance == 2) {
                        if (initialLocation) {
                            //pawn is moving twice, check two squares in front are empty
                            Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
                            validMove = targetLocation.isEmpty() && between[0].isEmpty();
                        }
                    } else {
                        validMove = targetLocation.isEmpty();
                    }
                    return validMove;
                } else if (color == ChessBoard.BLACK && rowDistance < 0 && rowDistance >= -2) {
                    if (rowDistance == -2) {
                        if (initialLocation) {
                            //pawn is moving twice, check two squares in front are empty
                            Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
                            validMove = targetLocation.isEmpty() && between[0].isEmpty();
                        }
                    } else {
                        validMove = targetLocation.isEmpty();
                    }
                }
                // attacking diagonals
            }else if (this.location.isNeighborColumn(targetLocation)) {
                if (color == ChessBoard.WHITE && rowDistance == 1) {
                    validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == ChessBoard.BLACK;
                }else if (color == ChessBoard.BLACK && rowDistance == -1) {
                    validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == ChessBoard.WHITE;
                }
            }
            return validMove;
    }

        @Override
        public void move(String to){
            Square targetLocation = location.getBoard().getSquareAt(to);

            if (targetLocation.isAtLastRow(color)){
                targetLocation.putNewQueen(color);
            }else{
                targetLocation.setPiece(this);
            }
            location.clear();
            location = targetLocation;
            location.getBoard().nextPlayer();

            this.count++;
            if (count > 1)
                initialLocation = false;
        }
        @Override
        public String toString() {
            return color == ChessBoard.WHITE ? "P" : "p";
        }
}