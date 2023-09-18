package chess;

import java.util.ConcurrentModificationException;
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
    public void moveTo(Position newPosition, Board board, Player currentPlayer, Player opponent) {
        if (isMovingTwoSquares(newPosition)){
            if (newPosition.getY() < position.getY()){
                Rook rook = currentPlayer.getLeftRook();
                rook.moveTo(new Position(position.getX(), position.getY()-1), board, currentPlayer, opponent);
            } else {
                Rook rook = currentPlayer.getRightRook();
                rook.moveTo(new Position(position.getX(), position.getY()+1), board, currentPlayer, opponent);
            }
        }

        opponent.addRemovedPiece(board.getPiece(newPosition));
        board.getChessBoard()[position.getX()][position.getY()] = null;
        setPosition(newPosition);
        board.getChessBoard()[newPosition.getX()][newPosition.getY()] = this;
        wasMoved = true;
    }

    public void undo(Position oldPos, Position newPos, Board board, Piece opponentPiece, Player opponent) {
        setPosition(oldPos);
        board.getChessBoard()[oldPos.getX()][oldPos.getY()] = this;
        if (opponentPiece!= null){
            opponent.undoRemovedPiece();
        }
        board.getChessBoard()[newPos.getX()][newPos.getY()] = opponentPiece;
    }

    private void moveKing(Position newPosition, Board board) {
        board.getChessBoard()[position.getX()][position.getY()] = null;
        setPosition(newPosition);
        board.getChessBoard()[newPosition.getX()][newPosition.getY()] = this;
    }

    @Override
    public boolean canMoveTo(Position newPosition, Board board, Player currentPlayer, Player opponent) {

        if (Piece.super.isValid(newPosition, board, colour) &&
                isMovingOneSquare(newPosition, board) &&
                hasMoreThanOneSquareToOppKing(newPosition, opponent)){

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

        if (canCastle(newPosition, board, currentPlayer, opponent)){
            Position currentPosition = position;
            moveKing(newPosition, board);
            King king = currentPlayer.getKing();
            if (king.isChecked(board, currentPlayer, opponent)){
                undo(currentPosition, newPosition, board, null, opponent);
                return false;
            }
            undo(currentPosition, newPosition, board, null, opponent);
            return true;
        }
        return false;
    }

    public boolean isChecked(Board board, Player currentPlayer, Player opponent) {
        try {
            return piecesThatCheckPos(position, board, currentPlayer, opponent).size() > 0;
        } catch (ConcurrentModificationException e){
            System.out.println(this + " is checked!");
            return true;
        }
    }

    private List<Piece> piecesThatCheckPos(Position newPosition, Board board, Player currentPlayer, Player opponent) throws ConcurrentModificationException { //checks if king will be checked at the newPosition
        List<Piece> opponentPieces = opponent.getAvailablePieces();
        List<Piece> opponentAttackers = new LinkedList<>();
        for (Piece piece : opponentPieces){
            if (piece.canMoveTo(newPosition, board, opponent, currentPlayer)){
                opponentAttackers.add(piece);
            }
        }
        return opponentAttackers;
    }

    private boolean isMovingOneSquare(Position newPosition, Board board){ //checks if king is moving one square from the initial pos
         return board.getSurroundingPositions(newPosition).contains(position);
    }

    private boolean hasMoreThanOneSquareToOppKing(Position newPosition, Player opponent){ //calculating the distance between the kings in order
                                                                // not to come closer than one square each other
        King opponentKing = opponent.getKing();
        if (Math.abs(opponentKing.getPosition().getX() - newPosition.getX()) <= 1){
            return Math.abs(opponentKing.getPosition().getY() - newPosition.getY()) >= 2;
        }
        if (Math.abs(opponentKing.getPosition().getY() - newPosition.getY()) <= 1){
            return Math.abs(opponentKing.getPosition().getX() - newPosition.getX()) >= 2;
        }
        return true;

    }

    private boolean canCastle(Position newPosition, Board board, Player currentPlayer, Player opponent){ //checks if all rules are respected in order to
                                                                // perform the castle
        if (!wasMoved &&
                !isChecked(board, currentPlayer, opponent) &&
                isMovingTwoSquares(newPosition) &&
                hasMoreThanOneSquareToOppKing(newPosition, opponent)){

            Rook rook = getRook(newPosition, currentPlayer);
            if (!rook.wasMoved() && currentPlayer.getAvailablePieces().contains(rook)){
                return isPathForCastleSafe(rook, board, currentPlayer, opponent);
            }
        }
        return false;
    }

    private boolean isMovingTwoSquares(Position newPosition){ //checks if king is moving two squares from
                                                                // the initial position, in order to perform a castle
        return Math.abs(position.getY() - newPosition.getY()) == 2;
    }

    private Rook getRook(Position newPosition, Player player){
        if (newPosition.getY() < position.getY()){
            return player.getLeftRook();
        }
        return player.getRightRook();
    }

    private boolean hasEmptyPathThroughRook(Rook rook, Board board){ //checks if between king and rook
                                                                                // are no other pieces
        Position rookPos = rook.getPosition();
        return !board.hasPiecesBetween(position, rookPos);
    }

    private boolean isPathForCastleSafe(Rook rook, Board board, Player currentPlayer, Player opponent){
        if (hasEmptyPathThroughRook(rook, board)){
            Position rookPos = rook.getPosition();
            List<Position> posBetween;
            if (rookPos.getY() > position.getY()){
                posBetween = board.getPositionsBetween(position, new Position(position.getX(), 6));
            } else {
                posBetween =  board.getPositionsBetween(position, rookPos);
            }
            for (Position posToCheck : posBetween){
                if (piecesThatCheckPos(posToCheck, board, currentPlayer, opponent).size() > 0){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isInCheckMate(Board board, Player currentPlayer, Player opponent){
        List<Piece> attackers = piecesThatCheckPos(position, board, opponent, currentPlayer);
        List<Position> surroundingPositions = board.getSurroundingPositions(position);
        if (attackers.isEmpty()){
            return false;
        }
        Position currentPosition = position;

        for (Position pos : surroundingPositions){
            Piece opponentPiece = board.getPiece(pos);
            moveKing(pos, board);
            if (piecesThatCheckPos(pos, board, opponent, currentPlayer).isEmpty()){
                undo(currentPosition, pos, board, opponentPiece, opponent);
                return false;
            }
            undo(currentPosition, pos, board, opponentPiece, opponent);

        }

        if (attackers.size() == 1){
            Piece attacker = attackers.get(0);
            List<Position> posBetween = board.getPositionsBetween(position, attacker.getPosition());
            posBetween.add(attacker.getPosition());
            for (Position pos : posBetween){
                if (!piecesThatCheckPos(pos, board, currentPlayer, opponent).isEmpty()){
                    return false;
                }
            }
        }
        return true;
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
