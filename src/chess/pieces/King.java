package chess.pieces;

import chess.*;
import chess.helper.Colour;
import chess.helper.Position;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.List;

public class King implements Piece {
    private Position position;
    private final Colour colour;

    /**
     * This field is used to check whether the current piece was moved (used for the castling situation).
     * It will be false until the piece will be moved.
     */
    private boolean wasMoved;

    public King(Position position, Colour colour) {
        this.position = position;
        this.colour = colour;
        wasMoved = false;
    }

    /**
     * This method moves the current king to the newPosition, sets its current position to the newPosition,
     * and also updates the board with this piece at the new position.
     * Also, it deletes from the chess board the piece from the previous position.
     * If at the new position there is a piece that belongs to the opponent, its list with lost pieces will be updated.
     * More than that, this method updates the "wasMoved" field, setting it to true.
     * On the other hand, if the player wants to castle, all the actions above are performed.
     * Moreover, the rook is moved to its new position.
     * @param board - represents the chess board;
     * @param currentPlayer - represents the current player;
     * @param opponent - represent the opponent;
     */
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

    /**
     * This method checks if:
     *      this piece belongs to the current player,
     *      the player wants to move the king to a surrounding position, but not farther than one square,
     *      there is an empty spot or a piece that belongs to the opponent player,
     *      newPosition and the opponent king's position are separated by at least one square,
     *      the move gets the king in check,
     *      the player wants to castle.
     * @param newPosition - represents the position where the current player wants to move its king;
     * @param board - represents the chess board;
     * @param currentPlayer - represents the player that is currently moving;
     * @param opponent - represents the opponent of current player;
     * @return true if all the following requirements are met:
     *      the piece has the same colour with player's colour,
     *      it is moved one square from the current position (on the same row/column/diagonal),
     *      there will be at least one square between kings,
     *      there is an opponent's piece at the new position or there is no other piece,
     *      the move didn't get the current player's king in check
     *      OR
     *      the player can castle and will not be checked.
     *      Otherwise, the method returns false.
     */
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

    /**
     * This method is a helper for "canMoveTo" and is designed to undo the last move.
     * It sets the current king to the previous position, and if a piece that belongs to the opponent was removed,
     * it is put back on the board at its position.
     * @param oldPos - represents the position where the king was before the last move;
     * @param newPos - represents the current position for the king and also the position for a piece that belongs to
     *               the opponent (if any was removed from the table);
     * @param board - represents the chess board;
     * @param opponentPiece - represents the opponent's piece that was removed (parameter is null if no piece was removed);
     * @param opponent - represents the opponent.
     */
    private void undo(Position oldPos, Position newPos, Board board, Piece opponentPiece, Player opponent) {
        setPosition(oldPos);
        board.getChessBoard()[oldPos.getX()][oldPos.getY()] = this;
        if (opponentPiece!= null){
            opponent.undoRemovedPiece();
        }
        board.getChessBoard()[newPos.getX()][newPos.getY()] = opponentPiece;
    }

    /**
     * This method is a helper for "canMoveTo" and it moves the current king to the new position.
     * If at the new position there is a piece that belongs to the opponent, it will be removed.
     * @param newPosition - represents the position where the current player wants to move its piece;
     * @param board - represents the chess board;
     */
    private void moveKing(Position newPosition, Board board) {
        board.getChessBoard()[position.getX()][position.getY()] = null;
        setPosition(newPosition);
        board.getChessBoard()[newPosition.getX()][newPosition.getY()] = this;
    }

    /**
     * This method checks if the king has any escape move, or if it is checkmate.
     * It checks the following situations:
     *      if there is no piece that can threaten the king, it is not checkmate,
     *      if there is a piece that threatens the king, it checks whether the king can be moved to a safe position
     *          (where it will no longer be in check), or if there is at least one piece that belongs to the current
     *          player that can be moved to a position between the king and the opponent's piece,
     *      if there are at least two pieces that belong to the opponent and threaten the current king, it only checks
     *          if the king can be moved to a safe position.
     * @param board - represents the chess board;
     * @param currentPlayer - represents the player that has to move.
     * @param opponent - represents the opponent.
     * @return true if the king can't escape from the check, false if there is a saving move.
     */
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

    /**
     * This method checks if the king is checked at the current position.
     * @param board - represents the chess board;
     * @param currentPlayer - represents the player that has to move.
     * @param opponent - represents the opponent.
     * @return true if the king is under threat, false otherwise.
     */
    public boolean isChecked(Board board, Player currentPlayer, Player opponent) {
        try {
            return piecesThatCheckPos(position, board, currentPlayer, opponent).size() > 0;
        } catch (ConcurrentModificationException e){
            System.out.println(this + " is checked!");
            return true;
        }
    }

    /**
     * This method checks if the king can castle, meaning all the following requirements have to be met:
     *      the king and the rook have not been moved,
     *      king is not checked,
     *      there will be at least one square to the opponent's king after castling,
     *      there are no pieces between the king and the rook,
     *      the king won't go through a checked position,
     *      the king won't be checked at the new position.
     * @param newPosition - represents the position where the current player wants to move its king;
     * @param board - represents the chess board;
     * @param currentPlayer - represents the player that has to move.
     * @param opponent - represents the opponent.
     * @return true if all the above requirements are met, false otherwise.
     */
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

    /**
     * This method checks which pieces that belong to the opponent player can be moved to a given position (newPosition).
     * @param newPosition - represents the position that you want to check if is checked.
     * @param board - represents the chess board;
     * @param currentPlayer  - represents the player that has to move.
     * @param opponent - represents the opponent.
     * @return a list with all pieces that belong to the opponent player and can be moved to newPosition or null
     * if there are no pieces.
     */
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

    /**
     * This method checks if the new position is next to the current position.
     * @param newPosition - represents the position to be checked.
     * @param board - represents the chess board;
     * @return true if there is exactly one square between the current position and the newPosition, false otherwise.
     */
    private boolean isMovingOneSquare(Position newPosition, Board board){ //checks if king is moving one square from the initial pos
         return board.getSurroundingPositions(newPosition).contains(position);
    }

    /**
     * This method checks if there is at least one square between newPosition and the opponent king's position.
     * @param newPosition - represents the position where the current player wants to move;
     * @param opponent - represents the opponent.
     * @return true if there is at least one square between newPosition and the opponent king's position,
     *      false otherwise.
     */
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

    /**
     * This method checks if the king is moved two squares from the current position (for a castling move).
     * @param newPosition - represents the position where the current player wants to move;
     * @return true if the king is moved two squares, false otherwise.
     */
    private boolean isMovingTwoSquares(Position newPosition){ //checks if king is moving two squares from
                                                                // the initial position, in order to perform a castle
        return Math.abs(position.getY() - newPosition.getY()) == 2;
    }

    /**
     * This method is used to retrieve the appropriate rook for castling.
     * @param newPosition - represents the position where the current player wants to move;
     * @param player - represents the current player.
     * @return the left rook if the castling is to the left side, or the right rook otherwise.
     */
    private Rook getRook(Position newPosition, Player player){
        if (newPosition.getY() < position.getY()){
            return player.getLeftRook();
        }
        return player.getRightRook();
    }

    /**
     * This method checks if there are no pieces between the king and the rook.
     * @param rook - represents the rook with which castling will be performed.
     * @param board - represents the chess board;
     * @return true if there is no piece between the king and the rook, false otherwise.
     */
    private boolean hasEmptyPathThroughRook(Rook rook, Board board){ //checks if between king and rook
                                                                                // are no other pieces
        Position rookPos = rook.getPosition();
        return !board.hasPiecesBetween(position, rookPos);
    }

    /**
     * This method checks if the king will be moved through a checked position while the player tries to castle.
     * @param rook - represents the rook with which castling will be performed.
     * @param board - represents the chess board;
     * @param currentPlayer - represents the current player.
     * @param opponent - represents the opponent.
     * @return true if the king won't be moved through a checked position, false otherwise.
     */
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
