package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.User;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class SimulationPane extends JFrame {
    private static final List<String> allowedRoleNames =
            Arrays.asList("Developer", "Scrum Master", "Product Owner");

    public SimulationPane() {
        setTitle("Add New User");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Username field
        mainPanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        mainPanel.add(usernameField);

        // User type
        mainPanel.add(new JLabel("User Type:"));
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup typeGroup = new ButtonGroup();
        JRadioButton playerButton = new JRadioButton("Player", true);
        JRadioButton teacherButton = new JRadioButton("Teacher");
        typeGroup.add(playerButton);
        typeGroup.add(teacherButton);
        typePanel.add(playerButton);
        typePanel.add(teacherButton);
        mainPanel.add(typePanel);
        mainPanel.add(new JLabel()); // Empty cell for layout

        // Role selection
        mainPanel.add(new JLabel("Role:"));
        JComboBox<String> roleCombo = new JComboBox<>(allowedRoleNames.toArray(new String[0]));
        mainPanel.add(roleCombo);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add User");
        JButton cancelButton = new JButton("Cancel");

        addButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String role = (String) roleCombo.getSelectedItem();
            boolean isPlayer = playerButton.isSelected();

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username cannot be empty",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                User newUser = isPlayer ?
                        new Player(username, new ScrumRole(role)) :
                        new User(username, new ScrumRole(role));
                newUser.register();

                boolean success = SimulationStateManager.addUserToCurrentSimulation(newUser);

                if (success) {
                    JOptionPane.showMessageDialog(this, "User added successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Failed to add user. User may already exist or simulation not running.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error adding user: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}