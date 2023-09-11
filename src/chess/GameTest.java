package chess;

import static org.junit.Assert.*;

public class GameTest {
    Board board;
    Position initPos;
    Position newPos;
    Piece piece;

    /**
     * The tests below are checking if moving a pawn respects all the chess rules.
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
    @org.junit.Test
    public void movePawn() {
        Piece[][] matrix = new Piece[8][8];
        matrix[6][0] = new Pawn(new Position(6,0), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(6,0);
        newPos = new Position(4,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
    }

    @org.junit.Test
    public void moveStaticPiece() {
        board = new Board();
        initPos = new Position(1,1);
        newPos = new Position(3,1);
        piece = board.getPiece(initPos);
        piece.moveTo(newPos, board);
        board.updateBoard(initPos, newPos, piece);

        initPos = new Position(3,1);
        newPos = new Position(4,1);
        piece = board.getPiece(initPos);
        piece.moveTo(newPos, board);
        board.updateBoard(initPos, newPos, piece);

        initPos = new Position(4,1);
        newPos = new Position(5,1);
        piece = board.getPiece(initPos);
        piece.moveTo(newPos, board);
        board.updateBoard(initPos, newPos, piece);

        initPos = new Position(5,1);
        newPos = new Position(5,0);
        piece = board.getPiece(initPos);
        assertFalse(piece.moveTo(newPos, board));

        initPos = new Position(5,1);
        newPos = new Position(6,1);
        piece = board.getPiece(initPos);
        assertFalse(piece.moveTo(newPos, board));

        initPos = new Position(5,1);
        newPos = new Position(6,2);
        piece = board.getPiece(initPos);
        assertTrue(piece.moveTo(newPos, board));
        board.updateBoard(initPos, newPos, piece);
    }


    /**
     * tests for rooks
     */
    @org.junit.Test
    public void moveRook1() {
        Piece[][] matrix = new Piece[8][8];
        matrix[0][0] = new Rook(new Position(0,0), Colour.BLACK);
        matrix[0][7] = new Rook(new Position(0,7), Colour.BLACK);
        matrix[7][7] = new Rook(new Position(7,7), Colour.WHITE);
        matrix[7][0] = new Rook(new Position(7,0), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(0,0);
        newPos = new Position(7,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
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
        newPos = new Position(0,7);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
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
        newPos = new Position(4,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveRook4() {
        Piece[][] matrix = new Piece[8][8];
        matrix[0][0] = new Rook(new Position(0,0), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,0);
        newPos = new Position(4,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        piece.moveTo(newPos, board2);
        board2.updateBoard(initPos, newPos, piece);
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
        newPos = new Position(0,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
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
        newPos = new Position(2,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop1() { //moving down-right, no obstacle
        Piece[][] matrix = new Piece[8][8];

        matrix[0][2] = new Bishop(new Position(0,2), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,2);
        newPos = new Position(5,7);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop2() { //moving up-right, no obstacle
        Piece[][] matrix = new Piece[8][8];

        matrix[7][0] = new Bishop(new Position(7,0), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(7,0);
        newPos = new Position(0,7);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop3() { //moving up-right, to attack an opponent's piece
        Piece[][] matrix = new Piece[8][8];

        matrix[7][0] = new Bishop(new Position(7,0), Colour.WHITE);
        matrix[1][6] = new Bishop(new Position(1,6), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(7,0);
        newPos = new Position(1,6);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop4() { //moving up-right, where is a friendly piece
        Piece[][] matrix = new Piece[8][8];

        matrix[7][0] = new Bishop(new Position(7,0), Colour.WHITE);
        matrix[1][6] = new Bishop(new Position(1,6), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(7,0);
        newPos = new Position(1,6);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop5() { //try a position that is not on diagonal; no obstacles
        Piece[][] matrix = new Piece[8][8];

        matrix[7][0] = new Bishop(new Position(7,0), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(7,0);
        newPos = new Position(1,1);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop6() { //move on diagonal, with obstacles
        Piece[][] matrix = new Piece[8][8];

        matrix[7][0] = new Bishop(new Position(7,0), Colour.WHITE);
        matrix[1][6] = new Bishop(new Position(1,6), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(7,0);
        newPos = new Position(0,7);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveBishop7() { //move on diagonal, to next empty cell
        Piece[][] matrix = new Piece[8][8];

        matrix[7][0] = new Bishop(new Position(7,0), Colour.WHITE);
        matrix[1][6] = new Bishop(new Position(1,6), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(7,0);
        newPos = new Position(5,2);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    /**
     * tests for knights
     */

    @org.junit.Test
    public void moveKnight() { //check a move to an empty spot
        Piece[][] matrix = new Piece[8][8];

        matrix[3][3] = new Knight(new Position(3,3), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(3,3);
        newPos = new Position(1,2);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveKnight2() { //check a move to an empty spot
        Piece[][] matrix = new Piece[8][8];

        matrix[3][3] = new Knight(new Position(3,3), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(3,3);
        newPos = new Position(5,2);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveKnight3() { //check a move to an empty spot
        Piece[][] matrix = new Piece[8][8];

        matrix[3][3] = new Knight(new Position(3,3), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(3,3);
        newPos = new Position(2,5);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveKnight4() { //check a move to an empty spot
        Piece[][] matrix = new Piece[8][8];

        matrix[3][3] = new Knight(new Position(3,3), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(3,3);
        newPos = new Position(4,1);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveKnight5() { //attack a piece
        Piece[][] matrix = new Piece[8][8];

        matrix[3][3] = new Knight(new Position(3,3), Colour.WHITE);
        matrix[4][1] = new Pawn(new Position(4,1), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(3,3);
        newPos = new Position(4,1);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveKnight6() { //move over a friendly piece
        Piece[][] matrix = new Piece[8][8];

        matrix[3][3] = new Knight(new Position(3,3), Colour.WHITE);
        matrix[4][1] = new Pawn(new Position(4,1), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(3,3);
        newPos = new Position(4,1);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    /**
     * tests for queens
     */

    @org.junit.Test
    public void moveQueen() { //performing moves, without obstacles
        Piece[][] matrix = new Piece[8][8];

        matrix[0][0] = new Queen(new Position(0,0), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,0);
        newPos = new Position(0,7);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveQueen1() { //performing moves, without obstacles
        Piece[][] matrix = new Piece[8][8];

        matrix[0][0] = new Queen(new Position(0,0), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,0);
        newPos = new Position(6,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveQueen2() { //performing moves, without obstacles
        Piece[][] matrix = new Piece[8][8];

        matrix[0][0] = new Queen(new Position(0,0), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,0);
        newPos = new Position(5,5);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveQueen3() { //trying to move out of the path
        Piece[][] matrix = new Piece[8][8];

        matrix[0][0] = new Queen(new Position(0,0), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,0);
        newPos = new Position(1,2);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveQueen4() { //attacking an opponent's piece
        Piece[][] matrix = new Piece[8][8];

        matrix[0][0] = new Queen(new Position(0,0), Colour.BLACK);
        matrix[0][2] = new Queen(new Position(0,2), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(0,0);
        newPos = new Position(0,2);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveQueen5() { //attacking an opponent's piece
        Piece[][] matrix = new Piece[8][8];

        matrix[0][0] = new Queen(new Position(0,0), Colour.BLACK);
        matrix[2][2] = new Rook(new Position(2,2), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(0,0);
        newPos = new Position(2,2);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    @org.junit.Test
    public void moveQueen6() { //try to move over a friendly piece
        Piece[][] matrix = new Piece[8][8];

        matrix[0][0] = new Queen(new Position(0,0), Colour.BLACK);
        matrix[2][2] = new Rook(new Position(2,2), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,0);
        newPos = new Position(2,2);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.moveTo(newPos, board2));
        board2.updateBoard(initPos, newPos, piece);
        System.out.println(board2);
    }

    /**
     * tests for king
     */

    @org.junit.Test
    public void moveKing() {
        Piece[][] matrix = new Piece[8][8];

        matrix[0][4] = new King(new Position(0,4), Colour.WHITE);
        matrix[0][0] = new Rook(new Position(0,0), Colour.BLACK);
        matrix[2][4] = new Queen(new Position(2,4), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,4);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(((King) piece).isUnderCheck(board2));
    }
}