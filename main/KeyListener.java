package main;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    private boolean zPressed = false;
    private boolean ctrlPressed = false;

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            ctrlPressed = true;
            if (zPressed && !Move.turnHistory.isEmpty()) Move.undoMove();
        } else if (e.getKeyCode() == KeyEvent.VK_Z) {
            zPressed = true;
            if (ctrlPressed && !Move.turnHistory.isEmpty()) Move.undoMove();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            zPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            ctrlPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
