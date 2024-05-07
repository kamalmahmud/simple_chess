package Board;
import Pieces.*;

public class ChessBoard {
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public static final int boardSize = 8;

    private boolean whitePlaying;
    public final Square[][] squares = new Square[boardSize][boardSize];
    // Constructor
    public ChessBoard() {
        // Set the current player to white
        this.whitePlaying = true;

        // Initialize all squares to empty
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new Square(row,col,this);
            }
        }
        for(int col = 0; col<8; col++) {
            new Pawn(WHITE, squares[6][col]);
            new Pawn(BLACK, squares[1][col]);
        }
        for(int col = 0; col<8; col+=7){
            new Rook(WHITE,squares[7][col]);
            new Rook(BLACK,squares[0][col]);
        }
        new King(WHITE,squares[7][4]);
        new King(BLACK,squares[0][4]);
        new Queen(WHITE,squares[7][3]);
        new Queen(BLACK,squares[0][3]);
        for (int col = 2; col < 7; col+=3){
            new Bishop(WHITE,squares[7][col]);
            new Bishop(BLACK,squares[0][col]);
        }
        for(int col = 1; col<7; col+=5){
            new Knight(WHITE,squares[7][col]);
            new Knight(BLACK,squares[0][col]);
        }

    }

    // Override the toString() method to return a string representation of the chess board
    @Override
    public String toString(){

        String bordStr = "";
        bordStr += "    A   B   C   D   E   F   G   H  \n";
        bordStr += "  ---------------------------------\n";
        for (int row = 0; row < 8; ++row){
            bordStr += 8-row + " ";
            for (int col = 0; col < 8; ++col) {
                bordStr += "|";
                bordStr += " " + squares[row][col] + " ";
                if (col == 7)
                    bordStr += "| ";
            }
            bordStr += 8 - row + " \n";
            bordStr += "  ---------------------------------\n";
        }
        bordStr += "    A   B   C   D   E   F   G   H  \n";

        return bordStr;
    }


    // Get the square at a specified position
    public Square getSquareAt(String position) {
        position = position.toLowerCase();
        // Convert the position string (e.g. "a1") to row and column indices
        int col = position.charAt(0) - 'a';
        int row = 8 - (position.charAt(1) - '0');

        // Return the square at the specified position
        return squares[row][col];
    }
    // Get the current player

    public boolean isWhitePlaying() {
    return whitePlaying;
    }

    // Get the piece at a specified position
    public Piece getPieceAt(String from) {
        return this.getSquareAt(from).getPiece();
    }

    public void nextPlayer() {
        whitePlaying = !isWhitePlaying();
    }

    public Square[] getSquaresBetween(Square s1, Square s2) {
        // Determine the direction of movement
        int rowDirection = Integer.signum(s2.getRow() - s1.getRow());
        int colDirection = Integer.signum(s2.getColumn() - s1.getColumn());

        // Determine the number of squares between location and targetLocation
        int numRows = Math.abs(s2.getRow() - s1.getRow()) - 1;
        int numCols = Math.abs(s2.getColumn() - s1.getColumn()) - 1;
        int numSquares = Math.max(numRows, numCols);

        // Create an array to hold the squares between location and targetLocation
        Square[] squares = new Square[numSquares];

        // Iterate through the squares between location and targetLocation, adding each square to the array
        int row = s1.getRow() + rowDirection;
        int col = s1.getColumn() + colDirection;
        for (int i = 0; i < numSquares; i++) {
            squares[i] = getSquare(row, col);
            row += rowDirection;
            col += colDirection;
        }
        return squares;
    }

    private Square getSquare(int row, int col) {
        return squares[row][col];
    }

    public boolean isGameEnded() {
        // check if white has lost all pieces
        boolean noWhitePieces = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = squares[row][col];
                Piece piece = square.getPiece();
                if (piece != null && piece.getColor() == ChessBoard.WHITE) {
                    noWhitePieces = false;
                    break;
                }
            }
        }

        if (noWhitePieces) {
            System.out.println("Black wins!");
            return true;
        }

        // check if black has lost all pieces
        boolean noBlackPieces = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = squares[row][col];
                Piece piece = square.getPiece();
                if (piece != null && piece.getColor() == ChessBoard.BLACK) {
                    noBlackPieces = false;
                    break;
                }
            }
        }

        if (noBlackPieces) {
            System.out.println("White wins!");
            return true;
        }
        return false; // game not ended yet
    }
}
