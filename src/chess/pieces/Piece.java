package chess.pieces;

import chess.Board;
import chess.helper.Colour;
import chess.Player;
import chess.helper.Position;

/**
 * Piece interface declares the expected behavior of each piece.
 * The concrete behaviour will be defined in the classes that implement this interface.
 */


public interface Piece {

    /**
     * This method should implement the behavior that must be followed to move the chosen piece to a new position.
     * Inside this method, the following elements will be updated: the board, the opponent player's list with lost pieces
     * and also a field that checks whether a king or a rook was moved.
     * @param newPosition - represents the position where a chosen piece will be moved;
     * @param board - represents the chess board;
     * @param currentPlayer - represents the player that is currently moving;
     * @param opponent - represents the opponent of the current player;
     */
    void moveTo(Position newPosition, Board board, Player currentPlayer, Player opponent);

    /**
     * This method should define the process that checks if a chosen piece can be moved to a desired position.
     * Inside this method there will be defined an algorithm that checks if all chess rules are followed.
     * The method will be customized in accordance with piece's type.
     * @param newPosition - represents the position where the current player wants to move his piece;
     * @param board - represents the chess board;
     * @param currentPlayer - represents the player that is currently moving;
     * @param opponent - represents the opponent of the current player;
     * @return - true if are rules are respected, false otherwise.
     */
    boolean canMoveTo(Position newPosition, Board board, Player currentPlayer, Player opponent);
    Colour getColour();
    void setPosition(Position position);
    Position getPosition();

    /**
     * This method is used in classes that implement the interface which determines whether a chosen piece will be moved
     * to an empty spot or if it is going to take a piece from the opponent player.
     * @param position - represents the position where the chosen piece will be moved;
     * @param board - represents the chess board;
     * @param colour - represents the piece's colour;
     * @return true if at the inserted position there is an empty spot or there is a piece that belongs to the opponent.
     */

    default boolean isValid(Position position, Board board, Colour colour){
        Piece piece = board.getPiece(position);
        return piece == null || piece.getColour() != colour;
    }
}
