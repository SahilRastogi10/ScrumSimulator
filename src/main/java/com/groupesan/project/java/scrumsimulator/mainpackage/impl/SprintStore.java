package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class SprintStore {
    private static SprintStore sprintStore;

    public static SprintStore getInstance() {
        if (sprintStore == null) {
            sprintStore = new SprintStore();
        }
        return sprintStore;
    }

    private List<Sprint> sprints;
    private List<List<Sprint>> savedStates;  // Added for managing history

    private SprintStore() {
        sprints = new ArrayList<>();
        savedStates = new ArrayList<>();
    }

    public void addSprint(Sprint sprint) {
        sprints.add(sprint);
    }

    public List<Sprint> getSprints() {
        return new ArrayList<>(sprints);
    }

    // Save a deep copy of current sprints
    public void saveCurrentState() {
        List<Sprint> snapshot = new ArrayList<>();
        for (Sprint s : sprints) {
            snapshot.add(new Sprint(s));
        }
        savedStates.add(snapshot);
    }

    // Restore to a previously saved state by index
    public boolean restoreState(int index) {
        if (index >= 0 && index < savedStates.size()) {
            List<Sprint> snapshot = savedStates.get(index);
            sprints = new ArrayList<>();
            for (Sprint s : snapshot) {
                sprints.add(new Sprint(s));
            }
            return true;
        }
        return false;
    }

    public int getSavedStatesCount() {
        return savedStates.size();
    }
}
