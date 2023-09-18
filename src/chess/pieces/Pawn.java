package chess.pieces;

import chess.*;
import chess.helper.Colour;
import chess.helper.Input;
import chess.helper.Position;

public class Pawn implements Piece{
    private Position position;
    private  final Colour colour;

    public Pawn(Position position, Colour colour) {
        this.position = position;
        this.colour = colour;
    }

    /**
     * This method moves the current pawn to newPosition, sets its current position to newPosition,
     * and also updates the board with this piece at newPosition.
     * Also, it deletes from the chess board the piece from the previous position.
     * If at the new position there is a piece that belongs to the opponent (and the new position is on diagonal,
     * next to the current position), his list with lost pieces will be updated.
     * Moreover, the method checks if the pawn will be moved on the last row, in which case it shifts into another piece,
     * which is chosen by the player (between "rook", "bishop", "knight" and "queen").
     * @param board - represents the chess board;
     * @param currentPlayer - represents the current player;
     * @param opponent - represents the opponent;
     */
    @Override
    public void moveTo(Position newPosition, Board board, Player currentPlayer, Player opponent) {
        if (isAtEndOfBoard(newPosition)){
            opponent.addRemovedPiece(board.getPiece(newPosition));
            String input = Input.getInstance().getShiftedPiece();
            Piece newPiece = getPiece(input, newPosition);
            board.getChessBoard()[position.getX()][position.getY()] = null;
            setPosition(newPosition);
            board.getChessBoard()[newPosition.getX()][newPosition.getY()] = newPiece;
            currentPlayer.getRemovedPieces().remove(this);
            currentPlayer.addAvailablePiece(newPiece);
        } else {
            opponent.addRemovedPiece(board.getPiece(newPosition));
            board.getChessBoard()[position.getX()][position.getY()] = null;
            setPosition(newPosition);
            board.getChessBoard()[newPosition.getX()][newPosition.getY()] = this;
        }
    }

    /**
     * This method checks if this piece belongs to the current player and if the player wants to move the pawn one or two
     * squares forward, or to the next position on the diagonal.
     * If the player wants to move the piece two squares forward, the method checks if the pawn is at his initial position,
     * if it is moved on the same column and if there are no other pieces between current position and the new position.
     * Also, it checks if there are no other pieces at the new position.
     *
     * If the player wants to move the piece one square forward, it checks if the pawn is moved on the same column and
     * if there are no other pieces at the new position.
     * If the player wants to take an opponent's piece, it checks if the new position is on diagonal, if there is a piece
     * on it that belongs to the opponent and if the new position is next to the current position.
     * Moreover, the method checks if, after performing the move, the current player's king will be checked.
     * @param newPosition - represents the position where the current player wants to move his piece;
     * @param board - represents the chess board;
     * @param currentPlayer - represents the player that is currently moving;
     * @param opponent - represents the opponent of current player;
     * @return true if all the following requirements are met:
     *      the piece has the same colour with player's colour,
     *      the piece is moved one square forward, on the same column, and there is an empty spot,
     *      the piece is moved two squares forward, on the same column, there is an empty spot, and between these
     *          positions there are no other pieces,
     *      it is moving on diagonal, and there is a piece that belongs to the opponent,
     *      the move didn't get the current player's king in check.
     *      If any of these conditions is not met, the method returns false.
     */
    @Override
    public boolean canMoveTo(Position newPosition, Board board, Player currentPlayer, Player opponent) {
        if (colour == currentPlayer.getColour() &&
                (isMovingOneSquare(newPosition, board) ||
                 isMovingTwoSquares(newPosition, board) ||
                 isAttacking(newPosition, board))){

            Piece opponentPiece = board.getPiece(newPosition);
            Position currentPosition = position;
            movePawn(newPosition, board, opponent);
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
     * This method is a helper for "canMoveTo" and it moves the current pawn to the new position.
     * If at the new position there is a piece that belongs to the opponent, it will be removed.
     * @param newPosition - represents the position where the current player wants to move his piece;
     * @param board - represents the chess board;
     * @param opponent - represents the opponent of current player;
     */
    private void movePawn(Position newPosition, Board board, Player opponent){
        opponent.addRemovedPiece(board.getPiece(newPosition));
        board.getChessBoard()[position.getX()][position.getY()] = null;
        setPosition(newPosition);
        board.getChessBoard()[newPosition.getX()][newPosition.getY()] = this;
    }

    /**
     * This method is a helper for "canMoveTo" and is designed to undo the last move.
     * It sets the current pawn to the previous position, and if a piece that belongs to the opponent was removed,
     * it is put back on the board at its position.
     * @param oldPos - represents the position where the pawn was before last move;
     * @param newPos - represents the current position for the pawn and also the position for a piece that belongs to
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
     * This method is a helper for "canMoveTo" and checks if the piece moves on a diagonal position, next to
     * the current position and if there is a piece that belongs to the opponent.
     * @param newPosition - represents the position where the player wants to move;
     * @param board - represents the chess board;
     * @return true if all the following conditions are met:
     *      the piece is moving on diagonal, to the next square,
     *      there is a piece that belongs to the opponent player.
     *      Returns false otherwise.
     */
    private boolean isAttacking(Position newPosition, Board board){
        return board.isADiagonalPos(position, newPosition) &&
                movesOneForward(newPosition) &&
                board.getPiece(newPosition) != null &&
                board.getPiece(newPosition).getColour() != colour;
    }

    /**
     * This method is a helper for "canMoveTo" and it checks if the piece is moving on the same column, and if it is
     * moving one square forward.
     * @param newPosition - represents the position where the player wants to move;
     * @param board - represents the chess board;
     * @return true if the piece is moving on the same diagonal and one square forward, false otherwise.
     */
    private boolean isMovingOneSquare(Position newPosition, Board board){
        return board.isSameColumn(position, newPosition) &&
                movesOneForward(newPosition) &&
                board.getPiece(newPosition) == null;
    }

    /**
     * This method is a helper for "canMoveTo" and it checks if the piece is at its initial position,
     * is moving on the same column, is moving two squares forward, there are no pieces between the current and
     * the new position, and there is no piece at the new position.
     * @param newPosition - represents the position where the player wants to move;
     * @param board - represents the chess board;
     * @return true if all the following requirements are met:
     *      the piece is at its initial position,
     *      it is moving on the same column,
     *      it is moving two squares forward,
     *      there are no pieces between those positions,
     *      there is no piece at the new position.
     *      Returns false otherwise.
     */
    private boolean isMovingTwoSquares(Position newPosition, Board board){
        return isAtInitPos() &&
                board.isSameColumn(position, newPosition) &&
                movesTwoForward(newPosition) &&
                !board.hasPiecesBetween(position, newPosition) &&
                board.getPiece(newPosition) == null;
    }

    /**
     * This is a helper method and checks if the current piece is at its initial position.
     * @return true if the piece was not moved, false otherwise.
     */
    private boolean isAtInitPos(){
        return colour == Colour.WHITE ? position.getX()  == 1 : position.getX()  == 6;
    }

    /**
     * This is a helper method, and it checks if the piece is moving two squares forward.
     * @param newPosition - represents the position where the player wants to move;
     * @return true if the piece is moving two squares forward, false otherwise.
     */
    private boolean movesTwoForward(Position newPosition){
        if (colour == Colour.BLACK){
            return newPosition.getX() < position.getX() && Math.abs(newPosition.getX() - position.getX()) == 2;
        } else {
            return newPosition.getX() > position.getX() && Math.abs(newPosition.getX() - position.getX()) == 2;
        }
    }

    /**
     * This is a helper method, and it checks if the piece is moving one square forward.
     * @param newPosition - represents the position where the player wants to move;
     * @return true if the piece is moving one square forward, false otherwise.
     */
    private boolean movesOneForward(Position newPosition){
        if (colour == Colour.BLACK){
            return newPosition.getX() < position.getX() && Math.abs(newPosition.getX() - position.getX()) == 1;
        } else {
            return newPosition.getX() > position.getX() && Math.abs(newPosition.getX() - position.getX()) == 1;
        }
    }

    /**
     * This is a helper method, and it is used to retrieve a new piece (between "rook", "bishop", "knight" and "queen")
     * when the current piece reaches the last row.
     * @param input - represents the name of the piece that will replace the current pawn;
     * @param newPosition - represents the position where the player wants to move;
     * @return a new piece (with the same colour as the current pawn and its position is set to newPosition) that was
     * chosen by the player to replace the pawn.
     */
    private Piece getPiece(String input, Position newPosition){
        switch (input.trim().toLowerCase()){
            case "rook":
                return new Rook(newPosition, colour);
            case "bishop":
                return new Bishop(newPosition, colour);
            case "knight":
                return new Knight(newPosition, colour);
            case "queen":
                return new Queen(newPosition, colour);
        }
        return null;
    }

    /**
     * This method is a helper method, and it checks if newPosition is placed on the last row.
     * @param newPosition - represents the position where the player wants to move;
     * @return true if newPosition is placed on the last row, false otherwise.
     */

    public boolean isAtEndOfBoard(Position newPosition){
        return colour == Colour.BLACK ? newPosition.getX() == 0 : newPosition.getX()  == 7;
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
        return colour == Colour.WHITE ? "\u001B[37mP\u001B[0m" : "\u001B[30mP\u001B[0m";
    }
}
