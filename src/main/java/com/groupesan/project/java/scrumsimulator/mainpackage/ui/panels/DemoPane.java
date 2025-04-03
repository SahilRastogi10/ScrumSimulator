package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DemoPane extends JFrame implements BaseComponent {
    private final Player player = new Player("bob", new ScrumRole("demo"));
    private JLabel roleDisplayLabel;
    private final Map<String, JButton> roleButtons = new HashMap<>();

    public DemoPane() {
        this.init();
        player.doRegister();
    }

    /**
     * Initialization of Demo Pane. Demonstrates creation of User stories, Sprints, and Simulation
     * start.
     */
    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Demo");
        setSize(1200, 300);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        // Create buttons for different actions
        JButton sprintsButton = new JButton("Sprints");
        JButton userStoriesButton = new JButton("User Stories");
        JButton updateStoryStatusButton = new JButton("Update User Story Status");
        JButton simulationButton = new JButton("Add User");
        JButton modifySimulationButton = new JButton("Modify Simulation");
        JButton joinSimulationButton = new JButton("Join Simulation");
        JButton simulationSwitchRoleButton = new JButton("Switch Role");
        JButton variantSimulationUIButton = new JButton("Variant Simulation UI");
        JButton sprintUIButton = new JButton("US Selection UI");
        JButton backlogButton = new JButton("Backlog");

        // Store buttons in a map for easy access
        roleButtons.put("Sprints", sprintsButton);
        roleButtons.put("User Stories", userStoriesButton);
        roleButtons.put("Update User Story Status", updateStoryStatusButton);
        roleButtons.put("Add User", simulationButton);
        roleButtons.put("Modify Simulation", modifySimulationButton);
        roleButtons.put("Join Simulation", joinSimulationButton);
        roleButtons.put("Variant Simulation UI", variantSimulationUIButton);
        roleButtons.put("US Selection UI", sprintUIButton);
        roleButtons.put("Product Backlog", backlogButton);

        // Set up action listeners for each button
        sprintsButton.addActionListener(e -> new SprintListPane().setVisible(true));
        userStoriesButton.addActionListener(e -> new UserStoryListPane().setVisible(true));
        updateStoryStatusButton.addActionListener(e -> new UpdateUserStoryPanel().setVisible(true));
        simulationButton.addActionListener(e -> new SimulationPane().setVisible(true));
        modifySimulationButton.addActionListener(e -> new ModifySimulationPane(new SimulationManager()).setVisible(true));
        joinSimulationButton.addActionListener(e -> new SimulationUI().setVisible(true));
        variantSimulationUIButton.addActionListener(e -> new VariantSimulationUI().setVisible(true));
        sprintUIButton.addActionListener(e -> new SprintUIPane(player).setVisible(true));
        backlogButton.addActionListener(e -> {BacklogPane form = new BacklogPane();form.setVisible(true);});

        // Add buttons to the panel with layout constraints
        myJpanel.add(sprintsButton, new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(userStoriesButton, new CustomConstraints(1, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(updateStoryStatusButton, new CustomConstraints(3, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(simulationButton, new CustomConstraints(7, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(modifySimulationButton, new CustomConstraints(5, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(joinSimulationButton, new CustomConstraints(6, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(variantSimulationUIButton, new CustomConstraints(3, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(sprintUIButton, new CustomConstraints(8, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(backlogButton, new CustomConstraints(6, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Button to switch roles
        simulationSwitchRoleButton.addActionListener(e -> {
            SimulationSwitchRolePane feedbackPanelUI = new SimulationSwitchRolePane();
            feedbackPanelUI.setVisible(true);
            feedbackPanelUI.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    String selectedRole = feedbackPanelUI.getSelectedRole();
                    if (selectedRole != null && !selectedRole.isEmpty()) {
                        roleDisplayLabel.setText("Current Role: " + selectedRole);
                        updateButtonVisibility(selectedRole);
                    }
                }
            });
        });

        myJpanel.add(simulationSwitchRoleButton, new CustomConstraints(1, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Panel to display the current role
        JPanel rolePanel = new JPanel();
        rolePanel.setBorder(new LineBorder(Color.BLACK));
        roleDisplayLabel = new JLabel("Current Role: Scrum Master");
        rolePanel.add(roleDisplayLabel);
        myJpanel.add(rolePanel, new CustomConstraints(1, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        JButton SprintBlockerButton = new JButton("Blocker List");
        SprintBlockerButton.addActionListener(
                e -> {
                    SprintBlockerPane sprintBlockerPane = new SprintBlockerPane();
                    sprintBlockerPane.setVisible(true);
                });

        myJpanel.add(
                SprintBlockerButton,
                new CustomConstraints(
                        10, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));


        add(myJpanel);
    }

    private void updateButtonVisibility(String role) {
        // Define which buttons each role can access
        Map<String, String[]> rolePermissions = new HashMap<>();
        rolePermissions.put("Developer", new String[]{"Update User Story Status", "Join Simulation"});
        rolePermissions.put("Product Owner", new String[]{"Start Simulation", "Modify Simulation", "Add User", "US Selection UI"});
        rolePermissions.put("Scrum Master", new String[]{"Sprints", "User Stories", "Modify Simulation", "Join Simulation", "Product Backlog"});

        // Hide all buttons initially
        roleButtons.values().forEach(button -> button.setVisible(false));

        // Show buttons based on the user's role
        if (rolePermissions.containsKey(role)) {
            for (String buttonName : rolePermissions.get(role)) {
                if (roleButtons.containsKey(buttonName)) {
                    roleButtons.get(buttonName).setVisible(true);
                }
            }
        }
    }
}