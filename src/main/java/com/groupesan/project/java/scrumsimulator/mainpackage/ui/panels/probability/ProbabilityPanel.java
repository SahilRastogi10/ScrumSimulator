package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.probability;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class ProbabilityPanel extends JPanel {
    protected List<JSlider> sliders = new ArrayList<>();
    protected Random random = new Random();

    public ProbabilityPanel() {
        setLayout(new GridLayout(0, 1, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    protected void addProbabilityControl(String label) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel probLabel = new JLabel(label + " Probability:");

        JSlider slider = new JSlider(0, 100, 50);
        sliders.add(slider);

        JLabel valueLabel = new JLabel("50%");
        slider.addChangeListener(e -> valueLabel.setText(slider.getValue() + "%"));

        panel.add(probLabel, BorderLayout.WEST);
        panel.add(slider, BorderLayout.CENTER);
        panel.add(valueLabel, BorderLayout.EAST);

        add(panel);
    }

    public void randomizeAll() {
        for (JSlider slider : sliders) {
            int randomValue = random.nextInt(101);
            slider.setValue(randomValue);
        }
    }
}