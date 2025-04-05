package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;

public class UserListPane extends JFrame {
    public UserListPane() {
        setTitle("User List");
        setSize(500, 400);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JTextArea userListArea = new JTextArea();
        userListArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(userListArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Load users
        JSONArray users = SimulationStateManager.getCurrentSimulationUsers();
        StringBuilder userList = new StringBuilder();

        if (users.length() == 0) {
            userList.append("No users added yet.");
        } else {
            userList.append(String.format("%-20s %-20s %-15s\n", "Username", "Role", "Type"));
            userList.append("--------------------------------------------------\n");

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                userList.append(String.format("%-20s %-20s %-15s\n",
                        user.getString("name"),
                        user.getString("role"),
                        user.getString("type")));
            }
        }

        userListArea.setText(userList.toString());

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}