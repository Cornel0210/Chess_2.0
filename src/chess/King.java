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
        opponent.undoRemovedPiece();
        board.getChessBoard()[newPos.getX()][newPos.getY()] = opponentPiece;
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

        return canCastle(newPosition, board, currentPlayer, opponent);
    }

    public boolean isChecked(Board board, Player currentPlayer, Player opponent){
        return positionIsChecked(position, board, currentPlayer, opponent);
    }

    private boolean positionIsChecked(Position newPosition, Board board, Player currentPlayer, Player opponent){ //checks if king will be checked at the newPosition
        List<Piece> opponentPieces = opponent.getAvailablePieces();
        for (Piece piece : opponentPieces){
            if (piece.canMoveTo(newPosition, board, opponent, currentPlayer)){
                return true;
            }
        }
        return false;
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
            if (!rook.wasMoved()){
                return !isPathToRookChecked(rook, board, currentPlayer, opponent);
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

    private boolean isPathToRookChecked(Rook rook, Board board, Player currentPlayer,  Player opponent){
        if (hasEmptyPathThroughRook(rook, board)){
            Position rookPos = rook.getPosition();
            List<Position> posBetween =  board.getPositionsBetween(position, rookPos);
            List<Piece> opponentPieces = opponent.getAvailablePieces();

            for (Position position : posBetween){
                for (Piece piece : opponentPieces){
                    if (piece.canMoveTo(position, board, currentPlayer, opponent)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isInCheckMate(Board board){
        /*List<Piece> opponentAttackers = piecesThatThreatensKing(board);
        List<Position> surroundingPositions = board.getSurroundingPositions(position);

        if (opponentAttackers.size() >= 2){
            for (Position pos : surroundingPositions){
                if (isChecked(pos,board)){
                    return false;
                }
            }
            return true;
        }

        if (opponentAttackers.size() == 1){
            for (Position pos : surroundingPositions){
                if (isChecked(pos,board)){
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
        }*/
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        King king = (King) o;

        if (!getPosition().equals(king.getPosition())) return false;
        return getColour() == king.getColour();
    }

    @Override
    public int hashCode() {
        int result = getPosition().hashCode();
        result = 31 * result + getColour().hashCode();
        return result;
    }
}
