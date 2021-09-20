package de.moooooooe.main;

import de.moooooooe.main.ConwayUtils.Simulation;
import de.moooooooe.main.GUI.ControlPanel;
import de.moooooooe.main.GUI.GUIFrame;

public class ConwayMain {
    private static boolean running;
    private static long lastFrame;
    private static GUIFrame f;

    public static void main(String[] args) {
        f = new GUIFrame();
        f.setVisible(true);

        running = true;
        lastFrame = System.currentTimeMillis();
        while(true) {
            if(!running) {
                // Stop button doesn't work properly without 100ms delay.
                try {
                    Thread.sleep(100);
                } catch(InterruptedException exc) {
                    exc.printStackTrace();
                }
                continue;
            }
            long currFrame = System.currentTimeMillis();
            float tslf = (float) ((currFrame - lastFrame) / 1000.0);
            lastFrame = currFrame;

            f.updateState(tslf);
            f.repaint();

            ControlPanel.setGen(Simulation.getGeneration());
        }
    }

    public static void setRunning(boolean run) {
        running = run;
    }

    public static boolean isRunning() {
        return running;
    }
}
