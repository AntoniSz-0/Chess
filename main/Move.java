package main;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static main.Square.setDefaultColor;

public class Move {
    private static final Tuple selectedSquare = new Tuple(-1, -1);
    private static final Tuple previouslySelectedSquare = new Tuple(-1, -1);

    private static Piece.Color colorTurn = Piece.Color.WHITE;
    public static ArrayList<Turn> turnHistory = new ArrayList<>();

    private static boolean enPassantHappened = false;
    private static boolean castlingHappened = false;
    private static boolean kingIsChecked = false;

    public static void action(int row, int column, JButton button) {
        if(Board.board[row - 1][column - 1].getPiece() == null && selectedSquare.x == -1) {
            return;
        }

        previouslySelectedSquare.x = selectedSquare.x;
        previouslySelectedSquare.y = selectedSquare.y;

        selectedSquare.x = column;
        selectedSquare.y = row;

        button.setBackground(new Color(0xA1C5FF));


        move: if (previouslySelectedSquare.x != -1) {
            Piece selectedPiece = Board.board[previouslySelectedSquare.y - 1][previouslySelectedSquare.x - 1].getPiece();
            if (selectedPiece != null && selectedPiece.getColor() == colorTurn && selectedPiece.moveIsValid(previouslySelectedSquare, selectedSquare)) {
                turnHistory.add(new Turn(selectedPiece, previouslySelectedSquare, selectedSquare, Board.board[selectedSquare.y - 1][selectedSquare.x - 1].getPiece(), enPassantHappened, castlingHappened));
                colorTurn = colorTurn.opposite();

                Board.board[selectedSquare.y - 1][selectedSquare.x - 1].setPiece(selectedPiece);
                Board.board[previouslySelectedSquare.y - 1][previouslySelectedSquare.x - 1].setPiece(null);

                l: if (kingIsChecked()) {
                    if (!kingIsChecked) break l;
                    undoMove();
                    break move;
                }

                if (selectedPiece.toString().equals("Pawn") && (selectedSquare.y == 8 || selectedSquare.y == 1)) {
                    String[] options = {"Queen", "Rook", "Bishop", "Knight"};
                    ImageIcon pawnIcon = new ImageIcon((colorTurn == Piece.Color.BLACK ? new ImageIcon("src/Icons/pawn_w.png") : new ImageIcon("src/Icons/pawn_b.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                    int answer = JOptionPane.showOptionDialog(null, "What do you want to promote the pawn into?", "Pawn Promotion", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, pawnIcon, options, 0) + 1;

                    if (answer != 0)
                        Board.board[selectedSquare.y - 1][selectedSquare.x - 1].setPiece(switch (answer) {
                            case 1 -> new Queen(colorTurn.opposite());
                            case 2 -> new Rook(colorTurn.opposite());
                            case 3 -> new Bishop(colorTurn.opposite());
                            case 4 -> new Knight(colorTurn.opposite());
                            default -> null;
                        });
                    else undoMove();
                }

                if (enPassantHappened) {
                    Board.board[selectedSquare.y - (turnHistory.size() % 2 == 1 ? 2 : 0)][selectedSquare.x - 1].setPiece(null);
                    enPassantHappened = false;
                }
                else if (castlingHappened) {
                    if (selectedSquare.x == 7) {
                        Board.board[selectedSquare.y - 1][5].setPiece(Board.board[selectedSquare.y - 1][7].getPiece());
                        Board.board[selectedSquare.y - 1][7].setPiece(null);
                    } else {
                        Board.board[selectedSquare.y - 1][3].setPiece(Board.board[selectedSquare.y - 1][0].getPiece());
                        Board.board[selectedSquare.y - 1][0].setPiece(null);
                    }
                    castlingHappened = false;
                }

                kingIsChecked = kingIsChecked();
            }
        }

        if(previouslySelectedSquare.x != -1) {
            // Reset previously selected square's color
            setDefaultColor(Board.board[previouslySelectedSquare.y - 1][previouslySelectedSquare.x - 1].getButton(), previouslySelectedSquare.y, previouslySelectedSquare.x);
            setDefaultColor(Board.board[selectedSquare.y - 1][selectedSquare.x - 1].getButton(), selectedSquare.y, selectedSquare.x);

            previouslySelectedSquare.x = -1;
            previouslySelectedSquare.y = -1;

            selectedSquare.x = -1;
            selectedSquare.y = -1;

        }
    }

    public static boolean squareIsChecked(Tuple targetSquare, Piece.Color color) {
        for (Square[] row : Board.board) {
            for (Square square : row) {
                if (square.getPiece() != null && square.getPiece().getColor() == color && square.getPiece().isChecking(square.getPosition(), targetSquare)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean squareIsChecked(Tuple targetSquare) {
        for (Square[] row : Board.board) {
            for (Square square : row) {
                if (square.getPiece() != null && square.getPiece().isChecking(square.getPosition(), targetSquare)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean kingIsChecked() {
        int kingCount = 0;
        for (Square[] row : Board.board) {
            for (Square square : row) {
                if (square.getPiece() instanceof King) {
                    if (squareIsChecked(square.getPosition())) return true;

                    kingCount++;

                    if (kingCount > 1) return false;
                }
            }
        }
        return false;
    }

    public static void undoMove() {
        Turn lastTurn = turnHistory.getLast();

        if (lastTurn.isEnPassant) {
            Board.board[lastTurn.getEndPosition().y - 1][lastTurn.getEndPosition().x - 1].setPiece(null);
            Board.board[Move.colorTurn == Piece.Color.WHITE ? lastTurn.getEndPosition().y : lastTurn.getEndPosition().y - 2][lastTurn.getEndPosition().x - 1].setPiece(Piece.toPiece("Pawn", Move.colorTurn));
        } else if (lastTurn.isCastling) {
            int castlingRow = lastTurn.getStartPosition().y - 1;
            Board.board[castlingRow][lastTurn.getEndPosition().x - 1].setPiece(null);
            Board.board[castlingRow][lastTurn.getStartPosition().x - 1].setPiece(Piece.toPiece("King", Move.colorTurn));
            if (lastTurn.getEndPosition().x == 7) {
                Board.board[castlingRow][lastTurn.getEndPosition().x - 2].setPiece(null);
                Board.board[castlingRow][7].setPiece(Piece.toPiece("Rook", Move.colorTurn.opposite()));
            } else {
                Board.board[castlingRow][lastTurn.getEndPosition().x].setPiece(null);
                Board.board[castlingRow][0].setPiece(Piece.toPiece("Rook", Move.colorTurn.opposite()));
            }
        } else {
            Board.board[lastTurn.getEndPosition().y - 1][lastTurn.getEndPosition().x - 1].setPiece(lastTurn.getTakenPiece() != null ? Piece.toPiece(lastTurn.getTakenPiece(), turnHistory.size() % 2 == 0 ? Piece.Color.WHITE : Piece.Color.BLACK) : null);
        }
        Board.board[lastTurn.getStartPosition().y - 1][lastTurn.getStartPosition().x - 1].setPiece(Piece.toPiece(lastTurn.getMovedPiece(), turnHistory.size() % 2 == 1 ? Piece.Color.WHITE : Piece.Color.BLACK));

        colorTurn = colorTurn.opposite();
        turnHistory.remove(lastTurn);
    }

    public static void setEnPassantHappened(boolean enPassantHappened) {
        Move.enPassantHappened = enPassantHappened;
    }

    public static void setCastlingHappened(boolean castlingHappened) {
        Move.castlingHappened = castlingHappened;
    }
}
