package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blockers;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStoryStore;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BlockerDetailsWindow extends JFrame {

    private final JTable detailsTable;
    private final DefaultTableModel tableModel;
    private final JComboBox<SpikeStory> spikeStoryComboBox;

    public BlockerDetailsWindow(Blockers blocker) {
        setTitle("Blocker Solution");
        setSize(600, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Severity", "Impact", "User Story", "Spike Story", "Solution"};

        // Create table model
        tableModel = new DefaultTableModel(columnNames, 0);
        detailsTable = new JTable(tableModel);
        updateTableData(blocker);

        // Center align the table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < detailsTable.getColumnCount(); i++) {
            detailsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(detailsTable);
        detailsTable.setFillsViewportHeight(true);

        spikeStoryComboBox = new JComboBox<>(SpikeStoryStore.getInstance().getSpikeStories().toArray(new SpikeStory[0]));
        spikeStoryComboBox.setSelectedItem(blocker.getAssociatedSpikeStory());

        JButton updateSpikeButton = new JButton("Update Spike Story");
        updateSpikeButton.addActionListener(e -> {
            SpikeStory selectedSpikeStory = (SpikeStory) spikeStoryComboBox.getSelectedItem();
            blocker.setAssociatedSpikeStory(selectedSpikeStory);
            JOptionPane.showMessageDialog(this, "Spike Story updated successfully.");
            updateTableData(blocker); // Refresh table after update
        });

        JButton deleteButton = new JButton("Delete Blocker");
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this blocker?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                BlockerStore.getInstance().removeBlocker(blocker);
                JOptionPane.showMessageDialog(this, "Blocker deleted successfully.");
                dispose();
            }
        });

        JPanel spikeStoryPanel = new JPanel(new BorderLayout());
        spikeStoryPanel.add(new JLabel("Select Associated Spike Story:"), BorderLayout.NORTH);
        spikeStoryPanel.add(spikeStoryComboBox, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateSpikeButton);
        buttonPanel.add(deleteButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(spikeStoryPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void updateTableData(Blockers blocker) {
        tableModel.setRowCount(0); // Clear existing rows

        String userStoryName = blocker.getAssociatedUserStory() != null
                ? blocker.getAssociatedUserStory().getName()
                : "None";

        String spikeStoryName = blocker.getAssociatedSpikeStory() != null
                ? blocker.getAssociatedSpikeStory().getName()
                : "None";

        String solutionText = "Solution for blocker " + blocker.getId();

        Object[] rowData = {
                blocker.getId(),
                blocker.getSeverity(),
                blocker.getImpact(),
                userStoryName,
                spikeStoryName,
                solutionText
        };

        tableModel.addRow(rowData);
    }
}
