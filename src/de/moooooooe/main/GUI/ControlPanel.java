package de.moooooooe.main.GUI;

import de.moooooooe.main.ConwayUtils.Simulation;
import de.moooooooe.main.Main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private JButton startButton, stopButton, newButton;
    private static JLabel genLabel, speedLabel;
    private static JSlider speedController;
    private JPanel[] panels;

    public ControlPanel() {
        this.setLayout(new BorderLayout());
        init();

        this.add(panels[1], BorderLayout.WEST);
        this.add(panels[0], BorderLayout.CENTER);
        this.add(panels[2], BorderLayout.EAST);
    }

    private void init() {
        // Init all panels
        panels = new JPanel[3];
        for(int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
        }

        /* Button Panel */
        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.setRunning(true);
            }
        });

        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.setRunning(false);

            }
        });

        newButton = new JButton("New");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.setRunning(false);
                Simulation.initSimulation();
                setGen(0);
            }
        });
        panels[0].add(startButton);
        panels[0].add(stopButton);
        panels[0].add(newButton);

        /* Speed Panel */
        speedLabel = new JLabel("Speed");
        speedLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        // Value of JSlider = circa amount of generations per second.
        speedController = new JSlider(JSlider.HORIZONTAL, 1, 1000, 1);
        speedController.setMajorTickSpacing(500);
        speedController.setPaintTicks(true);
        speedController.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                GUIFrame.editPause(speedController.getValue());
            }
        });

        panels[1].add(speedLabel);
        panels[1].add(speedController);

        /* Generation-Display Panel */
        genLabel = new JLabel("Generation: " + Simulation.getGeneration());
        genLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        panels[2].add(genLabel);
    }

    /**
     * Set the counter to a certain integer.
     * @param gen
     */
    public static void setGen(int gen) {
        // Have to add 1 to generation count because 0 will be displayed two times.
        genLabel.setText("Generation: " + gen);
    }
}