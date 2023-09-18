package chess.pieces;

import chess.Board;
import chess.helper.Colour;
import chess.Player;
import chess.helper.Position;

public class Rook implements Piece {
    private final Colour colour;
    private Position position;

    /**
     * This field is used to check whether the current piece was moved (used for the castling situation).
     * It will be false until the piece will be moved.
     */
    private boolean wasMoved = false;

    public Rook(Position position, Colour colour) {
        this.colour = colour;
        this.position = position;
    }

    /**
     * This method moves the current rook to newPosition, sets its current position to newPosition,
     * and also updates the board with this piece at the new position.
     * Also, it deletes from the chess board the piece from the previous position.
     * If at the new position there is a piece that belongs to the opponent, his list with lost pieces will be updated.
     * More than that, this method updates the "wasMoved" field, setting it to true.
     * @param board - represents the chess board;
     * @param currentPlayer - represents the current player;
     * @param opponent - represents the opponent;
     */

    @Override
    public void moveTo(Position newPosition, Board board, Player currentPlayer, Player opponent) {
        opponent.addRemovedPiece(board.getPiece(newPosition));
        board.getChessBoard()[position.getX()][position.getY()] = null;
        setPosition(newPosition);
        board.getChessBoard()[newPosition.getX()][newPosition.getY()] = this;
        wasMoved = true;
    }

    /**
     * This method checks if this piece belongs to the current player, if the player wants to move the rook to the same
     * row or column, and also if between rook's current position and the new one are no other pieces.
     * If all these requirements are met, it uses the default method to check if at the new position there is an empty
     * spot or if there is a piece that belongs to the opponent player.
     * Moreover, the method checks if after performing the move the current player's king will be checked.
     * @param newPosition - represents the position where the current player wants to move his piece;
     * @param board - represents the chess board;
     * @param currentPlayer - represents the player that is currently moving;
     * @param opponent - represents the opponent of the current player;
     * @return true if all the following requirements are met:
     *      the piece has the same colour with player's colour,
     *      it is moving on the same row/column,
     *      there are no other pieces between the current position and the new position,
     *      there is an opponent's piece at the new position or there is no other piece,
     *      the move didn't get the current player's king in check.
     *      If any of these conditions is not met, the method returns false.
     */

    @Override
    public boolean canMoveTo(Position newPosition, Board board, Player currentPlayer, Player opponent) {
        if (colour == currentPlayer.getColour() &&
                Piece.super.isValid(newPosition, board, colour) &&
                (board.isSameRow(position, newPosition) ||
                        board.isSameColumn(position, newPosition)) &&
                !hasPiecesTo(newPosition, board)){

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
     * It sets the current rook to the previous position, and if a piece that belongs to the opponent was removed,
     * it is put back on the board at its position.
     * @param oldPos - represents the position where the rook was before last move;
     * @param newPos - represents the current position for the rook and also the position for a piece that belongs to
     *               the opponent (if any were removed from the table);
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
     * This method returns true if there are pieces between the current position and newPosition.
     * @param newPosition - represents the position where the player wants to move the current piece.
     * @param board - represents the chess board.
     * @return true if the path between the current position and the inserted one(newPosition) has no pieces, false otherwise.
     */

    private boolean hasPiecesTo(Position newPosition, Board board){
        return board.hasPiecesBetween(position, newPosition);
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
