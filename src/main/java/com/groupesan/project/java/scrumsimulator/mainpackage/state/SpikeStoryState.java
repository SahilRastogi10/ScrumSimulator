package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;

public abstract class SpikeStoryState {
    protected SpikeStory spikeStory;

    SpikeStoryState(SpikeStory spikeStory) {
        this.spikeStory = spikeStory;
    }

    // Methods to change state, return a string of the state after change
    public abstract String onSelect();

    public abstract String onComplete();

    public abstract String onDelete();
}
