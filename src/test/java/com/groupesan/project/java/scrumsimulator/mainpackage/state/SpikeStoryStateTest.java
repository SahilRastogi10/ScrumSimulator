package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpikeStoryStateTest {

    SpikeStory testSpikeStory = new SpikeStory("testSpike", 1.0,1.0);

    @Test
    public void testSpikeStoryUnselectedState() {
        SpikeStoryUnselectedState testState = new SpikeStoryUnselectedState(testSpikeStory);
        assertAll(
                "Unselected State",
                () -> assertEquals("Selected", testState.onSelect()),
                () -> assertEquals("Unselected", testState.onComplete()),
                () -> assertEquals("Deleted", testState.onDelete()));
    }

    @Test
    public void testSpikeStorySelectedState() {
        SpikeStorySelectedState testState = new SpikeStorySelectedState(testSpikeStory);
        assertAll(
                "Selected State",
                () -> assertEquals("Unselected", testState.onSelect()),
                () -> assertEquals("Completed", testState.onComplete()),
                () -> assertEquals("Deleted", testState.onDelete()));
    }

    @Test
    public void testSpikeStoryCompletedState() {
        SpikeStoryCompletedState testState = new SpikeStoryCompletedState(testSpikeStory);
        assertAll(
                "Completed State",
                () -> assertEquals("Completed", testState.onSelect()),
                () -> assertEquals("Completed", testState.onComplete()),
                () -> assertEquals("Deleted", testState.onDelete()));
    }

    @Test
    public void testSpikeDeletedState() {
        SpikeStoryDeletedState testState = new SpikeStoryDeletedState(testSpikeStory);
        assertAll(
                "Deleted State",
                () -> assertEquals("Deleted", testState.onSelect()),
                () -> assertEquals("Deleted", testState.onComplete()),
                () -> assertEquals("Deleted", testState.onDelete()));
    }
}
