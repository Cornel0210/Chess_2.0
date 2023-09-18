package chess.pieces;

import chess.*;
import chess.helper.Colour;
import chess.helper.Position;

public class Bishop implements Piece {
    private final Colour colour;
    private Position position;

    public Bishop(Position position, Colour colour) {
        this.colour = colour;
        this.position = position;
    }

    /**
     * This method moves the current bishop to the newPosition, sets its current position to the newPosition,
     * and also updates the board with this piece at the newPosition.
     * Also, it deletes from the chess board the piece from the previous position.
     * If at the new position there is a piece that belongs to the opponent, its list with lost pieces will be updated.
     * @param board - represents the chess board;
     * @param currentPlayer - represents the current player;
     * @param opponent - represent the opponent;
     */
    @Override
    public void moveTo(Position newPosition, Board board, Player currentPlayer, Player opponent) {
        opponent.addRemovedPiece(board.getPiece(newPosition));
        board.getChessBoard()[position.getX()][position.getY()] = null;
        setPosition(newPosition);
        board.getChessBoard()[newPosition.getX()][newPosition.getY()] = this;
    }

    /**
     * This method checks if this piece belongs to the current player, if the player wants to move the bishop to the
     * diagonal, and also if between current bishop's position and the new one there are no other pieces.
     * If all these requirements are met, it uses the default method to check if at the new position there is an empty
     * spot or if there is a piece that belongs to the opponent player.
     * Moreover, the method checks if, after performing the move, the current player's king will be checked.
     * @param newPosition - represents the position where the current player wants to move his piece;
     * @param board - represents the chess board;
     * @param currentPlayer - represents the player that is currently moving;
     * @param opponent - represents the opponent of current player;
     * @return true if all the following requirements are met:
     *      the piece has the same colour with player's colour,
     *      it is moving on diagonal,
     *      there are no other pieces between the current position and the new position,
     *      there is an opponent's piece at the new position or there is no other piece,
     *      the move didn't get the current player's king in check.
     *      If any of these conditions is not met, the method returns false.
     */
    @Override
    public boolean canMoveTo(Position newPosition, Board board, Player currentPlayer, Player opponent) {
        if (colour == currentPlayer.getColour() &&
                board.isADiagonalPos(position, newPosition) &&
                !hasPiecesTo(newPosition, board) &&
                Piece.super.isValid(newPosition, board, colour)){

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

    /**
     * This method is a helper for "canMoveTo" and is designed to undo the last move.
     * It sets the current bishop to the previous position, and if a piece that belongs to the opponent was removed,
     * it is put back on the board at its position.
     * @param oldPos - represents the position where the bishop was before last move;
     * @param newPos - represents the current position for the bishop and also the position for a piece that belongs to
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
     * This method returns true if there are pieces between the current position and the newPosition.
     * @param newPosition - represents the position where the player wants to move the current piece.
     * @param board - represents the chess board.
     * @return true if the path between the current position and the inserted one(newPosition) has no pieces, false otherwise.
     */
    private boolean hasPiecesTo(Position newPosition, Board board){
       return board.hasPiecesBetween(position, newPosition);
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
        return colour == Colour.WHITE ? "\u001B[37mB\u001B[0m" : "\u001B[30mB\u001B[0m";
    }
}
