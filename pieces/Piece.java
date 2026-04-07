package pieces;

import main.Board;
import main.Tuple;

import javax.swing.*;

public abstract class Piece {
    public enum Color {
        WHITE,
        BLACK;

        public Color opposite() {
            return this == WHITE ? BLACK : WHITE;
        }
    }

    private static boolean validateTaking = true;

    Icon icon;
    Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean moveIsValid(Tuple currentPosition, Tuple targetPosition);


    public boolean isChecking(Tuple currentPosition, Tuple targetPosition) {
        validateTaking = false;
        return moveIsValid(currentPosition, targetPosition);
    }

    boolean canTake(Piece targetPiece) {
        if (validateTaking) {
            return !(targetPiece.color == color || targetPiece instanceof King);
        } else {
            validateTaking = true;
            return !(targetPiece.color == color);
        }
    }


    public Icon getIcon() {
        return icon;
    }

    public Color getColor() {
        return color;
    }

    boolean getValidateTaking() {
        return validateTaking;
    }
    void setValidateTaking(boolean validateTaking) {
        Piece.validateTaking = validateTaking;
    }

    public static Piece toPiece(String pieceName, Color pieceColor) {
        return switch (pieceName) {
            case "Pawn" -> new Pawn(pieceColor);
            case "Knight" -> new Knight(pieceColor);
            case "Bishop" -> new Bishop(pieceColor);
            case "Rook" -> new Rook(pieceColor);
            case "Queen" -> new Queen(pieceColor);
            case "King" -> new King(pieceColor);
            default -> null;
        };
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
