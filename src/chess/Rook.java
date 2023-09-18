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
    public void moveTo(Position newPosition, Board board, Player currentPlayer, Player opponent) {
        opponent.addRemovedPiece(board.getPiece(newPosition));
        board.getChessBoard()[position.getX()][position.getY()] = null;
        setPosition(newPosition);
        board.getChessBoard()[newPosition.getX()][newPosition.getY()] = this;
        wasMoved = true;
    }

    public void undo(Position oldPos, Position newPos, Board board, Piece opponentPiece, Player opponent) {
        setPosition(oldPos);
        board.getChessBoard()[oldPos.getX()][oldPos.getY()] = this;
        opponent.undoRemovedPiece();
        board.getChessBoard()[newPos.getX()][newPos.getY()] = opponentPiece;
    }

    @Override
    public boolean canMoveTo(Position newPosition, Board board, Player currentPlayer, Player opponent) {
        if (colour == currentPlayer.getColour() &&
                Piece.super.isValid(newPosition, board, colour) &&
                (board.isSameRow(position, newPosition) ||
                board.isSameColumn(position, newPosition)) &&
                hasNoPiecesTo(newPosition, board)){

            Piece opponentPiece = board.getPiece(newPosition);
            Position currentPosition = position;
            moveTo(newPosition, board, currentPlayer, opponent);
            King king = currentPlayer.getKing();
            if (king.isChecked(board, currentPlayer, opponent)){
                undo(currentPosition, newPosition, board, opponentPiece, opponent);
                return false;
            }
            undo(currentPosition, newPosition, board, opponentPiece, opponent);
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
