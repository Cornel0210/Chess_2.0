package chess;

import javafx.geometry.Pos;

public class Rook implements Piece {
    private final Colour colour;
    private Position position;
    private boolean wasMoved = false;

    public Rook(Position position, Colour colour) {
        this.colour = colour;
        this.position = position;
    }

    @Override
    public boolean moveTo(Position newPosition, Board board) {
        if (newPosition.isValid() && isCleanPath(newPosition, board)){
            if ((board.getPiece(newPosition) != null && board.getPiece(newPosition).getColour() != colour) ||
                    board.getPiece(newPosition) == null){
                setPosition(newPosition);
                wasMoved = true;
                return true;
            }
        }
        return false;
    }
    private boolean isSameRow(Position newPosition){
        return position.getY() == newPosition.getY();
    }
    
    private boolean isSameColumn(Position newPosition){
        return position.getY() == newPosition.getY();
    }
    private boolean isCleanPath(Position newPosition, Board board){
        if (isSameColumn(newPosition)){
            if (newPosition.getX() < position.getX()){
                for (int i = position.getX()-1; i > newPosition.getX(); i--) {
                    if (board.getPiece(new Position(i, position.getY())) != null){
                        return false;
                    }
                }
            } else if (newPosition.getX() > position.getX()){
                for (int i = position.getX()+1; i < newPosition.getX(); i++) {
                    if (board.getPiece(new Position(i, position.getY())) != null){
                        return false;
                    }
                }
            }
            return true;
        }

        if (isSameRow(newPosition)){
            if (newPosition.getY() < position.getY()){
                for (int i = position.getY()-1; i > newPosition.getY(); i--) {
                    if (board.getPiece(new Position(position.getX(), i)) != null){
                        return false;
                    }
                }
            } else if (newPosition.getY() > position.getY()){
                for (int i = position.getY()+1; i < newPosition.getY(); i++) {
                    if (board.getPiece(new Position(position.getX(), i)) != null){
                        return false;
                    }
                }
            }
            return true;
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

    @Override
    public String toString() {
        return colour == Colour.WHITE ? "r" : "R";
    }
}
