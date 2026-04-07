package pieces;

import main.Board;
import main.Tuple;
import main.Move;
import main.Turn;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);

        icon = color == Color.WHITE ? ScaledIcons.getWhitePawnIcon() : ScaledIcons.getBlackPawnIcon();
    }

    @Override
    public boolean moveIsValid(Tuple currentPosition, Tuple targetPosition) {
        int absDeltaX = Math.abs(targetPosition.x - currentPosition.x);
        int deltaY = targetPosition.y - currentPosition.y;

        Piece targetPiece = Board.board[targetPosition.y - 1][targetPosition.x - 1].getPiece();

        if (color == Color.WHITE) {
            if (targetPosition.x == currentPosition.x) {
                return targetPiece == null &&
                        (deltaY == 1 ||
                         deltaY == 2 && currentPosition.y == 2 && Board.board[currentPosition.y][currentPosition.x - 1].getPiece() == null); // moving two up, is on starting position and there is no piece in the way
            } else {
                if (deltaY == 1 && absDeltaX == 1) { // if moving one up and one to the side
                    if (targetPiece != null && canTake(targetPiece)) return true;

                    //checking for "en passant"
                    if (Move.turnHistory.isEmpty()) return false;
                    Turn lastTurn = Move.turnHistory.getLast();
                    if (targetPiece == null && lastTurn.getMovedPiece().equals("Pawn") && lastTurn.getStartPosition().y - lastTurn.getEndPosition().y == 2 && lastTurn.getEndPosition().y == targetPosition.y - 1) {
                        Move.setEnPassantHappened(true);
                        return true;
                    }
                }
            }
        } else {
            if (targetPosition.x == currentPosition.x) {
                return targetPiece == null &&
                        (deltaY == -1 ||
                         deltaY == -2 && currentPosition.y == Board.board.length - 1 && Board.board[currentPosition.y - 2][currentPosition.x - 1].getPiece() == null);    // moving two down, is on starting position and there is no piece in the way
            } else {
                if (deltaY == -1 && absDeltaX == 1) { // if moving one down and one to the side
                    if (targetPiece != null && canTake(targetPiece)) {
                        return true;
                    }

                    // checking for "en passant"
                    if (Move.turnHistory.isEmpty()) return false;
                    Turn lastTurn = Move.turnHistory.getLast();
                    if (targetPiece == null && lastTurn.getMovedPiece().equals("Pawn") && lastTurn.getStartPosition().y - lastTurn.getEndPosition().y == -2 && lastTurn.getEndPosition().y == targetPosition.y + 1) {
                        Move.setEnPassantHappened(true);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
