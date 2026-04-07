package pieces;

import main.Board;
import main.Move;
import main.Tuple;
import main.Turn;

public class King extends Piece {
    public King(Color color) {
        super(color);

        icon = color == Color.WHITE ? ScaledIcons.getWhiteKingIcon() : ScaledIcons.getBlackKingIcon();
    }

    @Override
    public boolean moveIsValid(Tuple currentPosition, Tuple targetPosition) {
        int deltaX = targetPosition.x - currentPosition.x;
        int absDeltaY = Math.abs(targetPosition.y - currentPosition.y);

        Piece targetPiece = Board.board[targetPosition.y - 1][targetPosition.x - 1].getPiece();
        if (targetPiece != null && !canTake(targetPiece)) {
            return false;
        }


        if (Math.abs(deltaX) <= 1 && absDeltaY <= 1 && Math.abs(deltaX) + absDeltaY > 0) {
            return !Move.squareIsChecked(targetPosition, color.opposite());
        }

        // castling
        if (currentPosition.x == 5 && absDeltaY == 0 && getValidateTaking() && !Move.kingIsChecked() && (Move.turnHistory.size() % 2 == 0 ? color == Color.WHITE && currentPosition.y == 1 : color == Color.BLACK && currentPosition.y == 8)) {
            // short castling (right)
            if (deltaX == 2 && !Move.squareIsChecked(new Tuple(6, currentPosition.y), color.opposite()) && !Move.squareIsChecked(new Tuple(7, currentPosition.y), color.opposite())) {
                return isCastlingPossible(currentPosition, 8);
            }

            // long castling (left)
            if (deltaX == -2 && !Move.squareIsChecked(new Tuple(4, currentPosition.y), color.opposite()) && !Move.squareIsChecked(new Tuple(3, currentPosition.y), color.opposite())) {
                return isCastlingPossible(currentPosition, 1);
            }
        }
        return false;
    }

    private boolean isCastlingPossible(Tuple currentPosition, int rookPosition) {
        for (int i = 0; i < Move.turnHistory.size(); i++) {
            Turn turn = Move.turnHistory.get(i);
            Color turnColor = i % 2 == 0 ? Color.WHITE : Color.BLACK;
            if (turnColor == color && ((turn.getMovedPiece().equals("Rook") && turn.getStartPosition().x == rookPosition) || (turn.getMovedPiece().equals("King") && turn.getStartPosition().x == 5)) && turn.getStartPosition().y == currentPosition.y) {
                return false;
            }
        }
        Move.setCastlingHappened(true);
        return true;
    }
}
