package de.moooooooe.main.GUI;

import de.moooooooe.main.ConwayUtils.Simulation;

import javax.swing.*;
import java.awt.*;

public class GUIFrame extends JFrame {

    private static Simulation sim;
    private final static int WIDTH = 1000; // Size of Simulation
    private final static int HEIGHT = 800;
    private static float tslu; // = Time since last update
    private static float pause = 1f;

    public GUIFrame() {
        super("Conway's Game of Life");
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(WIDTH, HEIGHT+50)); // Size of actual window
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        this.add(new GUILabel(), BorderLayout.CENTER);
        this.add(new ControlPanel(), BorderLayout.SOUTH);
        this.addMouseListener(new MouseDraw());

        sim = new Simulation();
        tslu = 0;
    }

    /**
     * Update the simulation if the pause time passed by.
     * @param tslf Time since last frame
     */
    public void updateState(float tslf) {
        tslu += tslf;
        if(tslu > pause) {
            sim.update();
            tslu = 0;
        }
    }

    /**
     * Change pause time respectively amount of generations per second.
     * pause = 1/speedControllerValue
     */
    public static void editPause(int speedControllerValue) {
        pause = (1.0f/(float)speedControllerValue);
    }

    /**
     * Draw the actual simulation.
     */
    private class GUILabel extends JLabel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            sim.draw(g);
        }
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}

