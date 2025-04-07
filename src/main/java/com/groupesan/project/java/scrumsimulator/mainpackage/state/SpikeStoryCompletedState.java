package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;

public class SpikeStoryCompletedState extends SpikeStoryState {

    public SpikeStoryCompletedState(SpikeStory spikeStory) {
        super(spikeStory);
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
        spikeStory.changeState(new SpikeStoryDeletedState(spikeStory));
        return "Deleted";
    }
}
