package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlockerTest {
    private Blockers blocker;
    private BlockerStore blockerStore;
    private BlockerFactory blockerFactory;

    @BeforeEach
    public void setup() {
        blockerFactory = BlockerFactory.getInstance();
        blockerStore = BlockerStore.getInstance();

        // Create a new blocker using the factory
        blocker = blockerFactory.createNewBlocker("Test Blocker", 2.5, 3.0);
    }

    @Test
    public void testBlockerCreation() {
        assertNotNull(blocker);
        assertNotNull(blocker.getId());
        assertEquals("Test Blocker", blocker.getDescription());
        assertEquals(2.5, blocker.getSeverity());
        assertEquals(3.0, blocker.getImpact());
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
        Blockers anotherBlocker = blockerFactory.createNewBlocker("Another Blocker", 1.5, 4.0);

        assertNotEquals(blocker.getId(), anotherBlocker.getId());
    }
}
