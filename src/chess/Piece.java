package chess;

public interface Piece {


    boolean moveTo(Position newPosition, Board board);
    Colour getColour();
    void setPosition(Position position);
    Position getPosition();
}
