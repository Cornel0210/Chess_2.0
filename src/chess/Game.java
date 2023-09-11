package chess;

public class Game {
    private final Board board = new Board();
    private final Player player1 = new Player("nickname1", Colour.WHITE, (King) board.getPiece(new Position(0,3)));
    private final Player player2 = new Player("nickname2", Colour.BLACK, (King) board.getPiece(new Position(7,3)));
    private boolean isWhite = true;

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
        Piece piece = board.getPiece(oldPosition);
        if (piece != null && piece.getColour() == player.getColour() && piece.moveTo(newPosition, board)){
            board.updateBoard(oldPosition, newPosition, piece);
            if (player.getKing().isUnderCheck(board)){

            }


            System.out.println("----------------------------");
            System.out.println(board);
            return true;
        } else {
            System.out.println("can t move");
        }
        return false;
    }

}
