package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

public class Blockers {
    private static int idCounter = 1; // Static counter for sequential IDs
    private String id;
    private String description;
    private double severity;
    private double impact;


    public Blockers(String id, String description, double severity, double impact) {
        this.id = String.valueOf(idCounter++); // Assign and increment the sequential ID
        this.description = description;
        this.severity = severity;
        this.impact = impact;
    }

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

}

