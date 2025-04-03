package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ProductBacklog {
    private List<String> userStories;

    public ProductBacklog() {
        userStories = new ArrayList<>();
    }

    // Add a new user story to the product backlog
    public void addUserStory(String story) {
        userStories.add(story);
    }

    // List all user stories in the product backlog
    public List<String> listUserStories() {
        return userStories;
    }
}

class SprintBacklog {
    private List<String> userStories;
    private boolean sprintStarted;

    public SprintBacklog() {
        userStories = new ArrayList<>();
        sprintStarted = false;
    }

    // Add user story to sprint backlog
    public void addUserStory(String story) {
        if (!sprintStarted) {
            userStories.add(story);
        } else {
            System.out.println("Error: Cannot add user stories. Sprint cycle already started.");
        }
    }

    // Finalize the sprint backlog and start the sprint
    public void finalizeSprintBacklog() {
        sprintStarted = true;
        System.out.println("Sprint backlog finalized. Sprint cycle started.");
    }

    // List all user stories in the sprint backlog
    public List<String> listUserStories() {
        return userStories;
    }
}

class ScrumMaster {
    private ProductBacklog productBacklog;
    private SprintBacklog sprintBacklog;

    public ScrumMaster(ProductBacklog productBacklog, SprintBacklog sprintBacklog) {
        this.productBacklog = productBacklog;
        this.sprintBacklog = sprintBacklog;
    }

    // Manually select and add user stories to the sprint backlog
    public void addStoriesToSprintBacklog(List<String> selectedStories) {
        for (String story : selectedStories) {
            sprintBacklog.addUserStory(story);
        }
        System.out.println("Added " + selectedStories.size() + " user stories to sprint backlog.");
    }

    // Randomly select user stories from product backlog
    public void randomSelectionForSprint(int numStories) {
        List<String> selectedStories = new ArrayList<>();
        List<String> productBacklogStories = productBacklog.listUserStories();
        Random rand = new Random();

        // Ensure there are enough user stories in the backlog
        if (numStories <= productBacklogStories.size()) {
            while (selectedStories.size() < numStories) {
                String randomStory = productBacklogStories.get(rand.nextInt(productBacklogStories.size()));
                if (!selectedStories.contains(randomStory)) {
                    selectedStories.add(randomStory);
                }
            }
            addStoriesToSprintBacklog(selectedStories);
        } else {
            System.out.println("Error: Not enough user stories in product backlog.");
        }
    }
}

class SprintCycle {
    private boolean sprintStarted;

    public SprintCycle() {
        sprintStarted = false;
    }

    // Start the sprint cycle
    public void startSprint() {
        if (sprintStarted) {
            System.out.println("Sprint already started. Cannot modify the sprint backlog.");
        } else {
            sprintStarted = true;
            System.out.println("Sprint cycle started.");
        }
    }

    // Stop the sprint cycle
    public void stopSprint() {
        sprintStarted = false;
        System.out.println("Sprint cycle stopped.");
    }

    public boolean isSprintStarted() {
        return sprintStarted;
    }
}

public class SprintBacklogSimulator {
    public static void main(String[] args) {
        // Initialize product backlog, sprint backlog, and scrum master
        ProductBacklog productBacklog = new ProductBacklog();
        SprintBacklog sprintBacklog = new SprintBacklog();
        ScrumMaster scrumMaster = new ScrumMaster(productBacklog, sprintBacklog);
        SprintCycle sprintCycle = new SprintCycle();

        // Add user stories to the product backlog
        productBacklog.addUserStory("User Story 1: As a user, I should be able to login.");
        productBacklog.addUserStory("User Story 2: As a user, I should be able to register.");
        productBacklog.addUserStory("User Story 3: As a user, I should be able to view my profile.");
        productBacklog.addUserStory("User Story 4: As a user, I should be able to reset my password.");

        // Scrum Master adds user stories to the sprint backlog
        scrumMaster.addStoriesToSprintBacklog(List.of("User Story 1", "User Story 2"));

        // Start the sprint cycle
        sprintCycle.startSprint();

        // Attempt to add more stories to the sprint backlog after the sprint has started
        scrumMaster.addStoriesToSprintBacklog(List.of("User Story 3"));

        // Finalize the sprint backlog
        sprintBacklog.finalizeSprintBacklog();

        // Attempt to add more stories after the sprint cycle has started
        scrumMaster.addStoriesToSprintBacklog(List.of("User Story 4"));

        // Output the lists of user stories
        System.out.println("Product Backlog: " + productBacklog.listUserStories());
        System.out.println("Sprint Backlog: " + sprintBacklog.listUserStories());
    }
}
