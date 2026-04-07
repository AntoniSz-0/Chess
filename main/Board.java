package main;

import pieces.*;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {
    private final static short HEIGHT = 8;
    private final static short WIDTH = 8;
    public static Square[][] board = new Square[HEIGHT][WIDTH];

    public Board() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH*100, HEIGHT*100);
        setLayout(new GridLayout(HEIGHT, WIDTH, 0, 0));
        setTitle("Szachy");
        setIconImage(new ImageIcon("src/Icons/chess.png").getImage());

        for (int i = HEIGHT; i >= 1; i--) { // rows
            for (int j = 1; j <= WIDTH; j++) { // columns
                board[i - 1][j - 1] = new Square(this, j, i);
            }
        }

        setUpBoard();
        System.gc();

        setVisible(true);
    }

    private void setUpBoard() {
        // pawns setup
        for (int i = 0; i <= 7; i++) {
            board[HEIGHT-2][i].setPiece(new Pawn(Piece.Color.BLACK));
            board[1][i].setPiece(new Pawn(Piece.Color.WHITE));
        }
    
        // figures setup
        {
            Piece[] pieces_w = {new Rook(Piece.Color.WHITE), new Knight(Piece.Color.WHITE), new Bishop(Piece.Color.WHITE)};
            Piece[] pieces_b = {new Rook(Piece.Color.BLACK), new Knight(Piece.Color.BLACK), new Bishop(Piece.Color.BLACK)};
            for(int i = 0; i < 3; i++) {
                board[HEIGHT-1][i].setPiece(pieces_b[i]);
                board[HEIGHT-1][7-i].setPiece(pieces_b[i]);

                board[0][i].setPiece(pieces_w[i]);
                board[0][7-i].setPiece(pieces_w[i]);
            }
        }

        board[0][4].setPiece(new King(Piece.Color.WHITE));
        board[HEIGHT-1][4].setPiece(new King(Piece.Color.BLACK));

        board[0][3].setPiece(new Queen(Piece.Color.WHITE));
        board[HEIGHT-1][3].setPiece(new Queen(Piece.Color.BLACK));
    }
}