package chess;

public interface Piece {
    boolean moveTo(Position newPosition, Board board);
    Colour getColour();
    void setPosition(Position position);
    Position getPosition();
    
    default boolean isCleanDiag(Position initPosition, Position newPosition, Board board){
        
        if (isADiagPos(initPosition, newPosition)){
            if (newPosition.getX() < initPosition.getX() && newPosition.getY() < initPosition.getY()){ //moving up-left
                for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                    if (!board.isEmptyCell(new Position(initPosition.getX() - i, initPosition.getY() - i))){
                        return false;
                    }
                }
                return true;
            }
            if (newPosition.getX() > initPosition.getX() && newPosition.getY() > initPosition.getY()){ //moving down-right
                for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                    if (!board.isEmptyCell(new Position(initPosition.getX() + i, initPosition.getY() + i))){
                        return false;
                    }
                }
                return true;
            }
            if (newPosition.getX() > initPosition.getX() && newPosition.getY() < initPosition.getY()){ //moving down-left
                for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                    if (!board.isEmptyCell(new Position(initPosition.getX() + i, initPosition.getY() - i))){
                        return false;
                    }
                }
                return true;
            }
            if (newPosition.getX() < initPosition.getX() && newPosition.getY() > initPosition.getY()){ //moving up-right
                for (int i = 1; i < Math.abs(initPosition.getX() - newPosition.getX()); i++) {
                    if (!board.isEmptyCell(new Position(initPosition.getX() - i, initPosition.getY() + i))){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    default boolean isCleanRow(Position initPosition, Position newPosition, Board board){

        if (isSameRow(initPosition, newPosition)){
            if (newPosition.getY() < initPosition.getY()){
                for (int i = initPosition.getY()-1; i > newPosition.getY(); i--) {
                    if (!board.isEmptyCell(new Position(initPosition.getX(), i))){
                        return false;
                    }
                }
            } else if (newPosition.getY() > initPosition.getY()){
                for (int i = initPosition.getY()+1; i < newPosition.getY(); i++) {
                    if (!board.isEmptyCell(new Position(initPosition.getX(), i))){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    default boolean isCleanColumn(Position initPosition, Position newPosition, Board board){

        if (isSameColumn(initPosition, newPosition)){
            if (newPosition.getX() < initPosition.getX()){
                for (int i = initPosition.getX()-1; i > newPosition.getX(); i--) {
                    if (!board.isEmptyCell(new Position(i, initPosition.getY()))){
                        return false;
                    }
                }
            } else if (newPosition.getX() > initPosition.getX()){
                for (int i = initPosition.getX()+1; i < newPosition.getX(); i++) {
                    if (!board.isEmptyCell(new Position(i, initPosition.getY()))){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    default boolean isSameRow(Position initPosition, Position newPosition){
        return initPosition.getX() == newPosition.getX();
    }

    default boolean isSameColumn(Position initPosition, Position newPosition){
        return initPosition.getY() == newPosition.getY();
    }

    default boolean isADiagPos(Position initPosition, Position newPosition){
        return Math.abs(initPosition.getX() - newPosition.getX()) == Math.abs(initPosition.getY() - newPosition.getY());
    }
    
}
