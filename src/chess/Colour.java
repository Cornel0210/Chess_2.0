package chess;

public enum Colour {
    BLACK, WHITE;

    public Colour getOpponentColour(){
        return this == WHITE ? BLACK : WHITE;
    }
}
