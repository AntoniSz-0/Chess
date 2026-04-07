package main;

import pieces.Piece;

public class Turn {
    private final String movedPiece;
    private final Tuple startPosition;
    private final Tuple endPosition;
    private final String takenPiece;
    final boolean isEnPassant;
    final boolean isCastling;


    public Turn(Piece piece, Tuple startPosition, Tuple endPosition, Piece takenPiece, boolean isEnPassant, boolean isCastling) {
        this.movedPiece = piece.toString();
        this.startPosition = new Tuple(startPosition);
        this.endPosition = new Tuple(endPosition);
        this.takenPiece = takenPiece != null ? takenPiece.toString() : null;
        this.isEnPassant = isEnPassant;
        this.isCastling = isCastling;
    }

    public String getMovedPiece() {
        return movedPiece;
    }
    public Tuple getStartPosition() {
        return startPosition;
    }
    public Tuple getEndPosition() {
        return endPosition;
    }
    public String getTakenPiece() {
        return takenPiece;
    }

    @Override
    public String toString() {
        return "Moved: " + movedPiece + "\nStart: " + startPosition + "\nEnd: " + endPosition + "\nTook: " + takenPiece;
    }
}
