package chess;

import java.util.LinkedList;
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
            Piece rook;
            Position rookNewPos;
            if (newPosition.getY() < position.getY()){
                rook = board.getPiece(new Position(position.getX(), 0));
                rookNewPos = new Position(position.getX(), position.getY()-1);
            } else {
                rook = board.getPiece(new Position(position.getX(), 7));
                rookNewPos = new Position(position.getX(), position.getY()+1);
            }
            setPosition(newPosition);
            wasMoved = true;
            ((Rook) rook).wasUsedToCastle(rookNewPos, board);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Position newPosition, Board board) {
        return canBeMovedTo(newPosition, board) &&
                isChecked(newPosition, board) &&
                isMovingOneSquare(newPosition, board) &&
                hasMoreThanOneSquareToOppKing(newPosition);
    }

    public boolean isChecked(Board board){
        return isChecked(position, board);
    }

    private boolean isChecked(Position newPosition, Board board){ //checks if king will be checked at the newPosition
        List<Piece> opponentPieces = Game.getInstance().getOpponent(colour).getAvailablePieces();
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

    private boolean isMovingOneSquare(Position newPosition, Board board){ //checks if king is moving one square from the initial pos
         return board.getSurroundingPositions(newPosition).contains(position) && position != newPosition;
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
                isMovingTwoSquares(newPosition) &&
                isRookAtInitPos(newPosition, board) &&
                hasMoreThanOneSquareToOppKing(newPosition) &&
                positionsAreNotChecked(newPosition, board);
    }

    private boolean isMovingTwoSquares(Position newPosition){ //checks if king is moving two squares from
                                                                // the initial position, in order to perform a castle
        return Math.abs(position.getY() - newPosition.getY()) == 2;
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
        return !board.hasPiecesBetween(position, rookPosition);
    }
    private boolean positionsAreNotChecked (Position newPosition, Board board){ //checks if king is checked at current
                                                    // position or goes through chess while castling
        List<Position> positionsToCheck = board.getPositionsBetween(position, newPosition);
        positionsToCheck.add(position);
        for (Position pos : positionsToCheck){
            if (isChecked(pos, board)){
                return false;
            }
        }
        return true;
    }
    public List<Piece> piecesThatThreatensKing(Board board){ //checks if at current position, king is checked
        List<Piece> opponentsPieces = Game.getInstance().getOpponent(colour).getAvailablePieces();
        List<Piece> piecesThatThreatensKing = new LinkedList<>();
        for (Piece piece : opponentsPieces){
            if (piece.canMoveTo(position, board)){
              piecesThatThreatensKing.add(piece);
            }
        }
        return piecesThatThreatensKing;
    }
    public boolean isInCheckMate(Board board){
        List<Piece> opponentAttackers = piecesThatThreatensKing(board);
        List<Position> surroundingPositions = board.getSurroundingPositions(position);

        if (opponentAttackers.size() >= 2){
            for (Position pos : surroundingPositions){
                if (canMoveTo(pos,board)){
                    return false;
                }
            }
            return true;
        }

        if (opponentAttackers.size() == 1){
            for (Position pos : surroundingPositions){
                if (canMoveTo(pos,board)){
                    return false;
                }
            }

            Piece attacker = opponentAttackers.get(0);
            List<Position> posBetweenKingAndAttacker = board.getPositionsBetween(position, attacker.getPosition());
            posBetweenKingAndAttacker.add(attacker.getPosition());

            List<Piece> availablePieces = Game.getInstance().getPlayer(colour).getAvailablePieces();
            for (Position posToCheck : posBetweenKingAndAttacker){
                for (Piece playersPiece : availablePieces){
                    if (playersPiece.canMoveTo(posToCheck, board)){
                        return false;
                    }
                }
            }
            return true;
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
        return colour == Colour.WHITE ? "\u001B[37mK\u001B[0m" : "\u001B[30mK\u001B[0m";
    }
}
