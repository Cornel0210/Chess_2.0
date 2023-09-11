package chess;

import java.util.LinkedList;
import java.util.List;

public interface Piece {
    boolean moveTo(Position newPosition, Board board);
    Colour getColour();
    void setPosition(Position position);
    Position getPosition();

    default List<Piece> getPiecesFromDiagonal(Position initPosition, Position newPosition, Board board){

        List<Piece> pieces = new LinkedList<>();

        if (newPosition.getX() < initPosition.getX() && newPosition.getY() < initPosition.getY()){ //moving up-left
            for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                if (!board.isEmptyCell(new Position(initPosition.getX() - i, initPosition.getY() - i))){
                    pieces.add(board.getPiece(new Position(initPosition.getX() - i, initPosition.getY() - i)));
                    break;
                }
            }
        }
        if (newPosition.getX() > initPosition.getX() && newPosition.getY() > initPosition.getY()){ //moving down-right
            for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                if (!board.isEmptyCell(new Position(initPosition.getX() + i, initPosition.getY() + i))){
                    pieces.add(board.getPiece(new Position(initPosition.getX() + i, initPosition.getY() + i)));
                    break;
                }
            }
        }
        if (newPosition.getX() > initPosition.getX() && newPosition.getY() < initPosition.getY()){ //moving down-left
            for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                if (!board.isEmptyCell(new Position(initPosition.getX() + i, initPosition.getY() - i))){
                    pieces.add(board.getPiece(new Position(initPosition.getX() + i, initPosition.getY() - i)));
                    break;
                }
            }
        }
        if (newPosition.getX() < initPosition.getX() && newPosition.getY() > initPosition.getY()){ //moving up-right
            for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                if (!board.isEmptyCell(new Position(initPosition.getX() - i, initPosition.getY() + i))){
                    pieces.add(board.getPiece(new Position(initPosition.getX() - i, initPosition.getY() + i)));
                    break;
                }
            }
        }
        return pieces;
    }

    default List<Piece> getPiecesFromRow(Position initPosition, Position newPosition, Board board){

        List<Piece> pieces = new LinkedList<>();

        if (newPosition.getY() < initPosition.getY()){
            for (int i = initPosition.getY()-1; i > newPosition.getY(); i--) {
                if (!board.isEmptyCell(new Position(initPosition.getX(), i))){
                    pieces.add(board.getPiece(new Position(initPosition.getX(), i)));
                    break;
                }
            }
        } else if (newPosition.getY() > initPosition.getY()){
            for (int i = initPosition.getY()+1; i < newPosition.getY(); i++) {
                if (!board.isEmptyCell(new Position(initPosition.getX(), i))){
                    pieces.add(board.getPiece(new Position(initPosition.getX(), i)));
                    break;
                }
            }
        }
        return pieces;
    }

    default List<Piece> getPiecesFromColum(Position initPosition, Position newPosition, Board board){

        List<Piece> pieces = new LinkedList<>();

        if (isSameColumn(initPosition, newPosition)){
            if (newPosition.getX() < initPosition.getX()){
                for (int i = initPosition.getX()-1; i > newPosition.getX(); i--) {
                    if (!board.isEmptyCell(new Position(i, initPosition.getY()))){
                        pieces.add(board.getPiece(new Position(i, initPosition.getY())));
                        break;
                    }
                }
            } else if (newPosition.getX() > initPosition.getX()){
                for (int i = initPosition.getX()+1; i < newPosition.getX(); i++) {
                    if (!board.isEmptyCell(new Position(i, initPosition.getY()))){
                        pieces.add(board.getPiece(new Position(i, initPosition.getY())));
                        break;
                    }
                }
            }
        }
        return pieces;
    }

    default boolean isSameRow(Position initPosition, Position newPosition){
        return initPosition.getX() == newPosition.getX();
    }

    default boolean isSameColumn(Position initPosition, Position newPosition){
        return initPosition.getY() == newPosition.getY();
    }

    default boolean isADiagPos(Position initPosition, Position newPosition){
        return Math.abs(initPosition.getX() - newPosition.getX()) == Math.abs(initPosition.getY() - newPosition.getY());
    }

}
