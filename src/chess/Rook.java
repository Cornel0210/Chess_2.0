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
    public void moveTo(Position newPosition, Board board) {
        temporaryMoveTo(newPosition, board);
        wasMoved = true;
    }

    public void temporaryMoveTo(Position newPosition, Board board) {
        board.getChessBoard()[position.getX()][position.getY()] = null;
        setPosition(newPosition);
        board.getChessBoard()[position.getX()][position.getY()] = this;
    }

    @Override
    public boolean canMoveTo(Position newPosition, Board board, Player player) {
        if (colour == player.getColour() &&
                Piece.super.isValid(newPosition, board, colour) &&
                (board.isSameRow(position, newPosition) ||
                board.isSameColumn(position, newPosition)) &&
                hasNoPiecesTo(newPosition, board)){

            Piece opponentPiece = board.getPiece(newPosition);
            Position currentPosition = position;
            temporaryMoveTo(newPosition, board);
            King king = player.getKing();
            if (king.isChecked()){
                temporaryMoveTo(currentPosition, board);
                board.getChessBoard()[newPosition.getX()][newPosition.getY()] = opponentPiece;
                return false;
            }
            return true;
        }
        return false;
    }



   /* @Override
    public boolean moveTo(Position newPosition, Board board) {
        if (canMoveTo(newPosition, board)){
            setPosition(newPosition);
            wasMoved = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Position newPosition, Board board) {
        if ((board.isSameRow(position, newPosition) ||
                board.isSameColumn(position, newPosition)) &&
                hasNoPiecesTo(newPosition, board)){
            return board.isEmptyCell(newPosition) || board.getPiece(newPosition).getColour() != colour;
        }
        return false;
    }*/
    private boolean hasNoPiecesTo(Position newPosition, Board board){
        return !board.hasPiecesBetween(position, newPosition);
    }

    public void wasUsedToCastle(Position newPosition, Board board){
        Piece[][] tempBoard = board.getChessBoard();
        tempBoard[position.getX()][position.getY()] = null;
        tempBoard[newPosition.getX()][newPosition.getY()] = this;
        setPosition(newPosition);
        wasMoved = true;
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
        return colour == Colour.WHITE ? "\u001B[37mR\u001B[0m" : "\u001B[30mR\u001B[0m";
    }
}
