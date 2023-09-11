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
        if (board.isSameRow(position, newPosition)){
            return board.getPiecesBetween_RowPositions(position, newPosition).isEmpty();
        }
        if (board.isSameColumn(position, newPosition)){
            return board.getPiecesBetween_ColumPositions(position, newPosition).isEmpty();
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
