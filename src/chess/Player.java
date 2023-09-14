package chess;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private final String name;
    private final Colour colour;
    private final King king;
    private final List<Piece> availablePieces;
    private final LinkedList<Piece> removedPieces;

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

    /*public boolean hasLost(Board board){
        List<Piece> opponentAttackers= king.piecesThatThreatensKing(board);
        if (opponentAttackers.size() >= 2){
            List<Position> surroundingPositions = board.getSurroundingPositions(king.getPosition());
            for (Position pos : surroundingPositions){
                if (!king.canMoveTo(pos,board)){
                    return false;
                }
            }
            return true;
        }

        if (opponentAttackers.size() == 1){

        }
    }*/



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
