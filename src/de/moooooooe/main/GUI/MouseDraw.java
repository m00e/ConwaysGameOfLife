package de.moooooooe.main.GUI;

import de.moooooooe.main.ConwayUtils.Cell;
import de.moooooooe.main.ConwayUtils.Simulation;
import de.moooooooe.main.ConwayMain;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class MouseDraw implements MouseInputListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getLocationOnScreen().x, yCoord = e.getLocationOnScreen().y;
        if(xCoord < GUIFrame.getWIDTH() &&
                yCoord < GUIFrame.getHEIGHT() &&
                !ConwayMain.isRunning()) {
            System.out.println(xCoord);
            Simulation.getCells()[xCoord/Cell.CELL_SIZE][yCoord/Cell.CELL_SIZE].setAlive(true);
            Simulation.getCells()[xCoord/Cell.CELL_SIZE][yCoord/Cell.CELL_SIZE].setNextRound(true);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int xCoord = e.getLocationOnScreen().x, yCoord = e.getLocationOnScreen().y;
        if(xCoord < GUIFrame.getWIDTH() &&
                yCoord < GUIFrame.getHEIGHT() &&
                !ConwayMain.isRunning()) {
            Simulation.getCells()[xCoord][yCoord].setAlive(true);
            Simulation.getCells()[xCoord][yCoord].setNextRound(true);
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}
