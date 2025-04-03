package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Backlog;

public class BacklogSelectedState extends BacklogState {

    public BacklogSelectedState(Backlog backlog) {
        super(backlog);
    }

    @Override
    public String onSelect() {
        backlog.changeState(new BacklogUnselectedState(backlog));
        return "Unselected";
    }

    @Override
    public String onComplete() {
        backlog.changeState(new BacklogCompletedState(backlog));
        return "Completed";
    }

    @Override
    public String onDelete() {
        backlog.changeState(new BacklogDeletedState(backlog));
        return "Deleted";
    }
}
