package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.probability;

import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class ProbabilityPanel extends JPanel {
    protected List<ProbabilityRangeControl> rangeControls = new ArrayList<>();
    protected Random random = new Random();

    public ProbabilityPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    protected void addProbabilityControl(String label) {
        ProbabilityRangeControl control = new ProbabilityRangeControl(label);
        rangeControls.add(control);
        add(control);
        add(Box.createVerticalStrut(10));
    }

    public void randomizeAll() {
        for (ProbabilityRangeControl control : rangeControls) {
            int min = random.nextInt(96);
            int max = min + 5 + random.nextInt(95 - min);
            control.setMinValue(min);
            control.setMaxValue(max);
        }
    }

    public List<ProbabilityRange> getProbabilityRanges() {
        List<ProbabilityRange> ranges = new ArrayList<>();
        for (ProbabilityRangeControl control : rangeControls) {
            ranges.add(new ProbabilityRange(
                    control.getLabel(),
                    control.getMinValue(),
                    control.getMaxValue()
            ));
        }
        return ranges;
    }

    public static class ProbabilityRange {
        private final String name;
        private final int min;
        private final int max;

        public ProbabilityRange(String name, int min, int max) {
            this.name = name;
            this.min = min;
            this.max = max;
        }

        public String getName() { return name; }
        public int getMin() { return min; }
        public int getMax() { return max; }

        public JSONObject toJson() {
            JSONObject obj = new JSONObject();
            obj.put("name", name);
            obj.put("min", min);
            obj.put("max", max);
            return obj;
        }
    }

    private static class ProbabilityRangeControl extends JPanel {
        private final String label;
        private final JSlider minSlider;
        private final JSlider maxSlider;
        private final JLabel minValueLabel;
        private final JLabel maxValueLabel;

        public ProbabilityRangeControl(String label) {
            this.label = label;
            this.minSlider = new JSlider(0, 100, 30);
            this.maxSlider = new JSlider(0, 100, 70);
            this.minValueLabel = new JLabel("30%");
            this.maxValueLabel = new JLabel("70%");

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createTitledBorder(label));

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

            add(minPanel);
            add(maxPanel);
        }

        public String getLabel() { return label; }
        public int getMinValue() { return minSlider.getValue(); }
        public int getMaxValue() { return maxSlider.getValue(); }
        public void setMinValue(int value) { minSlider.setValue(value); }
        public void setMaxValue(int value) { maxSlider.setValue(value); }
    }
}