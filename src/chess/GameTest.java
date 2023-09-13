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
        assertTrue(piece.canMoveTo(new Position(2,1), board));
    }

    @org.junit.Test
    public void movePiece2() {
        initPos = new Position(1,1);
        piece = board.getPiece(initPos);
        assertFalse(piece.canMoveTo(new Position(2,0), board));
    }
    @org.junit.Test
    public void movePiece3() {
        initPos = new Position(1,1);
        piece = board.getPiece(initPos);
        assertFalse(piece.canMoveTo(new Position(2,2), board));
    }
    @org.junit.Test
    public void movePiece4() {
        initPos = new Position(1,1);
        piece = board.getPiece(initPos);
        assertTrue(piece.canMoveTo(new Position(3,1), board));
    }
    @org.junit.Test
    public void movePiece5() {
        initPos = new Position(1,1);
        piece = board.getPiece(initPos);
        assertFalse(piece.canMoveTo(new Position(3,2), board));
    }
    @org.junit.Test
    public void movePiece6() {
        initPos = new Position(1,1);
        piece = board.getPiece(initPos);
        assertFalse(piece.canMoveTo(new Position(3,0), board));
    }
    @org.junit.Test
    public void movePiece7() {
        initPos = new Position(1,1);
        piece = board.getPiece(initPos);
        assertFalse(piece.canMoveTo(new Position(0,1), board));
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
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
    }

    @org.junit.Test
    public void moveStaticPiece() {
        board = new Board();
        initPos = new Position(1,1);
        newPos = new Position(3,1);
        piece = board.getPiece(initPos);
        piece.moveTo(newPos, board);
        board.updateBoard(initPos, piece);

        initPos = new Position(3,1);
        newPos = new Position(4,1);
        piece = board.getPiece(initPos);
        piece.moveTo(newPos, board);
        board.updateBoard(initPos, piece);

        initPos = new Position(4,1);
        newPos = new Position(5,1);
        piece = board.getPiece(initPos);
        piece.moveTo(newPos, board);
        board.updateBoard(initPos, piece);

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
        board.updateBoard(initPos, piece);
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
        assertTrue(piece.canMoveTo(newPos, board2));
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
        newPos = new Position(0,7);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.canMoveTo(newPos, board2));
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
        newPos = new Position(4,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        piece.canMoveTo(newPos, board2);
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
        newPos = new Position(0,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.canMoveTo(newPos, board2));
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
        newPos = new Position(2,0);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertTrue(piece.canMoveTo(newPos, board2));
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
        newPos = new Position(1,6);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.canMoveTo(newPos, board2));
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
        newPos = new Position(1,6);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertFalse(piece.canMoveTo(newPos, board2));
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
        newPos = new Position(0,7);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.canMoveTo(newPos, board2));
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
        newPos = new Position(5,2);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertFalse(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertFalse(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertTrue(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
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
        assertFalse(piece.canMoveTo(newPos, board2));
        board2.updateBoard(initPos, piece);
        System.out.println(board2);
    }

    /**
     * tests for king
     */

    @org.junit.Test
    public void underCheckKing() {
        Piece[][] matrix = new Piece[8][8];
        Piece king = new King(new Position(0,4), Colour.WHITE);
        Player player1 = new Player("default", Colour.BLACK, null);
        Player player2 = new Player("default", Colour.WHITE, king);

        matrix[0][4] = new King(new Position(0,4), Colour.WHITE);
        matrix[0][0] = new Rook(new Position(0,0), Colour.BLACK);
        matrix[2][4] = new Queen(new Position(2,4), Colour.BLACK);

        Board board2 = new Board(matrix);
        board2.allocatePieces(player1, player2);
        initPos = new Position(0,4);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertEquals(2, player2.getKing().piecesThatThreatensKing(board2, player1.getAvailablePieces()));
    }

    @org.junit.Test
    public void underCheckKing2() {
        Piece[][] matrix = new Piece[8][8];
        Piece king = new King(new Position(4,3), Colour.BLACK);
        Player player1 = new Player("default", Colour.BLACK, king);
        Player player2 = new Player("default", Colour.WHITE, null);

        matrix[4][3] = king;
        matrix[4][4] = new Rook(new Position(4,4), Colour.BLACK);
        matrix[4][5] = new Rook(new Position(4,5), Colour.WHITE);
        matrix[0][3] = new Queen(new Position(0,3), Colour.WHITE);
        matrix[7][0] = new Bishop(new Position(7,0), Colour.WHITE);
        matrix[3][2] = new Pawn(new Position(3,2), Colour.WHITE);
        matrix[3][4] = new Pawn(new Position(3,4), Colour.WHITE);

        Board board2 = new Board(matrix);
        board2.allocatePieces(player1, player2);
        initPos = new Position(4,3);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertEquals(4, player1.getKing().piecesThatThreatensKing(board2, player2.getAvailablePieces()));
    }

    @org.junit.Test
    public void underCheckKing3() {
        Piece[][] matrix = new Piece[8][8];
        Piece king = new King(new Position(4,3), Colour.BLACK);
        matrix[4][3] = king;
        Player player1 = new Player("default", Colour.BLACK, king);
        Player player2 = new Player("default", Colour.WHITE, null);
        matrix[2][2] = new Knight(new Position(2,2), Colour.BLACK);

        Board board2 = new Board(matrix);
        board2.allocatePieces(player1, player2);
        initPos = new Position(4,3);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertEquals(0, player1.getKing().piecesThatThreatensKing(board2, player2.getAvailablePieces()));
    }

    @org.junit.Test
    public void underCheckKing4() {
        Piece[][] matrix = new Piece[8][8];
        Piece king = new King(new Position(4,3), Colour.BLACK);
        Player player1 = new Player("default", Colour.BLACK, king);
        Player player2 = new Player("default", Colour.WHITE, null);

        matrix[4][3] = king;
        matrix[2][2] = new Knight(new Position(2,2), Colour.WHITE);

        Board board2 = new Board(matrix);
        board2.allocatePieces(player1, player2);
        initPos = new Position(4,3);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertEquals(1, player1.getKing().piecesThatThreatensKing(board2, player2.getAvailablePieces()));
    }

    /**
     * tests for castling
     */
    @org.junit.Test
    public void castle() { //no obstacles; castle to left
        Piece[][] matrix = new Piece[8][8];
        Piece king = new King(new Position(0,3), Colour.WHITE);
        matrix[0][3] = king;
        matrix[0][0] = new Rook(new Position(0,0), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(0,3);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(new Position(0,1),board2));
    }

    @org.junit.Test
    public void castle1() { //king is checked
        Piece[][] matrix = new Piece[8][8];
        Piece king = new King(new Position(0,3), Colour.WHITE);
        matrix[0][3] = king;
        matrix[0][0] = new Rook(new Position(0,0), Colour.WHITE);
        matrix[4][3] = new Queen(new Position(4,3), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,3);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.canMoveTo(new Position(0,1),board2));
    }

    @org.junit.Test
    public void castle2() { //king moves to checked position
        Piece[][] matrix = new Piece[8][8];
        Piece king = new King(new Position(0,3), Colour.WHITE);
        matrix[0][3] = king;
        matrix[0][0] = new Rook(new Position(0,0), Colour.WHITE);
        matrix[5][6] = new Bishop(new Position(5,6), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,3);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.canMoveTo(new Position(0,1),board2));
    }

    @org.junit.Test
    public void castle3() { //king goes through a checked position
        Piece[][] matrix = new Piece[8][8];
        Piece king = new King(new Position(0,3), Colour.WHITE);
        matrix[0][3] = king;
        matrix[0][0] = new Rook(new Position(0,0), Colour.WHITE);
        matrix[6][2] = new Rook(new Position(6,2), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,3);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.canMoveTo(new Position(0,1),board2));
    }

    @org.junit.Test
    public void castle4() { //rook belongs to opponent
        Piece[][] matrix = new Piece[8][8];
        Piece king = new King(new Position(0,3), Colour.WHITE);
        matrix[0][3] = king;
        matrix[0][0] = new Rook(new Position(0,0), Colour.BLACK);

        Board board2 = new Board(matrix);
        initPos = new Position(0,3);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.canMoveTo(new Position(0,1),board2));
    }

    @org.junit.Test
    public void castle5() { //rook was moved
        Piece[][] matrix = new Piece[8][8];
        Piece king = new King(new Position(0,3), Colour.WHITE);
        matrix[0][3] = king;
        Piece rook = new Rook(new Position(0,0), Colour.WHITE);
        matrix[0][0] = rook;

        Board board2 = new Board(matrix);
        rook.canMoveTo(new Position(1,0), board2);
        board2.updateBoard(new Position(0,0), rook);
        rook.canMoveTo(new Position(0,0), board2);
        board2.updateBoard(new Position(1,0), rook);
        initPos = new Position(0,3);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.canMoveTo(new Position(0,1),board2));
    }

    @org.junit.Test
    public void castle6() { //rook was moved
        Piece[][] matrix = new Piece[8][8];
        Piece king = new King(new Position(0,3), Colour.WHITE);
        matrix[0][3] = king;
        Piece rook = new Rook(new Position(0,0), Colour.WHITE);
        matrix[0][0] = rook;

        Board board2 = new Board(matrix);
        king.canMoveTo(new Position(1,4), board2);
        board2.updateBoard(new Position(0,3), king);
        king.canMoveTo(new Position(0,3), board2);
        board2.updateBoard(new Position(1,4), king);
        initPos = new Position(0,3);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.canMoveTo(new Position(0,1),board2));
    }

    @org.junit.Test
    public void castle7() { //there is no empty path between pieces for castling
        Piece[][] matrix = new Piece[8][8];
        Piece king = new King(new Position(0,3), Colour.WHITE);
        matrix[0][3] = king;
        matrix[0][0] = new Rook(new Position(0,0), Colour.WHITE);
        matrix[0][1] = new Knight(new Position(0,0), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(0,3);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertFalse(piece.canMoveTo(new Position(0,1),board2));
    }

    @org.junit.Test
    public void castle8() { //no obstacles; castle to right
        Piece[][] matrix = new Piece[8][8];
        Piece king = new King(new Position(0,3), Colour.WHITE);
        matrix[0][3] = king;
        matrix[0][7] = new Rook(new Position(0,7), Colour.WHITE);

        Board board2 = new Board(matrix);
        initPos = new Position(0,3);
        piece = board2.getPiece(initPos);
        System.out.println(board2);
        assertTrue(piece.moveTo(new Position(0,5),board2));
    }
}