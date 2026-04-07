package pieces;

import main.Tuple;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color);

        icon = color == Color.WHITE ? ScaledIcons.getWhiteQueenIcon() : ScaledIcons.getBlackQueenIcon();
    }

    @Override
    public boolean moveIsValid(Tuple currentPosition, Tuple targetPosition) {
        Piece bishop = new Bishop(color);
        Piece rook = new Rook(color);

        boolean validateTaking = getValidateTaking(); // for validating checks

        if (bishop.moveIsValid(currentPosition, targetPosition)) return true;

        setValidateTaking(validateTaking); // setting validateTaking to what it was before moveIsValid() set it to 'true'

        return rook.moveIsValid(currentPosition, targetPosition);
    }
}
