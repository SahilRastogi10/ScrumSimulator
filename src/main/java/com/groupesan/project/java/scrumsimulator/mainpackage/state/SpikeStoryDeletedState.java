package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;

public class SpikeStoryDeletedState extends SpikeStoryState {

    public SpikeStoryDeletedState(SpikeStory spikeStory) {
        super(spikeStory);
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
