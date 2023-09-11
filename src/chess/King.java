package chess;

import java.util.LinkedList;
import java.util.List;

public class King implements Piece {
    private Position position;
    private final Colour colour;
    private boolean wasMoved;

    public King(Position position, Colour colour) {
        this.position = position;
        this.colour = colour;
        wasMoved = false;
    }

    @Override
    public boolean moveTo(Position newPosition, Board board) {
        if (isValid(newPosition)){
            if (board.isEmptyCell(newPosition) || board.getPiece(newPosition) == null){
                wasMoved = true;
                //setPosition(newPosition);
                return true;
            }
        }
        return false;
    }

    private boolean isValid (Position newPosition){
        return !newPosition.equals(position) &&
                Math.abs(position.getX()-newPosition.getX()) <=1 &&
                Math.abs(position.getY()- newPosition.getY()) <=1;
    }

    public boolean isUnderCheck(Board board){
        List<Piece> pieces = getPiecesThatCheckedKing(board);
        if (pieces == null){
            return  false;
        }
        for (Piece piece : pieces){
            if (piece.moveTo(position, board)){
                return true;
            }
        }
        return false;
    }

    private List<Piece> getPiecesThatCheckedKing(Board board){
        List<Piece> pieces = new LinkedList<>();
        List<Position> extremes = getExtremePositionsRelatedToKing();

        if (extremes.isEmpty()){
            return null;
        }

        for (Position pos : extremes){
            if (board.isSameColumn(position, pos)){
                pieces.addAll(board.getPiecesBetween_ColumPositions(position, pos));
            }
            if (board.isSameRow(position, pos)){
                pieces.addAll(board.getPiecesBetween_RowPositions(position, pos));
            }
            if (board.isADiagPos(position, pos)){
                pieces.addAll(board.getPiecesBetween_DiagonalPositions(position, pos));
            }
        }
        return pieces;
    }

    private List<Position> getExtremePositionsRelatedToKing(){
        List<Position> positions = new LinkedList<>();
        positions.add(new Position(position.getX(), -1));
        positions.add(new Position(position.getX(), 8));
        positions.add(new Position(-1, position.getY()));
        positions.add(new Position(8, position.getY()));

        int x = position.getX();
        int y = position.getY();
        while (x - 1 >= 0 && y - 1 >= 0){
            x--;
            y--;
        }
        positions.add(new Position(x,y));

        x = position.getX();
        y = position.getY();
        while (x + 1 <= 7 && y + 1 <= 7){
            x++;
            y++;
        }
        positions.add(new Position(x,y));

        x = position.getX();
        y = position.getY();
        while (x + 1 <= 7 && y - 1 >= 0){
            x++;
            y--;
        }
        positions.add(new Position(x,y));

        x = position.getX();
        y = position.getY();
        while (x - 1 >= 0 && y + 1 <= 7){
            x--;
            y++;
        }
        positions.add(new Position(x,y));
        return positions;
    }

    public boolean wasMoved() {
        return wasMoved;
    }

    @Override
    public Colour getColour() {
        return this.colour;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return colour == Colour.WHITE ? "WK" : "BK";
    }
}
