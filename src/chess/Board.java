package chess;

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

   /* public List<Piece> getPiecesBetween_DiagonalPositions(Position initPosition, Position newPosition, boolean getFirst){

        List<Piece> pieces = new LinkedList<>();

        if (newPosition.getX() < initPosition.getX() && newPosition.getY() < initPosition.getY()){ //moving up-left
            for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                if (!isEmptyCell(new Position(initPosition.getX() - i, initPosition.getY() - i))){
                    pieces.add(getPiece(new Position(initPosition.getX() - i, initPosition.getY() - i)));
                    if (getFirst){
                        break;
                    }
                }
            }
        }
        if (newPosition.getX() > initPosition.getX() && newPosition.getY() > initPosition.getY()){ //moving down-right
            for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                if (!isEmptyCell(new Position(initPosition.getX() + i, initPosition.getY() + i))){
                    pieces.add(getPiece(new Position(initPosition.getX() + i, initPosition.getY() + i)));
                    if (getFirst){
                        break;
                    }
                }
            }
        }
        if (newPosition.getX() > initPosition.getX() && newPosition.getY() < initPosition.getY()){ //moving down-left
            for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                if (!isEmptyCell(new Position(initPosition.getX() + i, initPosition.getY() - i))){
                    pieces.add(getPiece(new Position(initPosition.getX() + i, initPosition.getY() - i)));
                    if (getFirst){
                        break;
                    }
                }
            }
        }
        if (newPosition.getX() < initPosition.getX() && newPosition.getY() > initPosition.getY()){ //moving up-right
            for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                if (!isEmptyCell(new Position(initPosition.getX() - i, initPosition.getY() + i))){
                    pieces.add(getPiece(new Position(initPosition.getX() - i, initPosition.getY() + i)));
                    if (getFirst){
                        break;
                    }
                }
            }
        }
        return pieces;
    }*/

    /*public List<Piece> getPiecesBetween_RowPositions(Position initPosition, Position newPosition, boolean getFirst){

        List<Piece> pieces = new LinkedList<>();

        if (newPosition.getY() < initPosition.getY()){
            for (int i = initPosition.getY()-1; i > newPosition.getY(); i--) {
                if (!isEmptyCell(new Position(initPosition.getX(), i))){
                    pieces.add(getPiece(new Position(initPosition.getX(), i)));
                    if (getFirst){
                        break;
                    }
                }
            }
        } else if (newPosition.getY() > initPosition.getY()){
            for (int i = initPosition.getY()+1; i < newPosition.getY(); i++) {
                if (!isEmptyCell(new Position(initPosition.getX(), i))){
                    pieces.add(getPiece(new Position(initPosition.getX(), i)));
                    if (getFirst){
                        break;
                    }
                }
            }
        }
        return pieces;
    }*/

    public boolean hasPiecesBetween(Position initPosition, Position newPosition){
        List<Piece> pieces = getPiecesBetween(initPosition, newPosition, Colour.WHITE);
        pieces.addAll(getPiecesBetween(initPosition, newPosition, Colour.BLACK));
        return pieces.size()>0;
    }

    public List<Piece> getPiecesBetween(Position initPosition, Position newPosition, Colour playerColour){

        List<Piece> pieces = new LinkedList<>();
        List<Position> positionsBetween = getPositionsBetween(initPosition, newPosition);
        for (Position pos : positionsBetween){
            Piece piece = getPiece(pos);
            if (piece != null && piece.getColour() != playerColour) {
                pieces.add(piece);
            }
        }
        return pieces;
    }

    /*public List<Piece> getPiecesBetween_ColumPositions(Position initPosition, Position newPosition, boolean getFirst){

        List<Piece> pieces = new LinkedList<>();

            if (newPosition.getX() < initPosition.getX()){
                for (int i = initPosition.getX()-1; i > newPosition.getX(); i--) {
                    if (!isEmptyCell(new Position(i, initPosition.getY()))){
                        pieces.add(getPiece(new Position(i, initPosition.getY())));
                        if (getFirst){
                            break;
                        }
                    }
                }
            } else if (newPosition.getX() > initPosition.getX()){
                for (int i = initPosition.getX()+1; i < newPosition.getX(); i++) {
                    if (!isEmptyCell(new Position(i, initPosition.getY()))){
                        pieces.add(getPiece(new Position(i, initPosition.getY())));
                        if (getFirst){
                            break;
                        }
                    }
                }
            }
        return pieces;
    }*/


   /* public List<Piece> getPiecesThatCanGoTo(Position position){
        List<Piece> pieces = new LinkedList<>();
        List<Position> extremes = getExtremitiesOfPosition(position);

        if (extremes.isEmpty()){
            return null;
        }

        for (Position pos : extremes){
            if (isSameColumn(position, pos)){
                pieces.addAll(getPiecesBetween_ColumPositions(position, pos));
            }
            if (isSameRow(position, pos)){
                pieces.addAll(getPiecesBetween_RowPositions(position, pos));
            }
            if (isADiagPos(position, pos)){
                pieces.addAll(getPiecesBetween_DiagonalPositions(position, pos));
            }
        }
        return pieces;
    }*/

    /*private List<Position> getExtremitiesOfPosition(Position position){
        List<Position> positions = new LinkedList<>();
        positions.add(new Position(position.getX(), -1));
        positions.add(new Position(position.getX(), 8));
        positions.add(new Position(-1, position.getY()));
        positions.add(new Position(8, position.getY()));

        int x = position.getX();
        int y = position.getY();
        while (x >= 0 && y >= 0){
            x--;
            y--;
        }
        positions.add(new Position(x,y));

        x = position.getX();
        y = position.getY();
        while (x <= 7 && y <= 7){
            x++;
            y++;
        }
        positions.add(new Position(x,y));

        x = position.getX();
        y = position.getY();
        while (x <= 7 && y >= 0){
            x++;
            y--;
        }
        positions.add(new Position(x,y));

        x = position.getX();
        y = position.getY();
        while (x >= 0 && y <= 7){
            x--;
            y++;
        }
        positions.add(new Position(x,y));
        return positions;
    }*/

    public boolean isSameRow(Position initPosition, Position newPosition){
        return initPosition.getX() == newPosition.getX();
    }

    public boolean isSameColumn(Position initPosition, Position newPosition){
        return initPosition.getY() == newPosition.getY();
    }

    public boolean isADiagPos(Position initPosition, Position newPosition){
        return Math.abs(initPosition.getX() - newPosition.getX()) == Math.abs(initPosition.getY() - newPosition.getY());
    }
    public void allocatePieces(Player player1, Player player2){
        for (Piece[] pieces : board) {
            for (Piece piece : pieces) {
                if (piece != null) {
                    if (piece.getColour() == player1.getColour()) {
                        player1.addAvailablePiece(piece);
                    } else {
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

    /*public List<Position> getFreePositions(){
        List<Position> freePositions = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null){
                    freePositions.add(new Position(i,j));
                }
            }
        }
        return freePositions;
    }*/
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
        return surroundingPositions;
    }

    public List<Position> getPositionsBetween(Position from, Position to){
        List<Position> positions = new LinkedList<>();

        if (isSameRow(from, to)){
            positions = getPositionsBetween_Row(from, to);
        }

        if (isSameColumn(from, to)){
            positions = getPositionsBetween_Column(from, to);
        }

        if (isADiagPos(from, to)){
            positions = getPositionsBetween_Diagonal(from, to);
        }
        return positions;
    }

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

    public Piece[][] getBoard() {
        return board;
    }

    public boolean isEmptyCell (Position position){
        return board[position.getX()][position.getY()] == null;
    }
    public void updateBoard(Position oldPosition, Piece piece){
        board[oldPosition.getX()][oldPosition.getY()] = null;
        board[piece.getPosition().getX()][piece.getPosition().getY()] = piece;
        System.out.println(this);
    }

    public void update(Position oldPosition, Position newPosition, Player opponent){
        Piece pieceToMove = getPiece(oldPosition);
        Piece removedPiece = getPiece(newPosition);
        board[oldPosition.getX()][oldPosition.getY()] = null;
        board[newPosition.getX()][newPosition.getY()] = pieceToMove;
        opponent.addRemovedPiece(removedPiece);
        System.out.println(this);
    }

    public void undo(Position oldPosition, Position newPosition, Piece pieceToMove, Piece removedPiece, Player opponent){
        board[oldPosition.getX()][oldPosition.getY()] = pieceToMove;
        pieceToMove.setPosition(oldPosition);
        board[newPosition.getX()][newPosition.getY()] = removedPiece;
        opponent.undoRemovedPiece();
        System.out.println(this);
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
