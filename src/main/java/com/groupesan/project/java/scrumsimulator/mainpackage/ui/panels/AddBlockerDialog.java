package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBlockerDialog extends JDialog {
    private JTextField severityField;
    private JTextField impactField;
    private JTextField descriptionField;
    private boolean confirmed = false;

    public AddBlockerDialog(JFrame parent) {
        super(parent, "Add New Blocker", true);
        setLayout(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 columns, with spacing

        severityField = new JTextField();
        impactField = new JTextField();
        descriptionField = new JTextField();

        add(new JLabel("Severity:"));
        add(severityField);
        add(new JLabel("Impact:"));
        add(impactField);
        add(new JLabel("Description:"));
        add(descriptionField);

        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose();
            }
        });

        cancelButton.addActionListener(e -> dispose());

        add(addButton);
        add(cancelButton);

        pack();
        setLocationRelativeTo(parent);
    }

    public String getSeverity() {
        return severityField.getText();
    }

    public String getImpact() {
        return impactField.getText();
    }

    public String getDescription() {
        return descriptionField.getText();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
