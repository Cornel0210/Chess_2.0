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
        if (canMoveTo(newPosition, board)){
            setPosition(newPosition);
            wasMoved = true;
            return true;
        }
        if (canCastle(newPosition, board)){
            Piece piece;
            if (newPosition.getY() < position.getY()){
                piece = board.getPiece(new Position(position.getX(), 0));
            } else {
                piece = board.getPiece(new Position(position.getX(), 7));
            }
            ((Rook) piece).wasUsedToCastle(newPosition);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Position newPosition, Board board) {
        if (canBeMovedTo(newPosition, board) &&
                isSafePosition(newPosition, board) &&
                isMovingOneSquare(newPosition) &&
                hasMoreThanOneSquareToOppKing(newPosition)){
            return true;
        }
        return false;
    }

    private boolean isSafePosition(Position newPosition, Board board){ //checks if king will be checked at the newPosition
        List<Piece> opponentPieces = board.getPiecesThatCanGoTo(newPosition);
        for (Piece piece : opponentPieces){
            if (piece.getColour() != colour && piece.canMoveTo(newPosition, board)){
                return false;
            }
        }
        return true;
    }

    private boolean canBeMovedTo(Position newPosition, Board board){ //checks if the newPosition is empty or there is an
                                                                // opponent's piece
        return (board.isEmptyCell(newPosition) || board.getPiece(newPosition).getColour() != colour);
    }

    private boolean isMovingOneSquare(Position newPosition){ //checks if king is moving one square from the initial pos
         return !newPosition.equals(position) &&
                Math.abs(position.getX() - newPosition.getX()) <=1 &&
                Math.abs(position.getY() - newPosition.getY()) <=1;
    }

    private boolean hasMoreThanOneSquareToOppKing(Position newPosition){ //calculating the distance between the kings in order
                                                                // not to come closer than one square each other
        King opponentKing = Game.getInstance().getOpponent(colour).getKing();
        if (Math.abs(opponentKing.getPosition().getX() - newPosition.getX()) <= 1){
            return Math.abs(opponentKing.getPosition().getY() - newPosition.getY()) >= 2;
        }
        if (Math.abs(opponentKing.getPosition().getY() - newPosition.getY()) <= 1){
            return Math.abs(opponentKing.getPosition().getX() - newPosition.getX()) >= 2;
        }
        return true;

    }

    private boolean canCastle(Position newPosition, Board board){ //checks if all rules are respected in order to
                                                                // perform the castle
        return !wasMoved &&
                isMovingTwoSquares(newPosition, board) &&
                isRookAtInitPos(newPosition, board) &&
                hasMoreThanOneSquareToOppKing(newPosition) &&
                positionsAreNotChecked(newPosition, board);
    }

    private boolean isMovingTwoSquares(Position newPosition, Board board){ //checks if king is moving two squares from
        // the initial position, in order to perform a castle

        return Math.abs(position.getY() - newPosition.getY()) ==2;
    }

    private boolean isRookAtInitPos(Position newPosition, Board board){ //checks if the rook with king wants to perform the
                                                                    //castle was ever moved
        Piece piece;
        if (newPosition.getY() < position.getY()){
            piece = board.getPiece(new Position(position.getX(), 0));
        } else {
            piece = board.getPiece(new Position(position.getX(), 7));
        }

        if (isRook(piece) && !((Rook) piece).wasMoved()){
            return hasEmptyPathThroughRook(piece.getPosition(), board);
        }

        return false;
    }

    private boolean isRook(Piece piece){
        if (piece != null) {
            return piece instanceof Rook;
        }
       return false;
    }

    private boolean hasEmptyPathThroughRook(Position rookPosition, Board board){ //checks if between king and rook
                                                                                // are no other pieces
        List<Piece> piecesBetween = board.getPiecesBetween_RowPositions(position, rookPosition);
        for (Piece piece : piecesBetween){
            if (piece != null){
                return false;
            }
        }
        return true;
    }
    private boolean positionsAreNotChecked (Position newPosition, Board board){ //checks if king is checked at current
                                                    // position or goes through chess while castling
        if (newPosition.getY() < position.getY()){
            for (int i = position.getY(); i > position.getY()-3; i--) {
                if (!isSafePosition(new Position(position.getX(), i), board)){
                    return false;
                }
            }
        } else {
            for (int i = position.getY(); i < position.getY()+3; i++) {
                if (!isSafePosition(new Position(position.getX(), i), board)){
                    return false;
                }
            }
        }
        return true;
    }

    public int piecesThatThreatensKing(Board board, List<Piece> opponentPieces){
        int count = 0;
        for (Piece piece : opponentPieces){
            if (piece.canMoveTo(position, board)){
                count++;
            }
        }
        return count;
    }
    public boolean isUnderCheck(Board board){
        return piecesThatThreatensKing(board) != 0;
    }

    public int piecesThatThreatensKing(Board board){ //checks if at current position, king is checked
        List<Piece> opponentsPieces = Game.getInstance().getOpponent(colour).getAvailablePieces();
        int count = 0;
        for (Piece piece : opponentsPieces){
            if (piece.canMoveTo(position, board)){
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
