package chess;

public class Queen implements Piece {

    private Position position;
    private final Colour colour;

    public Queen(Position position, Colour colour) {
        this.position = position;
        this.colour = colour;
    }

    @Override
    public boolean moveTo(Position newPosition, Board board) {
        if (isCleanPath(newPosition, board)){
            if (board.isEmptyCell(newPosition) || board.getPiece(newPosition).getColour() != colour){
                setPosition(newPosition);
                return true;
            }
        }
        return false;
    }

    private boolean isCleanPath(Position newPosition, Board board){
        return Piece.super.isCleanRow(position, newPosition, board) ||
                Piece.super.isCleanColumn(position, newPosition, board) ||
                Piece.super.isCleanDiag(position, newPosition, board);
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
        return colour == Colour.WHITE ? "WQ" : "BQ";
    }
}
