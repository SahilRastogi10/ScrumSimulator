package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Backlog;

public class BacklogDeletedState extends BacklogState {

    public BacklogDeletedState(Backlog backlog) {
        super(backlog);
    }

    @Override
    public String onSelect() {
        return "Deleted";
    }

    @Override
    public String onComplete() {
        return "Deleted";
    }

    @Override
    public String onDelete() {
        return "Deleted";
    }
}
