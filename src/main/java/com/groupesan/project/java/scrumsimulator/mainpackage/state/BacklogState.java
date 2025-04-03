package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Backlog;

public abstract class BacklogState {
    protected Backlog backlog;

    BacklogState(Backlog backlog) {
        this.backlog = backlog;
    }

    // Methods to change state, return a string of the state after change
    public abstract String onSelect();

    public abstract String onComplete();

    public abstract String onDelete();
}
