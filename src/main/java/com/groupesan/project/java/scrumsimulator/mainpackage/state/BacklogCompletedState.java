package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Backlog;

public class BacklogCompletedState extends BacklogState {

    public BacklogCompletedState(Backlog backlog) {
        super(backlog);
    }

    @Override
    public String onSelect() {
        return "Completed";
    }

    @Override
    public String onComplete() {
        return "Completed";
    }

    @Override
    public String onDelete() {
        backlog.changeState(new BacklogDeletedState(backlog));
        return "Deleted";
    }
}
