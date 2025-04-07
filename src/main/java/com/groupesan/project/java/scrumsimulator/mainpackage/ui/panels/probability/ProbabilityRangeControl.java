package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.probability;

import javax.swing.*;
import java.awt.*;

public class ProbabilityRangeControl extends JPanel {
    private final JSlider minSlider;
    private final JSlider maxSlider;
    private final JLabel minValueLabel;
    private final JLabel maxValueLabel;

    public ProbabilityRangeControl() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Probability Range"));

        // Initialize components
        minSlider = new JSlider(0, 100, 30);
        maxSlider = new JSlider(0, 100, 70);
        minValueLabel = new JLabel("30%");
        maxValueLabel = new JLabel("70%");

        // Min slider panel
        JPanel minPanel = new JPanel(new BorderLayout());
        minSlider.addChangeListener(e -> {
            minValueLabel.setText(minSlider.getValue() + "%");
            if (minSlider.getValue() > maxSlider.getValue()) {
                maxSlider.setValue(minSlider.getValue());
            }
        });
        minPanel.add(new JLabel("Min Probability:"), BorderLayout.WEST);
        minPanel.add(minSlider, BorderLayout.CENTER);
        minPanel.add(minValueLabel, BorderLayout.EAST);

        // Max slider panel
        JPanel maxPanel = new JPanel(new BorderLayout());
        maxSlider.addChangeListener(e -> {
            maxValueLabel.setText(maxSlider.getValue() + "%");
            if (maxSlider.getValue() < minSlider.getValue()) {
                minSlider.setValue(maxSlider.getValue());
            }
        });
        maxPanel.add(new JLabel("Max Probability:"), BorderLayout.WEST);
        maxPanel.add(maxSlider, BorderLayout.CENTER);
        maxPanel.add(maxValueLabel, BorderLayout.EAST);

        // Add components
        add(minPanel);
        add(maxPanel);
    }

    public int getMinValue() { return minSlider.getValue(); }
    public int getMaxValue() { return maxSlider.getValue(); }
    public void setMinValue(int value) { minSlider.setValue(value); }
    public void setMaxValue(int value) { maxSlider.setValue(value); }
}