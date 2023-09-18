package chess.helper;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Input class is used as a singleton in order to have only one instance that can be used for reading inputs from the
 * keyboard.
 * Its role is to retrieve valid positions(a position is considered valid if it is on the board
 * (its coordinates are between [0-7])), and also the name of the pieces that the pawns will be changed into when they
 * reach the last row.
 */


public class Input {
    private static Input INSTANCE;
    private final Scanner scanner;

    private Input() {
        this.scanner = new Scanner(System.in);
    }

    public Position getPosition(){
        int x, y;
        System.out.println("Insert two values between [0-7]");
        x = getInt();
        y = getInt();
        return new Position(x,y);
    }

    private int getInt(){
        int input = -1;
        while (input < 0 || input > 7){
            while (scanner.hasNextLine()){
                try {
                    input = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (InputMismatchException | NumberFormatException e2){
                    System.err.println("Insert a value between [0-7]");
                }
            }
        }
        return input;
    }

    public String getShiftedPiece(){
        System.out.println("You have to shift the pawn with a new piece.\n" +
                "Choose a piece between \"queen\", \"bishop\", \"rook\", \"knight\"");
        List<String> possibilities = Arrays.asList("queen", "bishop", "rook", "knight");
        String input = "";
        while (!possibilities.contains(input.trim().toLowerCase())){
            while (scanner.hasNextLine()){
                try {
                    input = scanner.nextLine();
                    break;
                } catch (InputMismatchException e){
                    System.err.println("Try again!");
                }
            }
        }

        return input;
    }

    public static Input getInstance() {
        if (INSTANCE == null){
            INSTANCE = new Input();
        }
        return INSTANCE;
    }
}
