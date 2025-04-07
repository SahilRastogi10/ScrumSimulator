package com.groupesan.project.java.scrumsimulator.mainpackage.core;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.ScrumIdentifierStoreSingleton;

public abstract class ScrumIdentifier {
    protected int id;
    private ScrumObject thisObject;

    /**
     * Constructor that initializes the ID and registers the identifier with the ScrumIdentifierStore.
     *
     * @param value the ID value
     */
    public ScrumIdentifier(int value) {
        id = value;
        // Registering the identifier to ensure all ScrumIdentifiers are managed.
        ScrumIdentifierStoreSingleton.get().registerIdentifier(this);
    }

    /**
     * Gets the ID value for this ScrumIdentifier.
     *
     * @return the ID as an integer
     */
    public int getValue() {
        return id;
    }

    /**
     * Associates a ScrumObject with this ScrumIdentifier.
     *
     * @param object the ScrumObject to associate
     */
    public void setThisObject(ScrumObject object) {
        thisObject = object;
    }

    /**
     * Gets the associated ScrumObject for this ScrumIdentifier.
     *
     * @return the associated ScrumObject
     */
    public ScrumObject getThisObject() {
        return thisObject;
    }

    /**
     * Abstract method for converting the ScrumIdentifier to a string representation.
     * This will be implemented by subclasses to define specific string formats.
     *
     * @return the string representation of the ScrumIdentifier
     */
    public abstract String toString();

    /**
     * Default string representation of the ScrumIdentifier.
     * This provides a fallback if a subclass doesn't override toString().
     * It returns the ID value as a string.
     *
     * @return the string representation of the ScrumIdentifier, defaulting to the ID
     */
    public String toStringDefault() {
        return "ScrumIdentifier{ID=" + id + "}";
    }
}
