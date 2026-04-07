package pieces;

import main.Board;
import main.Tuple;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);

        icon = color == Color.WHITE ? ScaledIcons.getWhiteBishopIcon() : ScaledIcons.getBlackBishopIcon();
    }

    @Override
    public boolean moveIsValid(Tuple currentPosition, Tuple targetPosition) {
        int absDeltaX = Math.abs(targetPosition.x - currentPosition.x);
        int absDeltaY = Math.abs(targetPosition.y - currentPosition.y);

        Piece targetPiece = Board.board[targetPosition.y - 1][targetPosition.x - 1].getPiece();
        if (targetPiece != null && !canTake(targetPiece)) {
            return false;
        }


        if (absDeltaX != 0 && absDeltaX == absDeltaY) {
            if (targetPosition.x > currentPosition.x) {
                if (targetPosition.y > currentPosition.y) { // going up right
                    for (int i = 0; i < absDeltaX - 1; i++) {
                        if (Board.board[currentPosition.y + i][currentPosition.x + i].getPiece() != null) {
                            return false;
                        }
                    }
                } else { // going down right
                    for (int i = 0; i < absDeltaX - 1; i++) {
                        if (Board.board[currentPosition.y - 2 - i][currentPosition.x + i].getPiece() != null) {
                            return false;
                        }
                    }
                }
            } else {
                if (targetPosition.y > currentPosition.y) { // going up left
                    for (int i = 0; i < absDeltaX - 1; i++) {
                        if (Board.board[currentPosition.y + i][currentPosition.x - 2 - i].getPiece() != null) {
                            return false;
                        }
                    }
                } else { // going down left
                    for (int i = 0; i < absDeltaX - 1; i++) {
                        if (Board.board[currentPosition.y - 2 - i][currentPosition.x - 2 - i].getPiece() != null) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }

        return false;
    }
}
