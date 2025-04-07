package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.probability;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProbabilityConfigPane extends JFrame {
    private final BlockerProbabilityPanel blockerPanel;
    private final SolutionProbabilityPanel solutionPanel;

    public ProbabilityConfigPane() {
        setTitle("Probability Configuration");
        setSize(650, 450);
        setLayout(new BorderLayout());

        // Initialize panels
        blockerPanel = new BlockerProbabilityPanel();
        solutionPanel = new SolutionProbabilityPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Blockers", blockerPanel);
        tabbedPane.addTab("Solutions", solutionPanel);

        add(tabbedPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        JButton closeButton = new JButton("Close");

        saveButton.addActionListener(this::saveProbabilities);
        closeButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveProbabilities(ActionEvent e) {
        try {
            if (blockerPanel.getProbabilityRanges().isEmpty() ||
                    solutionPanel.getProbabilityRanges().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please configure probabilities for both blockers and solutions",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            SimulationStateManager.saveBlockerProbabilities(blockerPanel.getProbabilityRanges());
            SimulationStateManager.saveSolutionProbabilities(solutionPanel.getProbabilityRanges());
            JOptionPane.showMessageDialog(this, "Probabilities saved successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error saving probabilities: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}