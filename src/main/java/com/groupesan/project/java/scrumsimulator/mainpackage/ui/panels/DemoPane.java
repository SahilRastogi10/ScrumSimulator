package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.dialogs.simulation.SimulationWizard;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.probability.ProbabilityConfigPane;
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

    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Demo");
        setSize(1200, 300);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        // Create buttons for different actions
        JButton createSimulationButton = new JButton("Create New Simulation");
        JButton modifySimulationButton = new JButton("Modify Simulation");
        JButton joinSimulationButton = new JButton("Join Simulation");
        JButton sprintsButton = new JButton("Sprints");
        JButton userStoriesButton = new JButton("User Stories");
        JButton spikeStoriesButton = new JButton("Spike Stories");
        JButton updateStoryStatusButton = new JButton("Update User Story Status");
        JButton simulationButton = new JButton("Add User");
        JButton usersButton = new JButton("Users");
        JButton simulationSwitchRoleButton = new JButton("Switch Role");
        JButton sprintUIButton = new JButton("US Selection UI");
        JButton backlogButton = new JButton("Backlog");
        JButton probabilityConfigButton = new JButton("Probability Config");
        JButton sprintBlockerButton = new JButton("Blocker List");

        // Store buttons in a map for easy access
        roleButtons.put("Modify Simulation", modifySimulationButton);
        roleButtons.put("Create New Simulation", createSimulationButton);
        roleButtons.put("Join Simulation", joinSimulationButton);
        roleButtons.put("Sprints", sprintsButton);
        roleButtons.put("User Stories", userStoriesButton);
        roleButtons.put("Spike Stories", spikeStoriesButton);
        roleButtons.put("Update User Story Status", updateStoryStatusButton);
        roleButtons.put("Add User", simulationButton);
        roleButtons.put("Users", usersButton);
        roleButtons.put("US Selection UI", sprintUIButton);
        roleButtons.put("Product Backlog", backlogButton);
        roleButtons.put("Probability Config", probabilityConfigButton);
        roleButtons.put("Blocker List", sprintBlockerButton);

        // Set up action listeners for each button
        createSimulationButton.addActionListener(e -> new SimulationWizard(simulation -> {
            System.out.println(simulation);
        }).setVisible(true));

        modifySimulationButton.addActionListener(e -> {
            JFrame modifySimulationFrame = new JFrame("Modify Simulation");
            modifySimulationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            modifySimulationFrame.add(ModifySimulationPane.create(true));  // true for modification mode
            modifySimulationFrame.pack();
            modifySimulationFrame.setVisible(true);
        });

        joinSimulationButton.addActionListener(e -> new SimulationUI().setVisible(true));
        sprintsButton.addActionListener(e -> new SprintListPane().setVisible(true));
        userStoriesButton.addActionListener(e -> new UserStoryListPane().setVisible(true));
        spikeStoriesButton.addActionListener(e -> new SpikeStoryListPane().setVisible(true));
        updateStoryStatusButton.addActionListener(e -> new UpdateUserStoryPanel().setVisible(true));
        simulationButton.addActionListener(e -> new SimulationPane().setVisible(true));
        usersButton.addActionListener(e -> new UserListPane().setVisible(true));
        sprintUIButton.addActionListener(e -> new SprintUIPane(player).setVisible(true));
        backlogButton.addActionListener(e -> {BacklogPane form = new BacklogPane();form.setVisible(true);});
        probabilityConfigButton.addActionListener(e -> new ProbabilityConfigPane().setVisible(true));
        sprintBlockerButton.addActionListener(e -> {
            SprintBlockerPane sprintBlockerPane = new SprintBlockerPane();
            sprintBlockerPane.setVisible(true);
        });

        // Add buttons to the panel with layout constraints
        myJpanel.add(createSimulationButton, new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(modifySimulationButton, new CustomConstraints(1, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(joinSimulationButton, new CustomConstraints(2, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        myJpanel.add(sprintsButton, new CustomConstraints(0, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(userStoriesButton, new CustomConstraints(1, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(updateStoryStatusButton, new CustomConstraints(2, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(backlogButton, new CustomConstraints(3, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(sprintUIButton, new CustomConstraints(4, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        myJpanel.add(probabilityConfigButton, new CustomConstraints(0, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(sprintBlockerButton, new CustomConstraints(1, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(spikeStoriesButton, new CustomConstraints(2, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        myJpanel.add(usersButton, new CustomConstraints(0, 3, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        myJpanel.add(simulationButton, new CustomConstraints(1, 3, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

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

        myJpanel.add(simulationSwitchRoleButton, new CustomConstraints(7, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Panel to display the current role
        JPanel rolePanel = new JPanel();
        rolePanel.setBorder(new LineBorder(Color.BLACK));
        roleDisplayLabel = new JLabel("Current Role: Scrum Master");
        rolePanel.add(roleDisplayLabel);
        myJpanel.add(rolePanel, new CustomConstraints(8, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }

    private void updateButtonVisibility(String role) {
        // Define which buttons each role can access
        Map<String, String[]> rolePermissions = new HashMap<>();
        rolePermissions.put("Developer", new String[]{"Update User Story Status", "Join Simulation", "Variant Simulation UI", "Spike Stories"});
        rolePermissions.put("Product Owner", new String[]{"Start Simulation", "Modify Simulation", "Add User", "US Selection UI", "Spike Stories"});
        rolePermissions.put("Scrum Master", new String[]{"Create New Simulation", "Sprints", "User Stories","Update User Story Status",
                "Probability Config", "Modify Simulation", "Join Simulation", "Add User", "US Selection UI",
                "Product Backlog", "Blocker List", "Users", "Spike Stories"});

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