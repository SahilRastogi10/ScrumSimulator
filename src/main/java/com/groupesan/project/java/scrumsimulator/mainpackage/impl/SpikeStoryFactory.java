package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

public class SpikeStoryFactory {

    private static SpikeStoryFactory spikeStoryFactory;

    public static SpikeStoryFactory getInstance() {
        if (spikeStoryFactory == null) {
            spikeStoryFactory = new SpikeStoryFactory();
        }
        return spikeStoryFactory;
    }

    private SpikeStoryFactory() {}

    public SpikeStory createNewSpikeStory(String name, String description, double pointValue, double  businessValue, UserStory linkedUserStory) {
        return new SpikeStory(name, description, pointValue, businessValue, linkedUserStory);
    }
}
