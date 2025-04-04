package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

public class BacklogFactory {
    private static BacklogFactory backlogFactory;
    public static BacklogFactory getInstance() {
        if (backlogFactory == null) {
            backlogFactory = new BacklogFactory();
        }
        return backlogFactory;
    }
    private BacklogFactory() {}
    public Backlog createNewBacklog(String name, String description, double pointValue, double businessValue) {
        Backlog newB = new Backlog(name, description, pointValue,businessValue);
        return newB;
    }
}
