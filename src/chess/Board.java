package chess;

import java.util.LinkedList;
import java.util.List;

public class Board {
    Piece[][] board = new Piece[8][8];

    public Board() {
        for (int j = 0; j < board.length; j++) {
            board[1][j] = new Pawn(new Position(1, j), Colour.WHITE);
            board[6][j] = new Pawn(new Position(6, j), Colour.BLACK);
        }
        board[0][0] = new Rook(new Position(0,0), Colour.WHITE);
        board[0][7] = new Rook(new Position(0,7), Colour.WHITE);
        board[7][7] = new Rook(new Position(7,7), Colour.BLACK);
        board[7][0] = new Rook(new Position(7,0), Colour.BLACK);

        board[0][1] = new Knight(new Position(0,1), Colour.WHITE);
        board[0][6] = new Knight(new Position(0,6), Colour.WHITE);
        board[7][1] = new Knight(new Position(7,1), Colour.BLACK);
        board[7][6] = new Knight(new Position(7,6), Colour.BLACK);

        board[0][2] = new Bishop(new Position(0,2), Colour.WHITE);
        board[0][5] = new Bishop(new Position(0,5), Colour.WHITE);
        board[7][2] = new Bishop(new Position(7,2), Colour.BLACK);
        board[7][5] = new Bishop(new Position(7,5), Colour.BLACK);

        board[0][3] = new King(new Position(0,3), Colour.WHITE);
        board[7][3] = new King(new Position(7,3), Colour.BLACK);

        board[0][4] = new Queen(new Position(0,4), Colour.WHITE);
        board[7][4] = new Queen(new Position(7,4), Colour.BLACK);

    }

    public Board(Piece[][] board) {
        this.board = board;
    }

    public List<Piece> getPiecesBetween_DiagonalPositions(Position initPosition, Position newPosition){

        List<Piece> pieces = new LinkedList<>();

        if (newPosition.getX() < initPosition.getX() && newPosition.getY() < initPosition.getY()){ //moving up-left
            for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                if (!isEmptyCell(new Position(initPosition.getX() - i, initPosition.getY() - i))){
                    pieces.add(getPiece(new Position(initPosition.getX() - i, initPosition.getY() - i)));
                    break;
                }
            }
        }
        if (newPosition.getX() > initPosition.getX() && newPosition.getY() > initPosition.getY()){ //moving down-right
            for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                if (!isEmptyCell(new Position(initPosition.getX() + i, initPosition.getY() + i))){
                    pieces.add(getPiece(new Position(initPosition.getX() + i, initPosition.getY() + i)));
                    break;
                }
            }
        }
        if (newPosition.getX() > initPosition.getX() && newPosition.getY() < initPosition.getY()){ //moving down-left
            for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                if (!isEmptyCell(new Position(initPosition.getX() + i, initPosition.getY() - i))){
                    pieces.add(getPiece(new Position(initPosition.getX() + i, initPosition.getY() - i)));
                    break;
                }
            }
        }
        if (newPosition.getX() < initPosition.getX() && newPosition.getY() > initPosition.getY()){ //moving up-right
            for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                if (!isEmptyCell(new Position(initPosition.getX() - i, initPosition.getY() + i))){
                    pieces.add(getPiece(new Position(initPosition.getX() - i, initPosition.getY() + i)));
                    break;
                }
            }
        }
        return pieces;
    }

    public List<Piece> getPiecesBetween_RowPositions(Position initPosition, Position newPosition){

        List<Piece> pieces = new LinkedList<>();

        if (newPosition.getY() < initPosition.getY()){
            for (int i = initPosition.getY()-1; i > newPosition.getY(); i--) {
                if (!isEmptyCell(new Position(initPosition.getX(), i))){
                    pieces.add(getPiece(new Position(initPosition.getX(), i)));
                    break;
                }
            }
        } else if (newPosition.getY() > initPosition.getY()){
            for (int i = initPosition.getY()+1; i < newPosition.getY(); i++) {
                if (!isEmptyCell(new Position(initPosition.getX(), i))){
                    pieces.add(getPiece(new Position(initPosition.getX(), i)));
                    break;
                }
            }
        }
        return pieces;
    }

    public List<Piece> getPiecesBetween_ColumPositions(Position initPosition, Position newPosition){

        List<Piece> pieces = new LinkedList<>();

            if (newPosition.getX() < initPosition.getX()){
                for (int i = initPosition.getX()-1; i > newPosition.getX(); i--) {
                    if (!isEmptyCell(new Position(i, initPosition.getY()))){
                        pieces.add(getPiece(new Position(i, initPosition.getY())));
                        break;
                    }
                }
            } else if (newPosition.getX() > initPosition.getX()){
                for (int i = initPosition.getX()+1; i < newPosition.getX(); i++) {
                    if (!isEmptyCell(new Position(i, initPosition.getY()))){
                        pieces.add(getPiece(new Position(i, initPosition.getY())));
                        break;
                    }
                }
            }
        return pieces;
    }

    public boolean isSameRow(Position initPosition, Position newPosition){
        return initPosition.getX() == newPosition.getX();
    }

    public boolean isSameColumn(Position initPosition, Position newPosition){
        return initPosition.getY() == newPosition.getY();
    }

    public boolean isADiagPos(Position initPosition, Position newPosition){
        return Math.abs(initPosition.getX() - newPosition.getX()) == Math.abs(initPosition.getY() - newPosition.getY());
    }


    public Piece getPiece(Position position){
        return board[position.getX()][position.getY()];
    }

    public boolean isEmptyCell (Position position){
        return board[position.getX()][position.getY()] == null;
    }
    public void updateBoard(Position oldPosition, Position newPosition, Piece piece){
        piece.setPosition(newPosition);
        board[oldPosition.getX()][oldPosition.getY()] = null;
        board[piece.getPosition().getX()][piece.getPosition().getY()] = piece;
    }

    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null){
                    stringBuilder.append("  |");
                } else {
                    stringBuilder.append(board[i][j]).append("|");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
