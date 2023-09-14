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


    public boolean movePiece(Position oldPosition, Position newPosition, Player player){
        Piece pieceToMove = board.getPiece(oldPosition);
        Piece removedPiece = board.getPiece(newPosition);
        Player opponent = getOpponent(player.getColour());
        boolean flag = false;

        if (pieceBelongsToPlayer(pieceToMove, player)){
             flag = pieceToMove.moveTo(newPosition, board);
            board.update(oldPosition, newPosition, opponent);
        }
        if (player.getKing().isChecked(board)){
            board.undo(oldPosition, newPosition, pieceToMove, removedPiece, opponent);
            flag = false;
        }
        return flag;
    }

    private boolean pieceBelongsToPlayer(Piece piece, Player player){
        if (piece != null){
            return piece.getColour() == player.getColour();
        }
        return false;
    }

    public Player getOpponent (Colour colour){
        return player1.getColour() == colour ? player2 : player1;
    }

    public Player getPlayer(Colour colour){
        return player1.getColour() == colour ? player1 : player2;
    }

    public static Game getInstance() {
        if (INSTANCE == null){
            INSTANCE = new Game();
        }
        return INSTANCE;
    }
}
