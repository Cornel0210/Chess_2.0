package chess;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private final String name;
    private final Colour colour;
    private final King king;
    private List<Piece> availablePieces;
    private LinkedList<Piece> removedPieces;

    public Player(String name, Colour colour, Piece king) {
        this.name = name;
        this.colour = colour;
        this.king = (King) king;
        availablePieces = new LinkedList<>();
        removedPieces = new LinkedList<>();
    }

    public Position move(){
        int x = getInt();
        int y = getInt();
        Position pos = new Position(x,y);
        while (!pos.isOnBoard()){
            x = getInt();
            y = getInt();
            pos = new Position(x,y);
        }
        return pos;
    }


    private int getInt(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            int i = scanner.nextInt();
            scanner.nextLine();
            return i;
        }
        return -1;
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
    public List<Piece> getAvailablePieces() {
        return availablePieces;
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

    public King getKing() {
        return king;
    }
}
