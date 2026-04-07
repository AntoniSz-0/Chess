package pieces;

import main.Board;
import main.Tuple;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);

        icon = color == Color.WHITE ? ScaledIcons.getWhiteRookIcon() : ScaledIcons.getBlackRookIcon();
    }

    @Override
    public boolean moveIsValid(Tuple currentPosition, Tuple targetPosition) {
        int absDeltaX = Math.abs(targetPosition.x - currentPosition.x);
        int absDeltaY = Math.abs(targetPosition.y - currentPosition.y);

        Piece targetPiece = Board.board[targetPosition.y - 1][targetPosition.x - 1].getPiece();
        if (targetPiece != null && !canTake(targetPiece)) {
            return false;
        }


        if (absDeltaX == 0) {
            if (absDeltaY != 0) { // if moving only vertically
                if(targetPosition.y > currentPosition.y) { // going up
                    for (int i = 0; i < absDeltaY - 1; i++) {
                        if (Board.board[currentPosition.y + i][currentPosition.x - 1].getPiece() != null) {
                            return false;
                        }
                    }
                } else { // going down
                    for (int i = 2; i < absDeltaY + 1; i++) {
                        if (Board.board[currentPosition.y - i][currentPosition.x - 1].getPiece() != null) {
                            return false;
                        }
                    }
                }
                return true;
            }
        } else if (absDeltaY == 0) { // moving only horizontally
            if (targetPosition.x > currentPosition.x) { // going right
                for (int i = 0; i < absDeltaX - 1; i++) {
                    if (Board.board[currentPosition.y - 1][currentPosition.x + i].getPiece() != null) {
                        return false;
                    }
                }
            } else { // going left
                for (int i = 2; i < absDeltaX + 1; i++) {
                    if (Board.board[currentPosition.y - 1][currentPosition.x - i].getPiece() != null) {
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }
}
