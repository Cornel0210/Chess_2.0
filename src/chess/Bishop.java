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
                setPosition(newPosition);
                return true;
            }
        }
        return false;
    }

    private boolean isADiagPos(Position newPosition){ //checks if newPosition is on position`s diagonal
        return Math.abs(position.getX() - newPosition.getX()) == Math.abs(position.getY() - newPosition.getY());
    }

    private boolean isCleanPath(Position newPosition, Board board){ //checks for intermediary pieces between current
                                                                    // position and the newPosition
        if (isADiagPos(newPosition)){
            if (newPosition.getX() < position.getX() && newPosition.getY() < position.getY()){ //moving up-left
                for (int i = 1; i < Math.abs(position.getX() - newPosition.getX()); i++) {
                    if (board.getPiece(new Position(position.getX() - i, position.getY() - i)) != null){
                        return false;
                    }
                }
                return true;
            }
            if (newPosition.getX() > position.getX() && newPosition.getY() > position.getY()){ //moving down-right
                for (int i = 1; i < Math.abs(position.getX() - newPosition.getX()); i++) {
                    if (board.getPiece(new Position(position.getX() + i, position.getY() + i)) != null){
                        return false;
                    }
                }
                return true;
            }
            if (newPosition.getX() > position.getX() && newPosition.getY() < position.getY()){ //moving down-left
                for (int i = 1; i < Math.abs(position.getX() - newPosition.getX()); i++) {
                    if (board.getPiece(new Position(position.getX() + i, position.getY() - i)) != null){
                        return false;
                    }
                }
                return true;
            }
            if (newPosition.getX() < position.getX() && newPosition.getY() > position.getY()){ //moving up-right
                for (int i = 1; i < Math.abs(position.getX() - newPosition.getX()); i++) {
                    if (board.getPiece(new Position(position.getX() - i, position.getY() + i)) != null){
                        return false;
                    }
                }
                return true;
            }
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
        return colour == Colour.WHITE ? "b" : "B";
    }
}
