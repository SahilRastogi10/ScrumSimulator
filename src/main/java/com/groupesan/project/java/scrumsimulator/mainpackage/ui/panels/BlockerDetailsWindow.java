package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blockers;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class BlockerDetailsWindow extends JFrame{

    public BlockerDetailsWindow(Blockers blocker) {
        setTitle("Blocker Solution");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Severity", "Impact", "Solution"};


        String solutionText = "Solution" + blocker.getId();

        Object[][] data = {
                {blocker.getId(), blocker.getSeverity(), blocker.getImpact(), solutionText}
        };

        JTable detailsTable = new JTable(data, columnNames);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < detailsTable.getColumnCount(); i++) {
            detailsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(detailsTable);
        detailsTable.setFillsViewportHeight(true);

        add(scrollPane, BorderLayout.CENTER);
    }
}
