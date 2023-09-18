package chess;

import org.junit.After;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    /**
     * Tests for moving a pawn.
     */

    @org.junit.Test
    public void movePawn() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[1][2] = new Pawn(new Position(1, 2), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(1,2), new Position(2,2), p1, p2));
    }
    @org.junit.Test
    public void movePawn2() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[1][2] = new Pawn(new Position(1, 2), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(1,2), new Position(3,2), p1, p2));
    }
    @org.junit.Test
    public void movePawn3() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[1][2] = new Pawn(new Position(1, 2), Colour.WHITE);
        customBoard[2][2] = new Pawn(new Position(2, 2), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(1,2), new Position(2,2), p1, p2));
    }
    @org.junit.Test
    public void movePawn4() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[1][2] = new Pawn(new Position(1, 2), Colour.WHITE);
        customBoard[2][2] = new Pawn(new Position(2, 2), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(1,2), new Position(3,2), p1, p2));
    }
    @org.junit.Test
    public void movePawn5() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[1][2] = new Pawn(new Position(1, 2), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(1,2), new Position(2,3), p1, p2));
    }
    @org.junit.Test
    public void movePawn6() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[1][2] = new Pawn(new Position(1, 2), Colour.WHITE);
        customBoard[2][3] = new Rook(new Position(2,3), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(1,2), new Position(2,3), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }
    @org.junit.Test
    public void movePawn7() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[1][2] = new Pawn(new Position(1, 2), Colour.WHITE);
        customBoard[2][3] = new Rook(new Position(2,3), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(1,2), new Position(2,3), p1, p2));
    }
    @org.junit.Test
    public void movePawn8() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[1][2] = new Pawn(new Position(1, 2), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(1,2), new Position(1,3), p1, p2));
    }
    @org.junit.Test
    public void movePawn9() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[1][2] = new Pawn(new Position(1, 2), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        game.movePiece(new Position(1,2), new Position(2,2), p1, p2);
        assertFalse(game.movePiece(new Position(2,2), new Position(4,2), p1, p2));
    }
    @org.junit.Test
    public void movePawn10() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[1][2] = new Pawn(new Position(1, 2), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        game.movePiece(new Position(1,2), new Position(2,2), p1, p2);
        assertFalse(game.movePiece(new Position(2,2), new Position(1,2), p1, p2));
    }

   /* @org.junit.Test
    public void movePawn11() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[6][1] = new Pawn(new Position(6, 1), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(6,1), new Position(7,1), p1, p2));
    }*/

    /**
     * Tests for moving a rook.
     */

    @org.junit.Test
    public void moveRook() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][0] = new Rook(new Position(0, 0), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(0,0), new Position(7,0), p1, p2));
    }
    @org.junit.Test
    public void moveRook2() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][0] = new Rook(new Position(0, 0), Colour.WHITE);
        customBoard[4][0] = new Knight(new Position(4, 0), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(0,0), new Position(7,0), p1, p2));
    }
    @org.junit.Test
    public void moveRook3() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][0] = new Rook(new Position(0, 0), Colour.WHITE);
        customBoard[4][0] = new Knight(new Position(4, 0), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(0,0), new Position(4,0), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }
    @org.junit.Test
    public void moveRook4() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][0] = new Rook(new Position(0, 0), Colour.WHITE);
        customBoard[3][0] = new Knight(new Position(3, 0), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        game.movePiece(new Position(3,0), new Position(1,1), p2, p1);
        assertFalse(game.movePiece(new Position(0,0), new Position(4,0), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }
    @org.junit.Test
    public void moveRook5() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][1] = new Rook(new Position(0, 1), Colour.WHITE);
        customBoard[3][0] = new Knight(new Position(3, 0), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        game.movePiece(new Position(3,0), new Position(1,1), p2, p1);
        assertTrue(game.movePiece(new Position(0,1), new Position(1,1), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }
    @org.junit.Test
    public void moveRook6() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][1] = new Rook(new Position(0, 1), Colour.WHITE);
        customBoard[3][0] = new Knight(new Position(3, 0), Colour.BLACK);
        customBoard[3][6] = new Rook(new Position(3, 6), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        game.movePiece(new Position(3,0), new Position(1,1), p2, p1);
        game.movePiece(new Position(3,6), new Position(0,6), p2, p1);
        assertFalse(game.movePiece(new Position(0,1), new Position(1,1), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }
    @org.junit.Test
    public void moveRook7() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][1] = new Rook(new Position(0, 1), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(0,1), new Position(3,4), p1, p2));
    }

    /**
     * Tests for moving a Bishop.
     */

    @org.junit.Test
    public void moveBishop() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][2] = new Bishop(new Position(0, 2), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(0,2), new Position(1,1), p1, p2));
    }
    @org.junit.Test
    public void moveBishop2() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][2] = new Bishop(new Position(0, 2), Colour.WHITE);
        customBoard[3][0] = new Knight(new Position(3, 0), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        game.movePiece(new Position(3,0), new Position(1,1), p2, p1);
        assertTrue(game.movePiece(new Position(0,2), new Position(1,1), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }
    @org.junit.Test
    public void moveBishop3() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][2] = new Bishop(new Position(0, 2), Colour.WHITE);
        customBoard[3][0] = new Knight(new Position(3, 0), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        game.movePiece(new Position(3,0), new Position(1,1), p2, p1);
        assertFalse(game.movePiece(new Position(0,2), new Position(1,3), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }
    @org.junit.Test
    public void moveBishop4() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][2] = new Bishop(new Position(0, 2), Colour.WHITE);
        customBoard[3][0] = new Knight(new Position(3, 0), Colour.BLACK);
        customBoard[3][6] = new Rook(new Position(3, 6), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        game.movePiece(new Position(3,0), new Position(1,1), p2, p1);
        game.movePiece(new Position(3,6), new Position(0,6), p2, p1);
        assertFalse(game.movePiece(new Position(0,2), new Position(1,1), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }

    /**
     * Tests for moving a Queen.
     */

    @org.junit.Test
    public void moveQueen() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[7][4] = new Queen(new Position(7, 4), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(7,4), new Position(0,4), p2, p1));
    }
    @org.junit.Test
    public void moveQueen2() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[7][4] = new Queen(new Position(7, 4), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(7,4), new Position(3,0), p2, p1));
    }
    @org.junit.Test
    public void moveQueen3() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[7][4] = new Queen(new Position(7, 4), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(7,4), new Position(4,7), p2, p1));
    }
    @org.junit.Test
    public void moveQueen4() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[7][4] = new Queen(new Position(7, 4), Colour.BLACK);
        customBoard[5][6] = new Pawn(new Position(5, 6), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(7,4), new Position(4,7), p2, p1));
    }
    @org.junit.Test
    public void moveQueen5() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[7][4] = new Queen(new Position(7, 4), Colour.BLACK);
        customBoard[5][6] = new Pawn(new Position(5, 6), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(7,4), new Position(5,6), p2, p1));
    }
    @org.junit.Test
    public void moveQueen6() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[7][4] = new Queen(new Position(7, 4), Colour.BLACK);
        customBoard[1][3] = new Rook(new Position(1, 3), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(7,4), new Position(5,6), p2, p1));
    }
    @org.junit.Test
    public void moveQueen7() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[7][4] = new Queen(new Position(7, 4), Colour.BLACK);
        customBoard[1][3] = new Rook(new Position(1, 3), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(7,4), new Position(6,3), p2, p1));
    }

    @org.junit.Test
    public void moveQueen8() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[7][4] = new Queen(new Position(7, 4), Colour.BLACK);
        customBoard[6][3] = new Rook(new Position(6, 3), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(7,4), new Position(6,3), p2, p1));
        System.out.println(p1.getRemovedPieces());
    }

    /**
     * Tests for moving a King.
     */

    @org.junit.Test
    public void moveKing() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(0,3), new Position(0,4), p1, p2));
    }

    @org.junit.Test
    public void moveKing2() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[0][5] = new King(new Position(0, 5), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(0,3), new Position(0,4), p1, p2));
    }
    @org.junit.Test
    public void moveKing3() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[0][5] = new King(new Position(0, 5), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(0,3), new Position(1,4), p1, p2));
    }
    @org.junit.Test
    public void moveKing4() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][4] = new Queen(new Position(0, 4), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(0,3), new Position(0,4), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }
    @org.junit.Test
    public void moveKing5() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][4] = new Queen(new Position(0, 4), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(0,3), new Position(0,2), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }
    @org.junit.Test
    public void moveKing6() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][4] = new Queen(new Position(0, 4), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(0,3), new Position(1,3), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }
    @org.junit.Test
    public void moveKing7() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][4] = new Queen(new Position(0, 4), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(0,3), new Position(1,2), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }
    @org.junit.Test
    public void moveKing8() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][4] = new Queen(new Position(0, 4), Colour.BLACK);
        customBoard[1][4] = new Bishop(new Position(1, 4), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(0,3), new Position(1,4), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }
    @org.junit.Test
    public void moveKing9() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][4] = new Queen(new Position(0, 4), Colour.BLACK);
        customBoard[1][4] = new Bishop(new Position(1, 4), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(0,3), new Position(0,4), p1, p2));
        System.out.println(p2.getRemovedPieces());
    }

    /**
     * castle
     */
    @org.junit.Test
    public void moveKing10() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][0] = new Rook(new Position(0, 0), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(0,3), new Position(0,1), p1, p2));
    }

    @org.junit.Test
    public void moveKing11() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[0][7] = new Rook(new Position(0, 7), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(0,3), new Position(0,5), p1, p2));
    }
    @org.junit.Test
    public void moveKing12() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[7][7] = new Rook(new Position(7, 7), Colour.BLACK);
        customBoard[0][5] = new Queen(new Position(0, 5), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(7,3), new Position(7,5), p2, p1));
    }
    @org.junit.Test
    public void moveKing13() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[7][7] = new Rook(new Position(7, 7), Colour.BLACK);
        customBoard[4][0] = new Queen(new Position(4, 0), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(7,3), new Position(7,5), p2, p1));
    }
    @org.junit.Test
    public void moveKing14() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[7][7] = new Rook(new Position(7, 7), Colour.BLACK);
        customBoard[3][0] = new Queen(new Position(3, 0), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(7,3), new Position(7,5), p2, p1));
    }
    @org.junit.Test
    public void moveKing15() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[7][0] = new Rook(new Position(7, 0), Colour.BLACK);
        customBoard[7][7] = new Rook(new Position(7, 7), Colour.BLACK);
        customBoard[3][0] = new Queen(new Position(3, 0), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertTrue(game.movePiece(new Position(7,3), new Position(7,1), p2, p1));
    }
    @org.junit.Test
    public void moveKing16() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[7][0] = new Rook(new Position(7, 0), Colour.BLACK);
        customBoard[7][7] = new Rook(new Position(7, 7), Colour.BLACK);
        customBoard[3][0] = new Queen(new Position(3, 0), Colour.WHITE);
        customBoard[6][0] = new Pawn(new Position(6, 0), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        assertFalse(game.movePiece(new Position(7,3), new Position(7,1), p2, p1));
    }

    /**
     * Check Mate
     */

    @org.junit.Test
    public void checkMate() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[6][1] = new Queen(new Position(6, 1), Colour.WHITE);
        customBoard[6][0] = new Rook(new Position(6, 0), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        game.movePiece(new Position(6,0), new Position(7,0), p1, p2);
        assertTrue(game.isEnd());
    }
    @org.junit.Test
    public void checkMate2() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[6][1] = new Queen(new Position(6, 1), Colour.BLACK);
        customBoard[6][0] = new Rook(new Position(6, 0), Colour.WHITE);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        game.movePiece(new Position(6,0), new Position(7,0), p1, p2);
        assertFalse(game.isEnd());
    }
    @org.junit.Test
    public void checkMate3() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[6][1] = new Queen(new Position(6, 1), Colour.WHITE);
        customBoard[6][0] = new Rook(new Position(6, 0), Colour.WHITE);
        customBoard[5][2] = new Rook(new Position(5, 2), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        game.movePiece(new Position(6,0), new Position(7,0), p1, p2);
        assertFalse(game.isEnd());
    }

    /**
     * draw
     */
    @org.junit.Test
    public void draw() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[1][6] = new Queen(new Position(1, 6), Colour.BLACK);
        customBoard[5][6] = new Rook(new Position(5, 6), Colour.BLACK);
        customBoard[0][0] = new King(new Position(0, 0), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        game.movePiece(new Position(5,6), new Position(5,1), p2, p1);
        assertTrue(game.isEnd());
    }
    @org.junit.Test
    public void draw2() {
        Piece[][] customBoard = new Piece[8][8];
        customBoard[6][1] = new Queen(new Position(6, 1), Colour.WHITE);
        customBoard[5][2] = new Bishop(new Position(5, 2), Colour.WHITE);
        customBoard[6][7] = new Rook(new Position(6, 7), Colour.BLACK);
        customBoard[0][3] = new King(new Position(0, 3), Colour.WHITE);
        customBoard[7][3] = new King(new Position(7, 3), Colour.BLACK);
        game = new Game(customBoard);
        game.printBoard();
        Player p1 = game.getPlayer(Colour.WHITE);
        Player p2 = game.getPlayer(Colour.BLACK);

        game.movePiece(new Position(6,1), new Position(6,3), p1, p2);
        assertFalse(game.isEnd());
    }
    @After
    public void print(){
        game.printBoard();
    }
}