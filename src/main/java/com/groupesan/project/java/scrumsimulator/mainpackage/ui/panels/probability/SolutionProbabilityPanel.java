package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.probability;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import org.json.JSONArray;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class SolutionProbabilityPanel extends JPanel {
    private final JComboBox<String> blockerComboBox;
    private final ProbabilityRangeControl rangeControl;
    private final List<ProbabilityPanel.ProbabilityRange> ranges = new ArrayList<>();

    public SolutionProbabilityPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize components
        blockerComboBox = new JComboBox<>();
        rangeControl = new ProbabilityRangeControl();

        // Set up the UI
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topPanel.add(new JLabel("Select Blocker:"));
        topPanel.add(blockerComboBox);

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> loadSolutionProbability());
        topPanel.add(loadButton);

        JButton randomizeButton = new JButton("Randomize");
        randomizeButton.addActionListener(e -> randomizeProbabilities());
        topPanel.add(randomizeButton);

        add(topPanel, BorderLayout.NORTH);
        add(rangeControl, BorderLayout.CENTER);

        // Load initial data
        loadPredefinedBlockers();
    }

    private void loadPredefinedBlockers() {
        // Predefined blocker values
        String[] predefinedBlockers = {
                "1",
                "2",
                "3",
                "4"
        };

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(predefinedBlockers);
        blockerComboBox.setModel(model);
    }

    private void loadSolutionProbability() {
        if (blockerComboBox.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No blockers available. Please add blockers first.",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String selectedBlocker = (String) blockerComboBox.getSelectedItem();
        JSONArray probabilities = SimulationStateManager.getSolutionProbabilities();

        for (int i = 0; i < probabilities.length(); i++) {
            JSONObject prob = probabilities.getJSONObject(i);
            if (prob.optString("name", "").equals(selectedBlocker) ||
                    prob.optString("description", "").equals(selectedBlocker)) {
                rangeControl.setMinValue(prob.optInt("min", 40));
                rangeControl.setMaxValue(prob.optInt("max", 80));
                return;
            }
        }
        rangeControl.setMinValue(40);
        rangeControl.setMaxValue(80);
    }

    private void randomizeProbabilities() {
        int min = (int)(Math.random() * 70); // Random between 0-70
        int max = min + 10 + (int)(Math.random() * (100 - min - 10)); // Ensure min < max
        rangeControl.setMinValue(min);
        rangeControl.setMaxValue(max);
    }

    public List<ProbabilityPanel.ProbabilityRange> getProbabilityRanges() {
        ranges.clear();
        if (blockerComboBox.getItemCount() > 0) {
            ranges.add(new ProbabilityPanel.ProbabilityRange(
                    (String) blockerComboBox.getSelectedItem(),
                    rangeControl.getMinValue(),
                    rangeControl.getMaxValue()
            ));
        }
        return ranges;
    }
}