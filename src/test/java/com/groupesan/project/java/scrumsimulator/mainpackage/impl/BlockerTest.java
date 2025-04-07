package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlockerTest {
    private Blockers blocker;
    private BlockerStore blockerStore;
    private BlockerFactory blockerFactory;
    private UserStory userStory;
    private SpikeStory spikeStory;

    @BeforeEach
    public void setup() {
        blockerFactory = BlockerFactory.getInstance();
        blockerStore = BlockerStore.getInstance();

        // Create a sample UserStory and SpikeStory
        userStory = new UserStory("Test UserStory", "This is a test user story", 5.0, 8.0);
        spikeStory = new SpikeStory("Test SpikeStory", "This is a test spike story", 3.0, 6.0);

        // Create a new blocker using the factory and associate with UserStory and SpikeStory
        blocker = blockerFactory.createNewBlocker("Test Blocker", 2.5, 3.0, userStory, spikeStory);
    }

    @Test
    public void testBlockerCreation() {
        assertNotNull(blocker);
        assertNotNull(blocker.getId());
        assertEquals("Test Blocker", blocker.getDescription());
        assertEquals(2.5, blocker.getSeverity());
        assertEquals(3.0, blocker.getImpact());
        assertEquals(userStory, blocker.getAssociatedUserStory());
        assertEquals(spikeStory, blocker.getAssociatedSpikeStory());
    }

    @Test
    public void testAddingBlockerToStore() {
        int initialSize = blockerStore.getBlockers().size();

        blockerStore.addBlocker(blocker);

        assertEquals(initialSize + 1, blockerStore.getBlockers().size());
        assertTrue(blockerStore.getBlockers().contains(blocker));
    }

    @Test
    public void testBlockerDescriptions() {
        blockerStore.addBlocker(blocker);

        assertTrue(blockerStore.getBlockersDesc().contains("Test Blocker"));
    }

    @Test
    public void testBlockerIdUniqueness() {
        Blockers anotherBlocker = blockerFactory.createNewBlocker("Another Blocker", 1.5, 4.0, userStory, spikeStory);

        assertNotEquals(blocker.getId(), anotherBlocker.getId());
    }

    @Test
    public void testAssociatingSpikeStoryWithBlocker() {
        Blockers newBlocker = blockerFactory.createNewBlocker("Blocker with SpikeStory", 2.0, 3.0, userStory, spikeStory);
        assertNotNull(newBlocker.getAssociatedSpikeStory());
        assertEquals(spikeStory, newBlocker.getAssociatedSpikeStory());
    }

    @Test
    public void testAssociatingUserStoryWithBlocker() {
        Blockers newBlocker = blockerFactory.createNewBlocker("Blocker with UserStory", 2.0, 3.0, userStory, null);
        assertNotNull(newBlocker.getAssociatedUserStory());
        assertEquals(userStory, newBlocker.getAssociatedUserStory());
    }
}
