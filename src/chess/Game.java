package chess;

public class Game {
    private static Game INSTANCE;
    private final Board board;
    private final Player player1;
    private final Player player2;
    private boolean isWhite;

    public Game() {
        board = new Board();
        player1 = new Player("nickname1", Colour.WHITE, board.getPiece(new Position(0,3)));
        player2 = new Player("nickname2", Colour.BLACK, board.getPiece(new Position(7,3)));
        isWhite = true;
        board.allocatePieces(player1, player2);
    }

    public void play(){
        Player current;
        System.out.println(board);
        while (true){
            current = isWhite ? player1 : player2;
            System.out.println(current.getName() + " has to move");
            Position from = current.move();
            Position to = current.move();
            while (!movePiece(from, to, current)){
                from = current.move();
                to = current.move();
            }
            isWhite = !isWhite;
        }
    }


    private boolean movePiece(Position oldPosition, Position newPosition, Player player){
        Piece pieceToMove = board.getPiece(oldPosition);
        Piece pieceRemoved = board.getPiece(newPosition);
        if (pieceToMove != null && pieceToMove.getColour() == player.getColour() && pieceToMove.moveTo(newPosition, board)){
            board.updateBoard(oldPosition, newPosition, pieceToMove);
            int attackersNr = player.getKing().piecesThatThreatensKing(board, getOpponent(player.getColour()).getAvailablePieces());


            System.out.println("----------------------------");
            System.out.println(board);
            return true;
        } else {
            System.out.println("can t move");
        }
        return false;
    }

    public static Game getInstance() {
        if (INSTANCE == null){
            INSTANCE = new Game();
        }
        return INSTANCE;
    }
    public Player getOpponent (Colour colour){
        return player1.getColour() == colour ? player2 : player1;
    }
}
