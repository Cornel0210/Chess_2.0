package chess;

public class Game {
    private static Game INSTANCE;
    private final Board board;
    private final Player player1;
    private final Player player2;
    private boolean isWhite;
    private boolean isEnd;

    public Game() {
        board = new Board();
        player1 = new Player("player1", Colour.WHITE, board.getPiece(new Position(0,3)));
        player2 = new Player("player2", Colour.BLACK, board.getPiece(new Position(7,3)));
        isWhite = true;
        board.allocatePieces(player1, player2);
    }

    public void play(){
        Player current;
        Player opponent;
        System.out.println(board);
        while (!isEnd){
            current = isWhite ? player1 : player2;
            opponent = isWhite ? player2 : player1;
            System.out.println(current.getName() + " has to move");
            Position from = current.getPosition();
            Position to = current.getPosition();
            while (movePiece(from, to, current, opponent)){
                from = current.getPosition();
                to = current.getPosition();
            }
            isWhite = !isWhite;
        }
    }

    public boolean movePiece(Position oldPosition, Position newPosition, Player player, Player opponent){
        Piece pieceToMove = board.getPiece(oldPosition);
        Piece removedPiece = board.getPiece(newPosition);

        if (!pieceToMove.canMoveTo(newPosition, board, player)) {
            System.out.println("You cannot perform that move.");
            return false;
        } else {
            pieceToMove.moveTo(newPosition, board);
            opponent.addRemovedPiece(removedPiece);
        }
        King opponentKing = opponent.getKing();
        if (opponentKing.isInCheckMate(board)){
            isEnd = true;
        }
        return true;
    }

    public Player getOpponent (Colour colour){
        return player1.getColour() == colour ? player2 : player1;
    }

    public Player getPlayer(Colour colour){
        return player1.getColour() == colour ? player1 : player2;
    }

    public void setCustomBoard(Piece[][] customBoard){
        board.resetBoard(customBoard, player1, player2);
        isEnd = false;
    }
    public void resetBoard(){
        isEnd = false;
        board.resetBoard(player1, player2);
    }

    public boolean isEnd() {
        return isEnd;
    }

    public static Game getInstance() {
        if (INSTANCE == null){
            INSTANCE = new Game();
        }
        return INSTANCE;
    }
}
