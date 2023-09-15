package chess;

public class Pawn implements Piece{
    private Position position;
    private  final Colour colour;

    public Pawn(Position position, Colour colour) {
        this.position = position;
        this.colour = colour;
    }

    @Override
    public void moveTo(Position newPosition, Board board) {
        board.getChessBoard()[position.getX()][position.getY()] = null;
        setPosition(newPosition);
        board.getChessBoard()[position.getX()][position.getY()] = this;
    }

    @Override
    public boolean canMoveTo(Position newPosition, Board board, Player player) {
        if (colour == player.getColour() &&
                (isMovingOneSquare(newPosition, board) ||
                 isMovingTwoSquares(newPosition, board) ||
                 isAttacking(newPosition, board))){

            Piece opponentPiece = board.getPiece(newPosition);
            Position currentPosition = position;
            moveTo(newPosition, board);
            King king = player.getKing();
            if (king.isChecked()){
                moveTo(currentPosition, board);
                board.getChessBoard()[newPosition.getX()][newPosition.getY()] = opponentPiece;
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean isAttacking(Position newPosition, Board board){
        return board.isADiagPos(position, newPosition) &&
                movesOneForward(newPosition) &&
                board.getPiece(newPosition) != null &&
                board.getPiece(newPosition).getColour() != colour;
    }

    private boolean isMovingOneSquare(Position newPosition, Board board){
        return board.isSameColumn(position, newPosition) &&
                movesOneForward(newPosition) &&
                board.getPiece(newPosition) == null;
    }

    private boolean isMovingTwoSquares(Position newPosition, Board board){
        return isAtInitPos() &&
                board.isSameColumn(position, newPosition) &&
                movesTwoForward(newPosition) &&
                !board.hasPiecesBetween(position, newPosition) &&
                board.getPiece(newPosition) == null;
    }


    private boolean isAtInitPos(){
        return colour == Colour.WHITE ? position.getX()  == 1 : position.getX()  == 6;
    }

    private boolean movesTwoForward(Position newPosition){
        if (colour == Colour.BLACK){
            return newPosition.getX() < position.getX() && Math.abs(newPosition.getX() - position.getX()) == 2;
        } else {
            return newPosition.getX() > position.getX() && Math.abs(newPosition.getX() - position.getX()) == 2;
        }
    }
    private boolean movesOneForward(Position newPosition){
        if (colour == Colour.BLACK){
            return newPosition.getX() < position.getX() && Math.abs(newPosition.getX() - position.getX()) == 1;
        } else {
            return newPosition.getX() > position.getX() && Math.abs(newPosition.getX() - position.getX()) == 1;
        }
    }

    public boolean isAtEndOfBoard(){
        return colour == Colour.BLACK ? position.getX() == 0 : position.getX()  == 7;
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
        return colour == Colour.WHITE ? "\u001B[37mP\u001B[0m" : "\u001B[30mP\u001B[0m";
    }
}
