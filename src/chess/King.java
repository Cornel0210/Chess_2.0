package chess;

import java.util.LinkedList;
import java.util.List;

public class King implements Piece {
    private Position position;
    private final Colour colour;
    private boolean wasMoved;

    public King(Position position, Colour colour) {
        this.position = position;
        this.colour = colour;
        wasMoved = false;
    }

    @Override
    public boolean moveTo(Position newPosition, Board board) {
        if (isValid(newPosition)){
            if (board.isEmptyCell(newPosition) || board.getPiece(newPosition) == null){
                wasMoved = true;
                //setPosition(newPosition);
                return true;
            }
        }
        return false;
    }

    private boolean isValid (Position newPosition){
        return !newPosition.equals(position) &&
                Math.abs(position.getX()-newPosition.getX()) <=1 &&
                Math.abs(position.getY()- newPosition.getY()) <=1;
    }

    private boolean isUnderCheck(Board board){
        List<Piece> pieces = new LinkedList<>();

        pieces.addAll(Piece.super.getPiecesFromRow(position, new Position(position.getX(), 0), board));
        pieces.addAll(Piece.super.getPiecesFromRow(position, new Position(position.getX(), 7), board));
        pieces.addAll(Piece.super.getPiecesFromColum(position, new Position(0, position.getY()), board));
        pieces.addAll(Piece.super.getPiecesFromColum(position, new Position(7, position.getY()), board));
        Position temp;
        if (position.getX()>= position.getY()){
            temp = new Position(position.getX() - position.getY(), 0);
            pieces.addAll(Piece.super.getPiecesFromDiagonal(position, temp, board));
        } else if (position.getX()<= position.getY()){
            temp = new Position( 0, position.getY()-position.getX());
            pieces.addAll(Piece.super.getPiecesFromDiagonal(position, temp, board));
        }
        if (!pieces.isEmpty()){
            for (Piece piece : pieces){
                if (piece.moveTo(position,board)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean wasMoved() {
        return wasMoved;
    }

    @Override
    public Colour getColour() {
        return this.colour;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }
}
