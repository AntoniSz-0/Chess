package pieces;

import javax.swing.*;
import java.awt.*;

class ScaledIcons {
    private static final short width = 100;
    private static final short height = 100;

    private static final Icon blackPawnIcon = new ImageIcon(new ImageIcon("src/Icons/pawn_b.png").getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH));
    private static final Icon whitePawnIcon = new ImageIcon(new ImageIcon("src/Icons/pawn_w.png").getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH));

    private static final Icon blackRookIcon = new ImageIcon(new ImageIcon("src/Icons/rook_b.png").getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH));
    private static final Icon whiteRookIcon = new ImageIcon(new ImageIcon("src/Icons/rook_w.png").getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH));

    private static final Icon blackKnightIcon = new ImageIcon(new ImageIcon("src/Icons/knight_b.png").getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH));
    private static final Icon whiteKnightIcon = new ImageIcon(new ImageIcon("src/Icons/knight_w.png").getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH));

    private static final Icon blackBishopIcon = new ImageIcon(new ImageIcon("src/Icons/bishop_b.png").getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH));
    private static final Icon whiteBishopIcon = new ImageIcon(new ImageIcon("src/Icons/bishop_w.png").getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH));

    private static final Icon blackQueenIcon = new ImageIcon(new ImageIcon("src/Icons/queen_b.png").getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH));
    private static final Icon whiteQueenIcon = new ImageIcon(new ImageIcon("src/Icons/queen_w.png").getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH));

    private static final Icon blackKingIcon = new ImageIcon(new ImageIcon("src/Icons/king_b.png").getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH));
    private static final Icon whiteKingIcon = new ImageIcon(new ImageIcon("src/Icons/king_w.png").getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH));

    static Icon getBlackPawnIcon() {
        return blackPawnIcon;
    }
    static Icon getWhitePawnIcon() {
        return whitePawnIcon;
    }

    static Icon getBlackRookIcon() {
        return blackRookIcon;
    }
    static Icon getWhiteRookIcon() {
        return whiteRookIcon;
    }

    static Icon getBlackKnightIcon() {
        return blackKnightIcon;
    }
    static Icon getWhiteKnightIcon() {
        return whiteKnightIcon;
    }

    static Icon getBlackBishopIcon() {
        return blackBishopIcon;
    }
    static Icon getWhiteBishopIcon() {
        return whiteBishopIcon;
    }

    static Icon getBlackQueenIcon() {
        return blackQueenIcon;
    }
    static Icon getWhiteQueenIcon() {
        return whiteQueenIcon;
    }

    static Icon getBlackKingIcon() {
        return blackKingIcon;
    }
    static Icon getWhiteKingIcon() {
        return whiteKingIcon;
    }
}
