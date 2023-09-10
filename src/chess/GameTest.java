package chess;

import static org.junit.Assert.*;

public class GameTest {
    Board board;
    Position initPos;
    Piece piece;

    /**
     * The tests below are checking if moving a black pawn respects all the chess rules.
     */

    @org.junit.Before
    public void initBoard(){
        board = new Board();
    }

    @org.junit.Test
    public void movePiece1() {
        initPos = new Position(1,1);
        piece = board.getPiece(initPos);
        assertTrue(piece.moveTo(new Position(2,1), board));
    }

    @org.junit.Test
    public void movePiece2() {
        initPos = new Position(1,1);
        piece = board.getPiece(initPos);
        assertFalse(piece.moveTo(new Position(2,0), board));
    }
    @org.junit.Test
    public void movePiece3() {
        initPos = new Position(1,1);
        piece = board.getPiece(initPos);
        assertFalse(piece.moveTo(new Position(2,2), board));
    }
    @org.junit.Test
    public void movePiece4() {
        initPos = new Position(1,1);
        piece = board.getPiece(initPos);
        assertTrue(piece.moveTo(new Position(3,1), board));
    }
    @org.junit.Test
    public void movePiece5() {
        initPos = new Position(1,1);
        piece = board.getPiece(initPos);
        assertFalse(piece.moveTo(new Position(3,2), board));
    }
    @org.junit.Test
    public void movePiece6() {
        initPos = new Position(1,1);
        piece = board.getPiece(initPos);
        assertFalse(piece.moveTo(new Position(3,0), board));
    }
    @org.junit.Test
    public void movePiece7() {
        initPos = new Position(1,1);
        piece = board.getPiece(initPos);
        assertFalse(piece.moveTo(new Position(0,1), board));
    }

    static Board boardS;
    static Piece pieceS;

    @org.junit.BeforeClass
    public static void initStaticBoard(){
        boardS = new Board();
    }

    @org.junit.Test
    public void moveStaticPiece1() { //move 2 squares forward; no obstacle
        initPos = new Position(1,1);
        pieceS = boardS.getPiece(initPos);
        assertTrue(pieceS.moveTo(new Position(3,1), boardS));
        boardS.updateBoard(initPos, pieceS);
    }

    @org.junit.Test
    public void moveStaticPiece2() { //move 1 square forward; no obstacle
        initPos = new Position(3,1);
        pieceS = boardS.getPiece(initPos);
        assertTrue(pieceS.moveTo(new Position(4,1), boardS));
        boardS.updateBoard(initPos, pieceS);
    }

    @org.junit.Test
    public void moveStaticPiece3() { //move 1 square forward; no obstacle
        initPos = new Position(4,1);
        pieceS = boardS.getPiece(initPos);
        assertTrue(pieceS.moveTo(new Position(5,1), boardS));
        boardS.updateBoard(initPos, pieceS);
    }

    @org.junit.Test
    public void moveStaticPiece4() { //try to move 1 square forward with an enemy pawn as obstacle
        initPos = new Position(5,1);
        pieceS = boardS.getPiece(initPos);
        assertFalse(pieceS.moveTo(new Position(6,1), boardS));
        boardS.updateBoard(initPos, pieceS);
    }

    @org.junit.Test
    public void moveStaticPiece5() { //try to move to the right, on the same row
        initPos = new Position(5,1);
        pieceS = boardS.getPiece(initPos);
        assertFalse(pieceS.moveTo(new Position(5,2), boardS));
        boardS.updateBoard(initPos, pieceS);
    }

    @org.junit.Test
    public void moveStaticPiece6() { //try to move back 1 square
        initPos = new Position(5,1);
        pieceS = boardS.getPiece(initPos);
        assertFalse(pieceS.moveTo(new Position(4,1), boardS));
        boardS.updateBoard(initPos, pieceS);
    }
    @org.junit.Test
    public void moveStaticPiece7() { //attack an opponent's pawn
        initPos = new Position(5,1);
        pieceS = boardS.getPiece(initPos);
        assertTrue(pieceS.moveTo(new Position(6,2), boardS));
        boardS.updateBoard(initPos, pieceS);
    }

    @org.junit.Test
    public void moveStaticPiece8() { // move 1 square forward; no obstacle; shapeShift available
        initPos = new Position(6,2);
        pieceS = boardS.getPiece(initPos);
        assertTrue(pieceS.moveTo(new Position(7,2), boardS));
        boardS.updateBoard(initPos, pieceS);
    }


    //tests for rook
    @org.junit.Test
    public void moveRook1() {
        Piece[][] matrix = new Piece[8][8];
        matrix[0][0] = new Rook(new Position(0,0), Colour.BLACK);
        matrix[0][7] = new Rook(new Position(0,7), Colour.BLACK);
        matrix[7][7] = new Rook(new Position(7,7), Colour.WHITE);
        matrix[7][0] = new Rook(new Position(7,0), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(0,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(new Position(7,0), board2));
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveRook2() {
        Piece[][] matrix = new Piece[8][8];
        matrix[0][0] = new Rook(new Position(0,0), Colour.BLACK);
        matrix[0][7] = new Rook(new Position(0,7), Colour.BLACK);
        matrix[7][7] = new Rook(new Position(7,7), Colour.WHITE);
        matrix[7][0] = new Rook(new Position(7,0), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(0,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.moveTo(new Position(0,7), board2));
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveRook3() {
        Piece[][] matrix = new Piece[8][8];
        matrix[0][0] = new Rook(new Position(0,0), Colour.BLACK);
        matrix[0][7] = new Rook(new Position(0,7), Colour.BLACK);
        matrix[7][7] = new Rook(new Position(7,7), Colour.WHITE);
        matrix[7][0] = new Rook(new Position(7,0), Colour.WHITE);
        matrix[4][0] = new Pawn(new Position(4,0), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(0,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(new Position(4,0), board2));
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveRook4() {
        Piece[][] matrix = new Piece[8][8];
        matrix[0][0] = new Rook(new Position(0,0), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        piece.moveTo(new Position(4,0), board2);
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
        assertTrue(((Rook) piece).wasMoved());
    }

    @org.junit.Test
    public void moveRook5() {
        Piece[][] matrix = new Piece[8][8];

        matrix[7][7] = new Rook(new Position(7,7), Colour.WHITE);
        matrix[7][0] = new Rook(new Position(7,0), Colour.WHITE);
        matrix[4][0] = new Pawn(new Position(4,0), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(7,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.moveTo(new Position(4,0), board2));
        assertFalse(piece.moveTo(new Position(0,0), board2));
        assertFalse(piece.moveTo(new Position(6,1), board2));
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
    }

    /**
     * tests for bishops
     */

    @org.junit.Test
    public void moveBishop() { //moving down-left, no obstacle
        Piece[][] matrix = new Piece[8][8];

        matrix[0][2] = new Bishop(new Position(0,2), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,2);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(new Position(2,0), board2));
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop1() { //moving down-right, no obstacle
        Piece[][] matrix = new Piece[8][8];

        matrix[0][2] = new Bishop(new Position(0,2), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,2);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(new Position(5,7), board2));
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop2() { //moving up-right, no obstacle
        Piece[][] matrix = new Piece[8][8];

        matrix[7][0] = new Bishop(new Position(7,0), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(7,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(new Position(0,7), board2));
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop3() { //moving up-right, to attack an opponent's piece
        Piece[][] matrix = new Piece[8][8];

        matrix[7][0] = new Bishop(new Position(7,0), Colour.WHITE);
        matrix[1][6] = new Bishop(new Position(1,6), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(7,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(new Position(1,6), board2));
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop4() { //moving up-right, where is a friendly piece
        Piece[][] matrix = new Piece[8][8];

        matrix[7][0] = new Bishop(new Position(7,0), Colour.WHITE);
        matrix[1][6] = new Bishop(new Position(1,6), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(7,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.moveTo(new Position(1,6), board2));
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop5() { //try a position that is not on diagonal; no obstacles
        Piece[][] matrix = new Piece[8][8];

        matrix[7][0] = new Bishop(new Position(7,0), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(7,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.moveTo(new Position(1,1), board2));
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop6() { //move on diagonal, with obstacles
        Piece[][] matrix = new Piece[8][8];

        matrix[7][0] = new Bishop(new Position(7,0), Colour.WHITE);
        matrix[1][6] = new Bishop(new Position(1,6), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(7,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.moveTo(new Position(0,7), board2));
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop7() { //move on diagonal, to next empty cell
        Piece[][] matrix = new Piece[8][8];

        matrix[7][0] = new Bishop(new Position(7,0), Colour.WHITE);
        matrix[1][6] = new Bishop(new Position(1,6), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(7,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(new Position(5,2), board2));
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
    }
}