package chess.pieces;

import chess.Board;
import chess.helper.Colour;
import chess.Player;
import chess.helper.Position;

public class Knight implements Piece {
    private Position position;
    private final Colour colour;

    public Knight(Position position, Colour colour) {
        this.position = position;
        this.colour = colour;
    }

    /**
     * This method moves the current knight to newPosition, sets its current position to newPosition,
     * and also updates the board with this piece at newPosition.
     * Also, it deletes from the chess board the piece from the previous position.
     * If at the new position there is a piece that belongs to the opponent, its list with lost pieces will be updated.
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
    }

    /**
     * This method checks if:
     *      this piece belongs to the current player,
     *      the player wants to move the knight to a position that forms with the current one an 'L' shape
     *          (2 squares left/right and 1 square up/down or 1 square left/right and 2 squares up/down).
     * If all these requirements are met, it uses the default method to check if at the new position there is an empty
     * spot or if there is a piece that belongs to the opponent player.
     * Moreover, the method checks if after performing the move the current player's king will be checked.
     * @param newPosition - represents the position where the current player wants to move his piece;
     * @param board - represents the chess board;
     * @param currentPlayer - represents the player that is currently moving;
     * @param opponent - represents the opponent of current player;
     * @return true if all the following requirements are met:
     *      the piece has the same colour with player's colour,
     *      the piece is moved 2 squares left/right and 1 square up/down or 1 square left/right and 2 squares up/down,
     *      there is an opponent's piece at the new position or there is no other piece,
     *      the move didn't get the current player's king in check.
     *      If any of these conditions is not met, the method returns false.
     */
    @Override
    public boolean canMoveTo(Position newPosition, Board board, Player currentPlayer, Player opponent) {
        if (colour == currentPlayer.getColour() &&
                this.isValid(newPosition) &&
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
     * It sets the current knight to the previous position, and if a piece that belongs to the opponent was removed,
     * it is put back on the board at its position.
     * @param oldPos - represents the position where the knight was before the last move;
     * @param newPos - represents the current position for the knight and also the position for a piece that belongs to
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
     * This method checks if the current piece is moving 2 squares left/right and 1 square up/down or
     * 1 square left/right and 2 squares up/down.
     * @param newPosition - represents the position where the player wants to move the piece;
     * @return true if the piece is moved 2 squares left/right and 1 square up/down or
     *      1 square left/right and 2 squares up/down, or false otherwise.
     */
    private boolean isValid(Position newPosition) { //checks if the move that you want to perform respects the 'L' shape
        return Math.abs(position.getX() - newPosition.getX()) == 2 && Math.abs(position.getY() - newPosition.getY()) == 1 ||
                Math.abs(position.getX() - newPosition.getX()) == 1 && Math.abs(position.getY() - newPosition.getY()) == 2;
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
        return colour == Colour.WHITE ? "\u001B[37mH\u001B[0m" : "\u001B[30mH\u001B[0m";
    }
}
