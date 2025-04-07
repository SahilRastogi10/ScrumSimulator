package com.groupesan.project.java.scrumsimulator.mainpackage.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.*;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.DemoPane;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.WizardManager;

import javax.swing.*;
import java.util.List;

public class App {

    // Constructor
    public App() {}

    // Start method to initialize the application
    public void start() {
        // Load the theme
        this.loadTheme();

        // Show the simulation wizard
        WizardManager.get().showSimulationWizard();

        // Run the application logic on the Swing Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Initialize user stories and blockers
            initializeUserStories();
            initializeBlockers();

            // Load the DemoPane form
            DemoPane form = new DemoPane();
            form.setVisible(true);
        });
    }

    // Initialize predefined UserStories and SpikeStories
    private void initializeUserStories() {
        // Create and add User Stories
        UserStory us1 = UserStoryFactory.getInstance().createNewUserStory("predefinedUS1", "description1", 1.0, 5.0);
        us1.doRegister();
        UserStoryStore.getInstance().addUserStory(us1);

        UserStory us2 = UserStoryFactory.getInstance().createNewUserStory("predefinedUS2", "description2", 2.0, 5.0);
        us2.doRegister();
        UserStoryStore.getInstance().addUserStory(us2);

        UserStory us3 = UserStoryFactory.getInstance().createNewUserStory("predefinedUS3", "description3", 3.0, 5.0);
        us3.doRegister();
        UserStoryStore.getInstance().addUserStory(us3);

        // Create and add Spike Story
        SpikeStory sp1 = SpikeStoryFactory.getInstance()
                .createNewSpikeStory("predefinedSP4", "description4", 3.0, 2.0, null);
        sp1.doRegister();
        SpikeStoryStore.getInstance().addSpikeStory(sp1);
    }

    // Initialize Blockers with UserStories and optionally associated SpikeStories
    private void initializeBlockers() {
        List<UserStory> userStories = UserStoryStore.getInstance().getUserStories();
        List<SpikeStory> spikeStories = SpikeStoryStore.getInstance().getSpikeStories();

        if (!userStories.isEmpty()) {
            for (int i = 0; i < userStories.size(); i++) {
                UserStory us = userStories.get(i);
                SpikeStory spike = (i < spikeStories.size()) ? spikeStories.get(i) : null;

                Blockers blocker = BlockerFactory.getInstance().createNewBlocker(
                        "Blocker for " + us.getName(), 1.0, 1.0, us, spike
                );

                BlockerStore.getInstance().addBlocker(blocker);
            }
        } else {
            System.out.println("Error: No user stories available for blocker initialization.");
        }
    }

    // Load the theme using FlatLightLaf
    private void loadTheme() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }
}
