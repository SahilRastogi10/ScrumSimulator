package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationSwitchRolePane extends JFrame {
    private JRadioButton developerRadioButton;
    private JRadioButton scrumMasterRadioButton;
    private JRadioButton productOwnerRadioButton;
    private ButtonGroup roleButtonGroup;
    private JButton switchButton;
    private JLabel currentRoleLabel;
    private String selectedRole;

    public SimulationSwitchRolePane() {
        setTitle("Simulation Status");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JLabel label = new JLabel("Roles:");
        panel.add(label);

        developerRadioButton = new JRadioButton("Developer");
        scrumMasterRadioButton = new JRadioButton("Scrum Master");
        productOwnerRadioButton = new JRadioButton("Product Owner");
        roleButtonGroup = new ButtonGroup();
        roleButtonGroup.add(developerRadioButton);
        roleButtonGroup.add(scrumMasterRadioButton);
        roleButtonGroup.add(productOwnerRadioButton);
        panel.add(developerRadioButton);
        panel.add(scrumMasterRadioButton);
        panel.add(productOwnerRadioButton);

        currentRoleLabel = new JLabel("Current Role: None");
        panel.add(currentRoleLabel);

        switchButton = new JButton("Switch Role");
        switchButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        onSwitchButtonClicked();
                    }
                });

        setLayout(new BorderLayout());
        add(switchButton, BorderLayout.SOUTH);
        add(panel);
    }

    private void onSwitchButtonClicked() {
        selectedRole = "";

        if (developerRadioButton.isSelected()) {
            selectedRole = "Developer";
        } else if (scrumMasterRadioButton.isSelected()) {
            selectedRole = "Scrum Master";
        } else if (productOwnerRadioButton.isSelected()) {
            selectedRole = "Product Owner";
        }

        if (!selectedRole.isEmpty()) {
            currentRoleLabel.setText("Current Role: " + selectedRole);
            JOptionPane.showMessageDialog(
                    null, "Switched to " + selectedRole, "Role Switching", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Failed to Switch Role",
                    "Role Switching Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        roleButtonGroup.clearSelection();
        dispose();
    }

    public String getSelectedRole() {
        return selectedRole;
    }
}