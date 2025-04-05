package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.probability;

import javax.swing.*;
import java.awt.*;

public class ProbabilityConfigPane extends JFrame {
    private JTabbedPane tabbedPane;

    public ProbabilityConfigPane() {
        setTitle("Probability Configuration");
        setSize(400, 300);
        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Blockers", new BlockerProbabilityPanel());
        tabbedPane.addTab("Solutions", new SolutionProbabilityPanel());

        add(tabbedPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton randomizeAllButton = new JButton("Randomize All");
        JButton closeButton = new JButton("Close");

        randomizeAllButton.addActionListener(e -> {
            ((ProbabilityPanel)tabbedPane.getSelectedComponent()).randomizeAll();
        });

        closeButton.addActionListener(e -> dispose());

        buttonPanel.add(randomizeAllButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}