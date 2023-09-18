package chess;

import chess.helper.Colour;
import chess.helper.Position;
import chess.pieces.*;

public class Main {
    public static void main(String[] args) {
       /* Game game = new Game();
        game.play();*/

        Piece[][] customBoard = new Piece[8][8];
        customBoard[6][7] = new Pawn(new Position(6, 7), Colour.WHITE);
        customBoard[6][0] = new Rook(new Position(6, 0), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        Game game = new Game(customBoard);
        game.play();
    }
}
