package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

public class BlockerFactory {
    private static BlockerFactory blockerFactory;

    // Singleton instance retrieval
    public static BlockerFactory getInstance() {
        if (blockerFactory == null) {
            blockerFactory = new BlockerFactory();
        }
        return blockerFactory;
    }

    // Private constructor to prevent direct instantiation
    private BlockerFactory() {}

    // Create a new Blocker with both UserStory and SpikeStory associated
    public Blockers createNewBlocker(String description, double severity, double impact, UserStory associatedUserStory, SpikeStory associatedSpikeStory) {
        // Just call the constructor — Blockers will generate ID internally
        return new Blockers(description, severity, impact, associatedUserStory, associatedSpikeStory);
    }
}
