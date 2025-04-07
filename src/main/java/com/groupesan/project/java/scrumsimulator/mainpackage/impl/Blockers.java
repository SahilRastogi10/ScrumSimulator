package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

public class Blockers {
    private static int idCounter = 1;
    private String id;
    private String description;
    private double severity;
    private double impact;
    private UserStory associatedUserStory;
    private SpikeStory associatedSpikeStory;

    // Constructor to initialize Blocker with both UserStory and SpikeStory
    public Blockers(String description, double severity, double impact, UserStory associatedUserStory, SpikeStory associatedSpikeStory) {
        this.id = String.valueOf(idCounter++); // Auto-generate ID
        this.description = description;
        this.severity = severity;
        this.impact = impact;
        this.associatedUserStory = associatedUserStory;
        this.associatedSpikeStory = associatedSpikeStory;
    }

    // Constructor for only UserStory
    public Blockers(String description, double severity, double impact, UserStory associatedUserStory) {
        this(description, severity, impact, associatedUserStory, null); // Only associate with UserStory
    }

    // Constructor for only SpikeStory
    public Blockers(String description, double severity, double impact, SpikeStory associatedSpikeStory) {
        this(description, severity, impact, null, associatedSpikeStory); // Only associate with SpikeStory
    }

    // Getters and setters for all fields
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getSeverity() {
        return severity;
    }

    public double getImpact() {
        return impact;
    }

    public UserStory getAssociatedUserStory() {
        return associatedUserStory;
    }

    public void setAssociatedUserStory(UserStory associatedUserStory) {
        this.associatedUserStory = associatedUserStory;
    }

    public SpikeStory getAssociatedSpikeStory() {
        return associatedSpikeStory;
    }

    public void setAssociatedSpikeStory(SpikeStory associatedSpikeStory) {
        this.associatedSpikeStory = associatedSpikeStory;
    }

    @Override
    public String toString() {
        return "Blocker ID: " + id + "\n" +
                "Description: " + description + "\n" +
                "Severity: " + severity + "\n" +
                "Impact: " + impact + "\n" +
                "Associated UserStory: " + (associatedUserStory != null ? associatedUserStory.getName() : "None") + "\n" +
                "Associated SpikeStory ID: " + (associatedSpikeStory != null ? associatedSpikeStory.getId() : "None");
    }
}
