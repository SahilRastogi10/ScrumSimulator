package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;

public class SpikeStoryUnselectedState extends SpikeStoryState {

    public SpikeStoryUnselectedState(SpikeStory spikeStory) {
        super(spikeStory);
    }

    @Override
    public String onSelect() {
        spikeStory.changeState(new SpikeStorySelectedState(spikeStory));
        return "Selected";
    }

    @Override
    public String onComplete() {
        return "Unselected";
    }

    @Override
    public String onDelete() {
        spikeStory.changeState(new SpikeStoryDeletedState(spikeStory));
        return "Deleted";
    }
}
