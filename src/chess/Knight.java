package chess;

public class Knight implements Piece {
    private Position position;
    private final Colour colour;

    public Knight(Position position, Colour colour) {
        this.position = position;
        this.colour = colour;
    }

    @Override
    public boolean moveTo(Position newPosition, Board board) {
        if (canMoveTo(newPosition, board)){
            setPosition(newPosition);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Position newPosition, Board board) {
        if (isValid(newPosition)){
            Piece piece = board.getPiece(newPosition);
            return piece == null || piece.getColour() != colour;
        }
        return false;
    }

    private boolean isValid(Position newPosition) { //checks if the move that you want to perform respects the 'L' shape
        return Math.abs(position.getX() - newPosition.getX()) == 2 && Math.abs(position.getY() - newPosition.getY()) == 1 ||
                Math.abs(position.getX() - newPosition.getX()) == 1 && Math.abs(position.getY() - newPosition.getY()) == 2;
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
        return colour == Colour.WHITE ? "\u001B[37mH\u001B[0m" : "\u001B[30mH\u001B[0m";
    }
}
