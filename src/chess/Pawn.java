package chess;

public class Pawn implements Piece{
    private Position position;
    private  final Colour colour;

    public Pawn(Position position, Colour colour) {
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
        if (isMovingTwoSquares(newPosition, board) || isMovingOneSquare(newPosition, board) || isAttacking(newPosition, board)){
            return true;
        }
        return false;
    }

    private boolean isMovingOneSquare(Position newPosition, Board board){
        return isMovingOnTheSameColumn(newPosition) && movesOneFwd(newPosition) && board.getPiece(newPosition) == null;
    }

    private boolean isMovingTwoSquares(Position newPosition, Board board){
        if (isAtInitPos() && isMovingOnTheSameColumn(newPosition) && movesTwoFwd(newPosition)){
            if (colour == Colour.BLACK){
                for (int i = position.getX()-1; i >= newPosition.getX(); i--) {
                    if (board.getPiece(new Position(i, position.getY())) != null){
                        return false;
                    }
                }
            } else {
                for (int i = position.getX()+1; i <= newPosition.getX(); i++) {
                    if (board.getPiece(new Position(i, position.getY())) != null){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean isAtEndOfBoard(Colour colour){
        if (colour == Colour.BLACK){
            return position.getX() == 0;
        } else {
            return position.getX()  == 7;
        }
    }

    private boolean isAtInitPos(){
        if (colour == Colour.WHITE){
            return position.getX()  == 1;
        } else {
            return position.getX()  == 6;
        }
    }

    private boolean movesTwoFwd(Position newPosition){
        if (colour == Colour.BLACK){
            return newPosition.getX() < position.getX() && Math.abs(newPosition.getX() - position.getX()) == 2;
        } else {
            return newPosition.getX() > position.getX() && Math.abs(newPosition.getX() - position.getX()) == 2;
        }
    }
    private boolean movesOneFwd(Position newPosition){
        if (colour == Colour.BLACK){
            return newPosition.getX() < position.getX() && Math.abs(newPosition.getX() - position.getX()) == 1;
        } else {
            return newPosition.getX() > position.getX() && Math.abs(newPosition.getX() - position.getX()) == 1;
        }
    }

    private boolean isMovingOnTheSameColumn(Position newPosition){
        return position.getY() == newPosition.getY();
    }

    private boolean isMovingOnDiagonal(Position newPosition){
        return Math.abs(position.getY() - newPosition.getY()) == 1 && movesOneFwd(newPosition);
    }

    private boolean isAttacking(Position newPosition, Board board){
        return isMovingOnDiagonal(newPosition) && board.getPiece(newPosition) != null && board.getPiece(newPosition).getColour() != colour;
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
        return colour == Colour.WHITE ? "WP" : "BP";
    }
}
