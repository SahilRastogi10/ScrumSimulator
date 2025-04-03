package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;


public class BlockerStore {

    private static BlockerStore blockerStore;

    /**
     * returns the shared instance of the UserStoryStore which contains all user stories in the
     * system.
     *
     * @return
     */
    public static BlockerStore getInstance() {
        if (blockerStore == null) {
            blockerStore = new BlockerStore();
        }
        return blockerStore;
    }


    private List<Blockers> blockers;

    private BlockerStore() {
        blockers = new ArrayList<>();
    }
    public void setBlockers(List<Blockers> blockers){
        this.blockers = blockers;
    }
    public void addBlocker(Blockers blockers) {
        this.blockers.add(blockers);
    }

    public List<Blockers> getBlockers() {
        return new ArrayList<>(blockers);
    }

    public List<String> getBlockersDesc() {
        List<String> descriptions = new ArrayList<>();
        for (Blockers blocker : blockers) {
            descriptions.add(blocker.getDescription());
        }
        return descriptions;
    }



}

