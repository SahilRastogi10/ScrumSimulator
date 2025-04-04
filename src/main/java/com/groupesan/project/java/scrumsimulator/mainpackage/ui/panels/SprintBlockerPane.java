package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blockers;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SprintBlockerPane extends JFrame {
    private JTable blockerTable;
    private List<Blockers> blockerList;

    public SprintBlockerPane() {
        setTitle("Blockers List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 350);


        String[] columnNames = {"ID", "Severity", "Impact", "Description"};
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

                try {
                    double severity = Double.parseDouble(severityStr);
                    double impact = Double.parseDouble(impactStr);

                    String id = java.util.UUID.randomUUID().toString();

                    Blockers newBlocker = new Blockers(id, description, severity, impact);

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

        buttonPanel.add(newBlockerButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private Object[][] getBlockerData() {
        Object[][] data = new Object[blockerList.size()][4];
        for (int i = 0; i < blockerList.size(); i++) {
            Blockers blocker = blockerList.get(i);
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = blocker.getSeverity();
            data[i][2] = blocker.getImpact();
            data[i][3] = blocker.getDescription();
        }
        return data;
    }

    private void refreshTable() {
        blockerList = BlockerStore.getInstance().getBlockers();
        blockerTable.setModel(new javax.swing.table.DefaultTableModel(
                getBlockerData(),
                new String[]{"ID", "Severity", "Impact", "Description"}
        ));
    }
    private void openBlockerDetailsWindow(int row) {
        if (row < blockerList.size()) {
            Blockers selectedBlocker = blockerList.get(row);

            BlockerDetailsWindow detailsWindow = new BlockerDetailsWindow(selectedBlocker);
            detailsWindow.setVisible(true);
        } else {
            System.out.println("Invalid row selected: " + row);
        }
    }
}