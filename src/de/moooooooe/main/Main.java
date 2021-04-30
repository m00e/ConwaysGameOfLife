package de.moooooooe.main;

import de.moooooooe.main.ConwayUtils.Simulation;
import de.moooooooe.main.GUI.ControlPanel;
import de.moooooooe.main.GUI.GUIFrame;

public class Main {
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
                // Sleeping is necessary for the simulation to run again if "start"-button is pressed.
                sleep(100);
                continue;
            }
            long thisFrame = System.currentTimeMillis();
            float tslf = (float) ((thisFrame - lastFrame) / 1000.0);
            lastFrame = thisFrame;

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
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException exc) {
            exc.printStackTrace();
        }
    }
}
