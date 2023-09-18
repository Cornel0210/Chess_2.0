package chess;

import chess.helper.Colour;
import chess.helper.Position;
import chess.pieces.*;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private Piece[][] board = new Piece[8][8];

    public Board() {
        initializeBoard();
    }
    public Board(Piece[][] board) {
        this.board = board;
    }

    /**
     * This method is used to load the chess board. It places the pieces on their positions.
     */
    private void initializeBoard(){
        for (int j = 0; j < board.length; j++) {
            board[1][j] = new Pawn(new Position(1, j), Colour.WHITE);
            board[6][j] = new Pawn(new Position(6, j), Colour.BLACK);
        }
        board[0][0] = new Rook(new Position(0,0), Colour.WHITE);
        board[0][7] = new Rook(new Position(0,7), Colour.WHITE);
        board[7][7] = new Rook(new Position(7,7), Colour.BLACK);
        board[7][0] = new Rook(new Position(7,0), Colour.BLACK);

        board[0][1] = new Knight(new Position(0,1), Colour.WHITE);
        board[0][6] = new Knight(new Position(0,6), Colour.WHITE);
        board[7][1] = new Knight(new Position(7,1), Colour.BLACK);
        board[7][6] = new Knight(new Position(7,6), Colour.BLACK);

        board[0][2] = new Bishop(new Position(0,2), Colour.WHITE);
        board[0][5] = new Bishop(new Position(0,5), Colour.WHITE);
        board[7][2] = new Bishop(new Position(7,2), Colour.BLACK);
        board[7][5] = new Bishop(new Position(7,5), Colour.BLACK);

        board[0][3] = new King(new Position(0,3), Colour.WHITE);
        board[7][3] = new King(new Position(7,3), Colour.BLACK);

        board[0][4] = new Queen(new Position(0,4), Colour.WHITE);
        board[7][4] = new Queen(new Position(7,4), Colour.BLACK);
    }

    /**
     * This method checks if there are pieces between initPosition and newPosition.
     * @return true if there is at least one piece between the two given positions, false otherwise.
     */
    public boolean hasPiecesBetween(Position initPosition, Position newPosition){
        List<Piece> pieces = getPiecesBetween(initPosition, newPosition);
        pieces.addAll(getPiecesBetween(initPosition, newPosition));
        return pieces.size()>0;
    }

    /**
     * This method checks if there are pieces between initPosition and newPosition. If true, the piece is
     * stored to a new list.
     * @return the list with the pieces between the two given positions.
     */
    public List<Piece> getPiecesBetween(Position initPosition, Position newPosition){

        List<Piece> pieces = new LinkedList<>();
        List<Position> positionsBetween = getPositionsBetween(initPosition, newPosition);
        for (Position pos : positionsBetween){
            Piece piece = getPiece(pos);
            if (piece != null) {
                pieces.add(piece);
            }
        }
        return pieces;
    }

    /**
     * This method retrieves the positions from the board that are next to the king's position (on same row/column/diagonal,
     * but not farther than one square).
     * @param kingPos - represents the king's position.
     * @return a new list with all positions that surround the king's current position and which are on the board.
     */
    public List<Position> getSurroundingPositions(Position kingPos){
        List<Position> surroundingPositions = new LinkedList<>();
        for (int i = kingPos.getX()-1; i <= kingPos.getX()+1; i++) {
            for (int j = kingPos.getY()-1; j <= kingPos.getY()+1; j++) {
                Position pos = new Position(i,j);
                if (pos.isOnBoard()){
                    surroundingPositions.add(pos);
                }
            }
        }
        surroundingPositions.remove(kingPos);
        return surroundingPositions;
    }

    /**
     * This method retrieves the squares between two given positions. It also checks if the positions are on the same
     * row, column or diagonal.
     * @return a new list with the positions between two given squares.
     *      null if there are no positions between the two given squares or if the two positions are not on the same
     *      row/column/diagonal.
     */
    public List<Position> getPositionsBetween(Position from, Position to){
        List<Position> positions = new LinkedList<>();

        if (isSameRow(from, to)){
            positions = getPositionsBetween_Row(from, to);
        }

        if (isSameColumn(from, to)){
            positions = getPositionsBetween_Column(from, to);
        }

        if (isADiagonalPos(from, to)){
            positions = getPositionsBetween_Diagonal(from, to);
        }
        return positions;
    }

    /**
     * This method retrieves the positions between two given squares that are on the same row.
     * @return a new list with the positions between two given squares.
     *      null if there are no positions between the two given squares.
     */
    private List<Position> getPositionsBetween_Row(Position from, Position to){
        List<Position> positions = new LinkedList<>();
        for (int i = from.getY()+1; i < to.getY(); i++) {
            positions.add(new Position(from.getX(), i));
        }
        for (int i = from.getY()-1; i > to.getY(); i--) {
            positions.add(new Position(from.getX(), i));
        }
        return positions;
    }

    /**
     * This method retrieves the positions between two given squares that are on the same column.
     * @return a new list with the positions between two given squares.
     *      null if there are no positions between the two given squares.
     */
    private List<Position> getPositionsBetween_Column(Position from, Position to){
        List<Position> positions = new LinkedList<>();
        for (int i = from.getX()+1; i < to.getX(); i++) {
            positions.add(new Position(i, from.getY()));
        }
        for (int i = from.getX()-1; i > to.getX(); i--) {
            positions.add(new Position(i, from.getY()));
        }
        return positions;
    }

    /**
     * This method retrieves the positions between two given squares that are on the same diagonal.
     * @return a new list with the positions between two given squares.
     *      null if there are no positions between the two given squares.
     */
    private List<Position> getPositionsBetween_Diagonal(Position from, Position to){
        List<Position> positions = new LinkedList<>();

        int i = 1;
        while (from.getX()-i > to.getX() && from.getY()-i > to.getY()){
            Position tempPos = new Position(from.getX()-i, from.getY()-i);
            positions.add(tempPos);
            i++;
        }

        i = 1;
        while (from.getX()+i < to.getX() && from.getY()-i > to.getY()){
            Position tempPos = new Position(from.getX()+i, from.getY()-i);
            positions.add(tempPos);
            i++;
        }

        i = 1;
        while (from.getX()-i > to.getX() && from.getY()+i < to.getY()){
            Position tempPos = new Position(from.getX()-i, from.getY()+i);
            positions.add(tempPos);
            i++;
        }

        i = 1;
        while (from.getX()+i < to.getX() && from.getY()+i < to.getY()){
            Position tempPos = new Position(from.getX()+i, from.getY()+i);
            positions.add(tempPos);
            i++;
        }

        return positions;
    }

    /**
     * This method checks if two positions are on the same row.
     * @return true if positions are on the same row, false otherwise.
     */
    public boolean isSameRow(Position initPosition, Position newPosition){
        return initPosition.getX() == newPosition.getX();
    }

    /**
     * This method checks if two positions are on the same column.
     * @return true if positions are on the same column, false otherwise.
     */
    public boolean isSameColumn(Position initPosition, Position newPosition){
        return initPosition.getY() == newPosition.getY();
    }

    /**
     * This method checks if two positions are on the same diagonal.
     * @return true if positions are on the same diagonal, false otherwise.
     */
    public boolean isADiagonalPos(Position initPosition, Position newPosition){
        return Math.abs(initPosition.getX() - newPosition.getX()) == Math.abs(initPosition.getY() - newPosition.getY());
    }

    /**
     * This is a helper method that allocates the pieces to corresponding player.
     */
    public void allocatePieces(Player player1, Player player2){

        for (Piece[] pieces : board) {
            for (Piece piece : pieces) {
                if (piece != null) {
                    if (piece.getColour() == player1.getColour()) {
                        if (piece instanceof King){
                            player1.setKing((King)piece);
                        }
                        if (piece instanceof Rook && piece.getPosition().getY() == 0){
                            player1.setLeftRook((Rook) piece);
                        }
                        if (piece instanceof Rook && piece.getPosition().getY() == 7){
                            player1.setRightRook((Rook) piece);
                        }
                        player1.addAvailablePiece(piece);
                    } else {
                        if (piece instanceof King){
                            player2.setKing((King)piece);
                        }
                        if (piece instanceof Rook && piece.getPosition().getY() == 0){
                            player2.setLeftRook((Rook) piece);
                        }
                        if (piece instanceof Rook && piece.getPosition().getY() == 7){
                            player2.setRightRook((Rook) piece);
                        }
                        player2.addAvailablePiece(piece);
                    }
                }
            }
        }

        player1.getAvailablePieces().remove(player1.getKing());
        player2.getAvailablePieces().remove(player2.getKing());
    }

    public Piece getPiece(Position position){
        return board[position.getX()][position.getY()];
    }
    public Piece[][] getChessBoard() {
        return board;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" |");
        for (int i = 0; i < board.length; i++) {
            stringBuilder.append(i).append("|");
        }
        stringBuilder.append("\n");
        for (int i = 0; i < board.length; i++) {
            stringBuilder.append(i).append("|");
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null){
                    stringBuilder.append(" |");
                } else {
                    stringBuilder.append(board[i][j]).append("|");
                }
            }
            stringBuilder.append(i).append("|").append("\n");
        }

        stringBuilder.append(" |");
        for (int i = 0; i < board.length; i++) {
            stringBuilder.append(i).append("|");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
