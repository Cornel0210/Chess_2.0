package chess;

public class Queen implements Piece {

    private Position position;
    private final Colour colour;

    public Queen(Position position, Colour colour) {
        this.position = position;
        this.colour = colour;
    }

    @Override
    public void moveTo(Position newPosition, Board board, Player currentPlayer, Player opponent) {
        opponent.addRemovedPiece(board.getPiece(newPosition));
        board.getChessBoard()[position.getX()][position.getY()] = null;
        setPosition(newPosition);
        board.getChessBoard()[newPosition.getX()][newPosition.getY()] = this;
    }
    public void undo(Position oldPos, Position newPos, Board board, Piece opponentPiece, Player opponent) {
        setPosition(oldPos);
        board.getChessBoard()[oldPos.getX()][oldPos.getY()] = this;
        if (opponentPiece!= null){
            opponent.undoRemovedPiece();
        }
        board.getChessBoard()[newPos.getX()][newPos.getY()] = opponentPiece;
    }
    @Override
    public boolean canMoveTo(Position newPosition, Board board, Player currentPlayer, Player opponent) {

        if (colour == currentPlayer.getColour() &&
                (board.isSameRow(position, newPosition) ||
                board.isSameColumn(position, newPosition) ||
                board.isADiagPos(position, newPosition)) &&
                hasNoPiecesTo(newPosition, board) &&
                Piece.super.isValid(newPosition, board, colour)) {

            Piece opponentPiece = board.getPiece(newPosition);
            Position currentPosition = position;
            moveTo(newPosition, board, currentPlayer, opponent);
            King king = currentPlayer.getKing();
            if (king.isChecked(board, currentPlayer, opponent)) {
                undo(currentPosition, newPosition, board, opponentPiece, opponent);
                return false;
            }
            undo(currentPosition, newPosition, board, opponentPiece, opponent);
            return true;
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
