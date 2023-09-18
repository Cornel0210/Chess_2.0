package chess;

import chess.helper.Colour;
import chess.helper.Input;
import chess.helper.Position;
import chess.pieces.King;
import chess.pieces.Piece;
import chess.pieces.Rook;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private final String name;
    private final Colour colour;
    private King king;
    private Rook leftRook;
    private Rook rightRook;
    private final List<Piece> availablePieces; //represents the pieces that haven't been yet removed by the opponent.
    private final LinkedList<Piece> removedPieces; //represents pieces removed by the player.

    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
        availablePieces = new LinkedList<>();
        removedPieces = new LinkedList<>();
    }

    /**
     * This method is used to retrieve a position when moving a piece.
     * @return a new position.
     */
    public Position getPosition(){
        return Input.getInstance().getPosition();
    }

    /**
     * This method is used when the pieces are allocated. It adds a piece to the availablePieces list.
     * @param piece - represents the piece that will be added to availablePiece.
     */
    public void addAvailablePiece(Piece piece){
        if (piece != null) {
            availablePieces.add(piece);
        }
    }

    /**
     * This method is used to add a removed piece to the removedPieces list.
     * @param piece - represents the piece that was removed by the opponent.
     */
    public void addRemovedPiece(Piece piece){
        if (piece != null) {
            removedPieces.add(piece);
            availablePieces.remove(piece);
        }
    }

    /**
     * This is a helper method for "canMoveTo". If an opponent's piece is removed and the king gets in check,
     * the move will be rolled back.
     */
    public void undoRemovedPiece(){
        Piece piece = removedPieces.pollLast();
        addAvailablePiece(piece);
    }

    public String getName() {
        return name;
    }

    public Colour getColour() {
        return colour;
    }

    public void setKing(King king) {
        this.king = king;
    }

    public void setLeftRook(Rook leftRook) {
        this.leftRook = leftRook;
    }

    public void setRightRook(Rook rightRook) {
        this.rightRook = rightRook;
    }

    public King getKing() {
        return king;
    }

    public Rook getLeftRook() {
        return leftRook;
    }

    public Rook getRightRook() {
        return rightRook;
    }
    public List<Piece> getAvailablePieces() {
        return availablePieces;
    }

    public LinkedList<Piece> getRemovedPieces() {
        return removedPieces;
    }
}
