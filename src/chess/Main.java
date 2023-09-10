package chess;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.movePiece(new Position(1,1), new Position(3,1));
        game.movePiece(new Position(3,1), new Position(5,1));
        game.movePiece(new Position(3,1), new Position(2,1));
        game.movePiece(new Position(1,1), new Position(3,1));
        game.movePiece(new Position(1,1), new Position(3,1));
    }
}
