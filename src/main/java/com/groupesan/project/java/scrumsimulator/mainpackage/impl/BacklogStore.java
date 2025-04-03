package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class BacklogStore {

    private static BacklogStore backlogStore;

    public static BacklogStore getInstance() {
        if (backlogStore == null) {
            backlogStore = new BacklogStore();
        }
        return backlogStore;
    }

    private List<Backlog> backlogs;

    private BacklogStore() {
        backlogs = new ArrayList<>();
    }

    public void addBacklog(Backlog backlog) {
        backlogs.add(backlog);
    }

    public void removeBacklog(Backlog backlog) {
            backlogs.remove(backlog);
    }
    public List<Backlog> getBacklogs() {
        return new ArrayList<>(backlogs);
    }
}
