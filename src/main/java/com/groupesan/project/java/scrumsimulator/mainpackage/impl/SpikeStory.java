package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumObject;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SpikeStoryState;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SpikeStoryUnselectedState;

import java.util.ArrayList;
import java.util.List;

public class SpikeStory extends ScrumObject {
    private ScrumIdentifier id;
    private String name;
    private String description;
    private double pointValue;
    private double businessValue;
    private SpikeStoryState state;
    private Player owner;
    private UserStory linkedUserStory; // Field to store the associated UserStory

    // Getter for the associated UserStory
    public UserStory getLinkedUserStory() {
        return linkedUserStory;
    }

    // Constructor to initialize SpikeStory with a linked UserStory
    public SpikeStory(String name, String description, double pointValue, double businessValue, UserStory linkedUserStory) {
        this.name = name;
        this.description = description;
        this.pointValue = pointValue;
        this.businessValue = businessValue;
        this.state = new SpikeStoryUnselectedState(this);
        this.linkedUserStory = linkedUserStory; // Associate the UserStory
        register(); // Register to initialize the ID
    }

    // Default constructor
    public SpikeStory(String name, double pointValue, double businessValue) {
        this.name = name;
        this.description = "";
        this.pointValue = pointValue;
        this.businessValue = businessValue;
        this.state = new SpikeStoryUnselectedState(this);
        register(); // Register to initialize the ID
    }

    // Constructor with description
    public SpikeStory(String name, String description, double pointValue, double businessValue) {
        this.name = name;
        this.description = description;
        this.pointValue = pointValue;
        this.businessValue = businessValue;
        this.state = new SpikeStoryUnselectedState(this);
        register(); // Register to initialize the ID
    }

    protected void register() {
        this.id = new ConcreteScrumIdentifier(ScrumIdentifierStoreSingleton.get().getNextId()); // Use ConcreteScrumIdentifier
    }

    public ScrumIdentifier getId() {
        if (!isRegistered()) {
            throw new IllegalStateException(
                    "This SpikeStory has not been registered and does not have an ID yet!");
        }
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPointValue() {
        return pointValue;
    }

    public void setPointValue(double pointValue) {
        this.pointValue = pointValue;
    }

    public double getBusinessValue() {
        return businessValue;
    }

    public void setBusinessValue(double businessValue) {
        this.businessValue = businessValue;
    }

    public List<ScrumObject> getChildren() {
        return new ArrayList<>(); // TODO: implement tasks
    }

    @Override
    public String toString() {
        if (isRegistered()) {
            return this.getId().toString() + " - " + name;
        }
        return "(unregistered) - " + getName();
    }

    public void changeState(SpikeStoryState state) {
        this.state = state;
    }

    public SpikeStoryState getSpikeStoryState() {
        return state;
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public Player getOwner() {
        return this.owner;
    }

    // Make this method public to match the one in ScrumObject
    @Override
    public boolean isRegistered() {
        return id != null;
    }

    // Concrete subclass of ScrumIdentifier
    private static class ConcreteScrumIdentifier extends ScrumIdentifier {
        public ConcreteScrumIdentifier(int value) {
            super(value);
        }

        @Override
        public String toString() {
            return "SpikeStory ID: " + getValue();
        }
    }
}
