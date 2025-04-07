package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;

public class SpikeStorySelectedState extends SpikeStoryState {

    public SpikeStorySelectedState(SpikeStory spikeStory) {
        super(spikeStory);
    }

    @Override
    public String onSelect() {
        spikeStory.changeState(new SpikeStoryUnselectedState(spikeStory));
        return "Unselected";
    }

    @Override
    public String onComplete() {
        spikeStory.changeState(new SpikeStoryCompletedState(spikeStory));
        return "Completed";
    }

    @Override
    public String onDelete() {
        spikeStory.changeState(new SpikeStoryDeletedState(spikeStory));
        return "Deleted";
    }
}
