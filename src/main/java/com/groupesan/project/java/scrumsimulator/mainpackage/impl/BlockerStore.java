package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Singleton store to manage Blockers across the Scrum Simulator application.
 */
public class BlockerStore {

    private static BlockerStore blockerStore;

    private List<Blockers> blockers;

    // Private constructor to enforce Singleton pattern
    private BlockerStore() {
        blockers = new ArrayList<>();
    }

    /**
     * Returns the shared instance of the BlockerStore which contains all blockers in the system.
     *
     * @return the singleton instance of BlockerStore
     */
    public static BlockerStore getInstance() {
        if (blockerStore == null) {
            blockerStore = new BlockerStore();
        }
        return blockerStore;
    }

    /**
     * Sets the blockers list. Useful for testing or resetting the store.
     *
     * @param blockers list of Blockers to replace current list
     */
    public void setBlockers(List<Blockers> blockers) {
        this.blockers = (blockers != null) ? new ArrayList<>(blockers) : new ArrayList<>();
    }

    /**
     * Adds a new blocker to the store.
     *
     * @param blocker Blockers object to add
     */
    public void addBlocker(Blockers blocker) {
        if (blocker != null) {
            blockers.add(blocker);
        }
    }

    /**
     * Removes a blocker from the store.
     *
     * @param blocker Blockers object to remove
     */
    public void removeBlocker(Blockers blocker) {
        if (blocker != null) {
            blockers.remove(blocker);
        }
    }

    /**
     * Gets a copy of the current list of blockers.
     *
     * @return unmodifiable copy of blockers list
     */
    public List<Blockers> getBlockers() {
        return new ArrayList<>(blockers);
    }

    /**
     * Returns a list of descriptions of all blockers.
     *
     * @return list of blocker descriptions
     */
    public List<String> getBlockersDesc() {
        List<String> descriptions = new ArrayList<>();
        for (Blockers blocker : blockers) {
            descriptions.add(blocker.getDescription());
        }
        return descriptions;
    }

    /**
     * Associates a SpikeStory with a given blocker.
     *
     * @param blocker    the blocker to associate the spike with
     * @param spikeStory the spike story to associate
     */
    public void associateSpikeStoryWithBlocker(Blockers blocker, SpikeStory spikeStory) {
        if (blocker != null && spikeStory != null) {
            blocker.setAssociatedSpikeStory(spikeStory);
        }
    }

    /**
     * Removes the associated SpikeStory from a blocker.
     *
     * @param blocker the blocker from which the spike story will be removed
     */
    public void removeSpikeStoryFromBlocker(Blockers blocker) {
        if (blocker != null) {
            blocker.setAssociatedSpikeStory(null);
        }
    }
}
