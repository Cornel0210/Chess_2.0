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
        if (canMoveTo(newPosition, board)){
            setPosition(newPosition);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Position newPosition, Board board) {
        if ((board.isSameRow(position, newPosition) ||
        board.isSameColumn(position, newPosition) ||
        board.isADiagPos(position, newPosition)) &&
        hasNoPiecesTo(newPosition, board)) {
            return board.isEmptyCell(newPosition) || board.getPiece(newPosition).getColour() != colour;
        }
        return false;
    }

    private boolean hasNoPiecesTo(Position newPosition, Board board){
            return !board.hasPiecesBetween(position, newPosition);
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
        return colour == Colour.WHITE ? "\u001B[37mQ\u001B[0m" : "\u001B[30mQ\u001B[0m";
    }
}
