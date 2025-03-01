package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoPane extends JFrame implements BaseComponent {
    private Player player = new Player("bob", new ScrumRole("demo"));
    private JLabel roleDisplayLabel;

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

        // Button for Sprints
        JButton sprintsButton = new JButton("Sprints");
        sprintsButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SprintListPane form = new SprintListPane();
                        form.setVisible(true);
                    }
                });

        // Simulation state and panel
        SimulationStateManager simulationStateManager = new SimulationStateManager();
        SimulationPanel simulationPanel = new SimulationPanel(simulationStateManager);
        myJpanel.add(
                simulationPanel,
                new CustomConstraints(
                        2, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        myJpanel.add(
                sprintsButton,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Button for User Stories
        JButton userStoriesButton = new JButton("User Stories");
        userStoriesButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        UserStoryListPane form = new UserStoryListPane();
                        form.setVisible(true);
                    }
                });

        myJpanel.add(
                userStoriesButton,
                new CustomConstraints(
                        1, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Button for Update User Story Status
        JButton updateStoryStatusButton = new JButton("Update User Story Status");
        updateStoryStatusButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        UpdateUserStoryPanel form = new UpdateUserStoryPanel();
                        form.setVisible(true);
                    }
                });

        myJpanel.add(
                updateStoryStatusButton,
                new CustomConstraints(
                        3, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Simulation button for Demo
        JButton simulationButton = new JButton("Add User");
        simulationButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SimulationPane simulationPane = new SimulationPane();
                        simulationPane.setVisible(true);
                    }
                });

        myJpanel.add(
                simulationButton,
                new CustomConstraints(
                        7, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Modify Simulation button
        JButton modifySimulationButton = new JButton("Modify Simulation");
        modifySimulationButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SimulationManager simulationManager = new SimulationManager();
                        ModifySimulationPane modifySimulationPane =
                                new ModifySimulationPane(simulationManager);
                        modifySimulationPane.setVisible(true);
                    }
                });

        myJpanel.add(
                modifySimulationButton,
                new CustomConstraints(
                        5, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Join Simulation button
        JButton joinSimulationButton = new JButton("Join Simulation");
        joinSimulationButton.addActionListener(
                e -> {
                    SimulationUI simulationUserInterface = new SimulationUI();
                    simulationUserInterface.setVisible(true);
                });

        myJpanel.add(
                joinSimulationButton,
                new CustomConstraints(
                        6, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Switch Role button
        JButton simulationSwitchRoleButton = new JButton("Switch Role");
        simulationSwitchRoleButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SimulationSwitchRolePane feedbackPanelUI = new SimulationSwitchRolePane();
                        feedbackPanelUI.setVisible(true);
                        feedbackPanelUI.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                String selectedRole = feedbackPanelUI.getSelectedRole();
                                if (selectedRole != null && !selectedRole.isEmpty()) {
                                    roleDisplayLabel.setText("Current Role: " + selectedRole);
                                }
                            }
                        });
                    }
                });

        myJpanel.add(
                simulationSwitchRoleButton,
                new CustomConstraints(
                        1, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Variant Simulation UI button
        JButton variantSimulationUIButton = new JButton("Variant Simulation UI");
        variantSimulationUIButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        VariantSimulationUI variantSimulationUI = new VariantSimulationUI();
                        variantSimulationUI.setVisible(true);
                    }
                });

        myJpanel.add(
                variantSimulationUIButton,
                new CustomConstraints(
                        3, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Sprint UI Selection button
        JButton SprintUIButton = new JButton("US Selection UI");
        SprintUIButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SprintUIPane sprintUIPane = new SprintUIPane(player);
                        sprintUIPane.setVisible(true);
                    }
                });

        myJpanel.add(
                SprintUIButton,
                new CustomConstraints(
                        8, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Create a panel for the current role display with a border
        JPanel rolePanel = new JPanel();
        rolePanel.setBorder(new LineBorder(Color.BLACK)); // Add a border to the panel
        roleDisplayLabel = new JLabel("Current Role: Scrum Master");
        rolePanel.add(roleDisplayLabel);

        // Add the role panel below the Switch Role button
        myJpanel.add(
                rolePanel,
                new CustomConstraints(
                        1, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }
}