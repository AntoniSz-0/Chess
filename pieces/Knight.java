package pieces;

import main.Board;
import main.Tuple;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);

        icon = color == Color.WHITE ? ScaledIcons.getWhiteKnightIcon() : ScaledIcons.getBlackKnightIcon();
    }

    @Override
    public boolean moveIsValid(Tuple currentPosition, Tuple targetPosition) {
        int absDeltaX = Math.abs(targetPosition.x - currentPosition.x);
        int absDeltaY = Math.abs(targetPosition.y - currentPosition.y);

        Piece targetPiece = Board.board[targetPosition.y - 1][targetPosition.x - 1].getPiece();

        return (absDeltaX == 2 && absDeltaY == 1 || absDeltaY == 2 && absDeltaX == 1) && (targetPiece == null || canTake(targetPiece));
    }
}
