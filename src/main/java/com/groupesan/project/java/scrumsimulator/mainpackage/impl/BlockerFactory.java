package com.groupesan.project.java.scrumsimulator.mainpackage.impl;
import java.util.UUID;

public class BlockerFactory {

    private static BlockerFactory blockerFactory;

    public static BlockerFactory getInstance() {
        if (blockerFactory == null) {
            blockerFactory = new BlockerFactory();
        }
        return blockerFactory;
    }

    private BlockerFactory() {}

    public Blockers createNewBlocker(String description, double severity, double impact) {
        String id = UUID.randomUUID().toString();
        Blockers newBlockers = new Blockers(id, description, severity, impact);
        return newBlockers;
    }
}
