package main;

import pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Square {
    private Piece piece;
    private final int column;
    private final int row;
    private final JButton button = new JButton("");

    public Square(JFrame frame, int column, int row) {
        this.column = column;
        this.row = row;

        button.setFocusable(false);
        button.addActionListener(_ -> Move.action(this.row, this.column, button));
        setDefaultColor(button, column, row);

        if(piece != null) {
            button.setIcon(piece.getIcon());
        }


        frame.add(button);
    }

    public static void setDefaultColor(JButton button, int column, int row) {
        Color dark = new Color(0x332222);
        Color light = new Color(0xAA8888);

        if (column % 2 == 0) {
            button.setBackground(row % 2 == 0 ? dark : light);
        } else {
            button.setBackground(row % 2 == 0 ? light : dark);
        }
    }

    public JButton getButton() {
        return button;
    }

    public Piece getPiece() {
        return piece;
    }

    public Tuple getPosition() {
        return new Tuple(column, row);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if(piece != null) {
            button.setIcon(piece.getIcon());
        } else {
            button.setIcon(null);
        }
    }
}
