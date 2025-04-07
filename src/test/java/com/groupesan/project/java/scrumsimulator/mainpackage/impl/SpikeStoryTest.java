package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpikeStoryTest {
    private SpikeStory mySpikeStory;
    private UserStory linkedUserStory;

    @BeforeEach
    public void setup() {
        // Creating a sample UserStory for association with SpikeStory
        linkedUserStory = new UserStory("Test UserStory", "This is a test user story", 5.0, 8.0);

        // Creating a new SpikeStory and associating it with the UserStory
        mySpikeStory = SpikeStoryFactory.getInstance().createNewSpikeStory(null,null,1,1,null);
    }

    @Disabled
    @Test
    public void testSpikeStoryUnregistered1() {
        Exception exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> {
                            ScrumIdentifier id = mySpikeStory.getId();
                        });

        String actualMessage = exception.getMessage();

        assertEquals(
                "This SpikeStory has not been registered and does not have an ID yet!",
                actualMessage);
    }

    /** Test case to ensure that toString handles the unregistered state */
    @Disabled
    @Test
    public void testSpikeStoryUnregistered2() {
        String string = mySpikeStory.toString();

        assertEquals("(unregistered) - predefinedSP4", string);
    }

    @Test
    public void testSpikeStoryRegistered() {
        mySpikeStory.doRegister();

        ScrumIdentifier id = mySpikeStory.getId();
        double businessValue = mySpikeStory.getBusinessValue();
        assertEquals(2.0, businessValue);
        assertNotNull(id);

        // Verifying that the SpikeStory is correctly linked with the UserStory
        assertEquals(linkedUserStory, mySpikeStory.getLinkedUserStory());
    }

    @Test
    public void testSpikeStoryWithUserStory() {
        // Verifying the association with the UserStory
        assertNotNull(mySpikeStory.getLinkedUserStory());
        assertEquals(linkedUserStory, mySpikeStory.getLinkedUserStory());
    }

    @Test
    public void testSpikeStoryWithoutUserStory() {
        // Creating a SpikeStory without UserStory association
        SpikeStory spikeStoryWithoutUserStory = new SpikeStory("Test SpikeStory Without UserStory", 3.0, 5.0);

        // Verifying that it has no associated UserStory
        assertNull(spikeStoryWithoutUserStory.getLinkedUserStory());
    }
}
