package chess;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private final String name;
    private final Colour colour;
    private King king;
    private Rook leftRook;
    private Rook rightRook;
    private List<Piece> availablePieces;
    private LinkedList<Piece> removedPieces;

    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
        availablePieces = new LinkedList<>();
        removedPieces = new LinkedList<>();
    }

    public Position getPosition(){
        return Input.getInstance().getPosition();
    }

    public void addAvailablePiece(Piece piece){
        if (piece != null) {
            availablePieces.add(piece);
        }
    }
    public void addRemovedPiece(Piece piece){
        if (piece != null) {
            removedPieces.add(piece);
            availablePieces.remove(piece);
        }
    }
    public void undoRemovedPiece(){
        Piece piece = removedPieces.pollLast();
        if (piece != null) {
            addAvailablePiece(piece);
        }
    }

    public void resetListsOfPieces(){
        availablePieces = new LinkedList<>();
        removedPieces = new LinkedList<>();
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
