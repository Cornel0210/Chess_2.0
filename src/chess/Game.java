package chess;

import java.util.List;

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
            while (!movePiece(from, to, current, opponent)){
                from = current.getPosition();
                to = current.getPosition();
            }
            isWhite = !isWhite;
            System.out.println(board);
        }
    }

    public boolean movePiece(Position oldPosition, Position newPosition, Player currentPlayer, Player opponent){
        Piece pieceToMove = board.getPiece(oldPosition);
        if (pieceToMove == null){
            System.out.println("Choose an existing piece!");
            return false;
        }

        if (!pieceToMove.canMoveTo(newPosition, board, currentPlayer, opponent)) {
            System.out.println("You cannot perform that move.");
            return false;
        }
        pieceToMove.moveTo(newPosition, board, currentPlayer, opponent);
        King opponentKing = opponent.getKing();
        if (opponentKing.isInCheckMate(board, currentPlayer, opponent)){
            isEnd = true;
            System.out.println(currentPlayer.getName() + " has won! Congratulations!");
        } else {
            if (isDraw(board, currentPlayer, opponent)){
                isEnd = true;
                System.out.println("Draw");
            }
        }
        return true;
    }

    private boolean isDraw(Board board, Player currentPlayer, Player opponent){
        boolean draw = false;
        if (opponent.getAvailablePieces().isEmpty()){
            draw = true;
            King opponentKing = opponent.getKing();
            List<Position> surroundingPos = board.getSurroundingPositions(opponentKing.getPosition());
            if (!opponentKing.isChecked(board, opponent, currentPlayer)){
                for (Position pos : surroundingPos){
                    if (opponentKing.canMoveTo(pos, board, opponent, currentPlayer)){
                        draw = false;
                    }
                }
            }
        }
        return draw;
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
