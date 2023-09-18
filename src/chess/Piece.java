package chess;

public interface Piece {
    void moveTo(Position newPosition, Board board, Player currentPlayer, Player opponent);
    boolean canMoveTo(Position newPosition, Board board, Player currentPlayer, Player opponent);
    Colour getColour();
    void setPosition(Position position);
    Position getPosition();

    default boolean isValid(Position newPosition, Board board, Colour colour){
        Piece piece = board.getPiece(newPosition);
        return piece == null || piece.getColour() != colour;
    }
}
