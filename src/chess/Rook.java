package chess;

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
        if (isCleanPath(newPosition, board)){
            if ((board.isEmptyCell(newPosition) || board.getPiece(newPosition).getColour() != colour)) {
                //setPosition(newPosition);
                wasMoved = true;
                return true;
            }
        }
        return false;
    }
    private boolean isCleanPath(Position newPosition, Board board){
        if (Piece.super.isSameRow(position, newPosition)){
            return Piece.super.getPiecesFromRow(position, newPosition, board).isEmpty();
        }
        if (Piece.super.isSameColumn(position, newPosition)){
            return Piece.super.getPiecesFromColum(position, newPosition, board).isEmpty();
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
        return colour == Colour.WHITE ? "WR" : "BR";
    }
}
