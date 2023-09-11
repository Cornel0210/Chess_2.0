package chess;

import java.util.List;

public class King implements Piece {
    private Position position;
    private final Colour colour;
    private boolean wasMoved;

    public King(Position position, Colour colour) {
        this.position = position;
        this.colour = colour;
        wasMoved = false;
    }

    @Override
    public boolean moveTo(Position newPosition, Board board) {
        if (canMoveTo(newPosition, board) && isSafePosition(newPosition, board)){
            wasMoved = true;
            //setPosition(newPosition);
            return true;
        }
        return false;
    }

    private boolean isSafePosition(Position newPosition, Board board){
        List<Piece> opponentPieces = board.getPiecesThatAttackAPosition(newPosition);
        for (Piece piece : opponentPieces){
            if (piece.getColour() != colour && piece.moveTo(newPosition, board)){
                return false;
            }
        }
        return true;
    }

    private boolean canMoveTo(Position newPosition, Board board){
        boolean flag = !newPosition.equals(position) &&
                Math.abs(position.getX() - newPosition.getX()) <=1 &&
                Math.abs(position.getY() - newPosition.getY()) <=1;
        flag = flag && isFarEnoughFromOpponentKing(newPosition);
        return flag && (board.isEmptyCell(newPosition) || board.getPiece(newPosition).getColour() != colour);
    }

    private boolean isFarEnoughFromOpponentKing(Position newPosition){
        King opponentKing = Game.getInstance().getOpponent(colour).getKing();
        if (Math.abs(opponentKing.getPosition().getX() - newPosition.getX()) <= 1){
            return Math.abs(opponentKing.getPosition().getY() - newPosition.getY()) >= 2;
        }
        if (Math.abs(opponentKing.getPosition().getY() - newPosition.getY()) <= 1){
            return Math.abs(opponentKing.getPosition().getX() - newPosition.getX()) >= 2;
        }
        return true;

    }

    public int piecesThatThreatensKing(Board board, List<Piece> opponentPieces){
        int count = 0;
        for (Piece piece : opponentPieces){
            if (piece.moveTo(position, board)){
                count++;
            }
        }
        return count;
    }

    public int piecesThatThreatensKing(Board board){
        List<Piece> opponentsPieces = Game.getInstance().getOpponent(colour).getAvailablePieces();
        int count = 0;
        for (Piece piece : opponentsPieces){
            if (piece.moveTo(position, board)){
                count++;
            }
        }
        return count;
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
        return colour == Colour.WHITE ? "WK" : "BK";
    }
}
