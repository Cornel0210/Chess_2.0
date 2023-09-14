package chess;

public class Bishop implements Piece{
    private final Colour colour;
    private Position position;

    public Bishop(Position position, Colour colour) {
        this.colour = colour;
        this.position = position;
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
        if(board.isADiagPos(position, newPosition) &&
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
        return colour == Colour.WHITE ? "\u001B[37mB\u001B[0m" : "\u001B[30mB\u001B[0m";
    }
}
