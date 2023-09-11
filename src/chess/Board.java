package chess;

import java.util.Arrays;

public class Board {
    Piece[][] board = new Piece[8][8];

    public Board() {
        for (int j = 0; j < board.length; j++) {
            board[1][j] = new Pawn(new Position(1, j), Colour.BLACK);
            board[6][j] = new Pawn(new Position(1, j), Colour.WHITE);
        }
        board[0][0] = new Rook(new Position(0,0), Colour.BLACK);
        board[0][7] = new Rook(new Position(0,7), Colour.BLACK);
        board[7][7] = new Rook(new Position(7,7), Colour.WHITE);
        board[7][0] = new Rook(new Position(7,0), Colour.WHITE);
    }

    public Board(Piece[][] board) {
        this.board = board;
    }

    public Piece getPiece(Position position){
        return board[position.getX()][position.getY()];
    }

    public boolean isEmptyCell (Position position){
        return board[position.getX()][position.getY()] == null;
    }
    public void updateBoard(Position oldPosition, Piece piece){
        board[oldPosition.getX()][oldPosition.getY()] = null;
        board[piece.getPosition().getX()][piece.getPosition().getY()] = piece;
    }

    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null){
                    stringBuilder.append(" |");
                } else {
                    stringBuilder.append(board[i][j]).append("|");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
