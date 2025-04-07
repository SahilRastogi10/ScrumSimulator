package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blockers;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SprintBlockerPane extends JFrame {
    private JTable blockerTable;
    private List<Blockers> blockerList;

    public SprintBlockerPane() {
        setTitle("Blockers List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 350);

        String[] columnNames = {"ID", "Severity", "Impact", "Description", "User Story", "Spike Story ID"};
        blockerList = BlockerStore.getInstance().getBlockers();

        if (blockerList == null || blockerList.isEmpty()) {
            JLabel noBlockersLabel = new JLabel("No blockers were found.");
            add(noBlockersLabel, BorderLayout.CENTER);
            return;
        }

        Object[][] data = getBlockerData();
        blockerTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(blockerTable);
        blockerTable.setFillsViewportHeight(true);

        JButton newBlockerButton = new JButton("Add a new blocker");
        newBlockerButton.addActionListener(e -> {
            AddBlockerDialog dialog = new AddBlockerDialog(this);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                String severityStr = dialog.getSeverity();
                String impactStr = dialog.getImpact();
                String description = dialog.getDescription();
                UserStory selectedUserStory = dialog.getSelectedUserStory(); // Get selected user story
                SpikeStory selectedSpikeStory = dialog.getSelectedSpikeStory(); // Get selected SpikeStory

                try {
                    double severity = Double.parseDouble(severityStr);
                    double impact = Double.parseDouble(impactStr);

                    String id = java.util.UUID.randomUUID().toString(); // Generate ID as String

                    // Create Blocker with UserStory and SpikeStory
                    Blockers newBlocker = new Blockers(description, severity, impact, selectedUserStory, selectedSpikeStory);
                    BlockerStore.getInstance().addBlocker(newBlocker);

                    refreshTable();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid severity and impact.");
                }
            }
        });

        JButton blockerSolutionsButton = new JButton("Blocker Solutions");
        blockerSolutionsButton.addActionListener(e -> {
            int selectedRow = blockerTable.getSelectedRow();
            if (selectedRow != -1) {
                Blockers selectedBlocker = blockerList.get(selectedRow);
                new BlockerDetailsWindow(selectedBlocker).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a blocker from the list first.");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newBlockerButton);
        buttonPanel.add(blockerSolutionsButton);

        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private Object[][] getBlockerData() {
        Object[][] data = new Object[blockerList.size()][6];
        for (int i = 0; i < blockerList.size(); i++) {
            Blockers blocker = blockerList.get(i);
            UserStory userStory = blocker.getAssociatedUserStory();
            SpikeStory spikeStory = blocker.getAssociatedSpikeStory();

            String userStoryName = (userStory != null) ? userStory.getName() : "None";
            String spikeId = (spikeStory != null &&
                    spikeStory.getLinkedUserStory() != null &&
                    spikeStory.getLinkedUserStory().equals(userStory))
                    ? spikeStory.getId().toString() : "None";  // Ensure spike ID is a String

            data[i][0] = blocker.getId();  // Ensure the ID is displayed correctly
            data[i][1] = blocker.getSeverity();
            data[i][2] = blocker.getImpact();
            data[i][3] = blocker.getDescription();
            data[i][4] = userStoryName;
            data[i][5] = spikeId; // Display SpikeStory ID
        }
        return data;
    }

    private void refreshTable() {
        blockerList = BlockerStore.getInstance().getBlockers();
        blockerTable.setModel(new javax.swing.table.DefaultTableModel(
                getBlockerData(),
                new String[]{"ID", "Severity", "Impact", "Description", "User Story", "Spike Story ID"}
        ));
    }
}
