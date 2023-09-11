package chess;

import java.util.Scanner;

public class Player {
    private final String name;
    private final Colour colour;
    private final King king;

    public Player(String name, Colour colour, King king) {
        this.name = name;
        this.colour = colour;
        this.king = king;
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
