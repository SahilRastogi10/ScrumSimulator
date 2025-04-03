package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Backlog;

public class BacklogUnselectedState extends BacklogState {

    public BacklogUnselectedState(Backlog backlog) {
        super(backlog);
    }

    @Override
    public String onSelect() {
        backlog.changeState(new BacklogSelectedState(backlog));
        return "Selected";
    }

    @Override
    public String onComplete() {
        return "Unselected";
    }

    @Override
    public String onDelete() {
        backlog.changeState(new BacklogDeletedState(backlog));
        return "Deleted";
    }
}
