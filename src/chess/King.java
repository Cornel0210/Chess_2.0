package chess;

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
                setPosition(newPosition);
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

    private boolean isUnderCheck(){

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
