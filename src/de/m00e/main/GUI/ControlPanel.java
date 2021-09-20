package de.moooooooe.main.GUI;

import de.moooooooe.main.ConwayMain;
import de.moooooooe.main.ConwayUtils.Simulation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private JButton startBtn, stopBtn, newBtn;
    private static JLabel genLbl, speedLbl;
    private static JSlider slider;
    private JPanel[] panels;

    public ControlPanel() {
        this.setLayout(new BorderLayout());
        init();

        this.add(panels[1], BorderLayout.WEST);
        this.add(panels[0], BorderLayout.CENTER);
        this.add(panels[2], BorderLayout.EAST);
    }

    private void init() {
        // Initialize all panels
        panels = new JPanel[3];
        for(int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
        }

        /* Button Panel */
        startBtn = new JButton("Start");
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConwayMain.setRunning(true);
            }
        });

        stopBtn = new JButton("Stop");
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConwayMain.setRunning(false);
            }
        });

        newBtn = new JButton("New Pattern");
        newBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConwayMain.setRunning(false);
                Simulation.initSimulation();
                setGen(0);
            }
        });
        panels[0].add(startBtn);
        panels[0].add(stopBtn);
        panels[0].add(newBtn);

        // Speed Panel
        speedLbl = new JLabel("Speed");
        speedLbl.setFont(new Font("SansSerif", Font.PLAIN, 18));

        // Value of slider = circa amount of generations per second.
        slider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 1);
        slider.setMajorTickSpacing(500);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                GUIFrame.editPause(slider.getValue());
            }
        });

        panels[1].add(speedLbl);
        panels[1].add(slider);

        // Generation-Display Panel
        genLbl = new JLabel("Generation: " + Simulation.getGeneration());
        genLbl.setFont(new Font("SansSerif", Font.PLAIN, 18));
        panels[2].add(genLbl);
    }

    /**
     * Set the generation counter to a certain integer.
     * @param gen
     */
    public static void setGen(int gen) {
        // Have to add 1 to generation count because 0 will be displayed two times.
        genLbl.setText("Generation: " + gen);
    }
}