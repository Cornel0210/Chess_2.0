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
        if(isCleanPath(newPosition, board)){
            Piece temp = board.getPiece(newPosition);
            if (temp == null || temp.getColour() != colour){
                //setPosition(newPosition);
                return true;
            }
        }
        return false;
    }

    private boolean isCleanPath(Position newPosition, Board board){
        if (Piece.super.isADiagPos(position, newPosition)){
            return Piece.super.getPiecesFromDiagonal(position, newPosition, board).isEmpty();
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
        return colour == Colour.WHITE ? "WB" : "BB";
    }
}
