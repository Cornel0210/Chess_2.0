package chess;

public class Game {
    Board board = new Board();

    public void movePiece(Position oldPosition, Position newPosition){
        if (oldPosition.isValid()){
            Piece piece = board.getPiece(oldPosition);
            if (piece != null && piece.moveTo(newPosition, board)){
                board.updateBoard(oldPosition, piece);
                System.out.println("----------------------------");
                System.out.println(board);
            } else {
                System.out.println("can t move");
            }
        }
    }
}
