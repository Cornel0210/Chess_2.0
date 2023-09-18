package chess;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private boolean isWhite;
    private boolean isEnd;

    public Game() {
        board = new Board();
        player1 = new Player("player1", Colour.WHITE);
        player2 = new Player("player2", Colour.BLACK);
        isWhite = true;
        board.allocatePieces(player1, player2);
    }
    public Game(Piece[][] chessBoard) {
        board = new Board(chessBoard);
        player1 = new Player("player1", Colour.WHITE);
        player2 = new Player("player2", Colour.BLACK);
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

    public boolean movePiece(Position oldPosition, Position newPosition, Player currentPlayer, Player opponent){
        Piece pieceToMove = board.getPiece(oldPosition);

        if (!pieceToMove.canMoveTo(newPosition, board, currentPlayer, opponent)) {
            System.out.println("You cannot perform that move.");
            return false;
        }
        pieceToMove.moveTo(newPosition, board, currentPlayer, opponent);
        King opponentKing = opponent.getKing();
        if (opponentKing.isInCheckMate(board)){
            isEnd = true;
        }
        return true;
    }

    public Player getPlayer(Colour colour){
        return player1.getColour() == colour ? player1 : player2;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void printBoard(){
        System.out.println(board);
    }
}
