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
                //setPosition(newPosition);
                return true;
            }
        }
        return false;
    }

    private boolean isCleanPath(Position newPosition, Board board){
        if (board.isADiagPos(position, newPosition)){
            return board.getPiecesBetween_DiagonalPositions(position, newPosition).isEmpty();
        }
        if (board.isSameRow(position, newPosition)){
            return board.getPiecesBetween_RowPositions(position, newPosition).isEmpty();
        }
        if (board.isSameColumn(position, newPosition)){
            return board.getPiecesBetween_ColumPositions(position, newPosition).isEmpty();
        }
        return false;
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
