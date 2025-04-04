package com.groupesan.project.java.scrumsimulator.mainpackage.impl;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.BacklogState;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.BacklogUnselectedState;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumObject;
import java.util.ArrayList;
import java.util.List;

public class Backlog extends ScrumObject {
    private UserStoryIdentifier id;
    private String name;
    private String description;
    private double pointValue;
    private double businessValue;
    private BacklogState state;
    private Player owner;

    public Backlog(String name, double pointValue,  double businessValue) {
        this.name = name;
        this.description = "";
        this.pointValue = pointValue;
        this.businessValue = businessValue;
        this.state = new BacklogUnselectedState(this);
    }


    public Backlog(String name, String description, double pointValue,  double businessValue) {
        this.name = name;
        this.description = description;
        this.pointValue = pointValue;
        this.businessValue = businessValue;
        this.state = new BacklogUnselectedState(this);
    }

    protected void register() {
        this.id = new UserStoryIdentifier(ScrumIdentifierStoreSingleton.get().getNextId());
    }


    public ScrumIdentifier getId() {
        if (!isRegistered()) {
            throw new IllegalStateException(
                    "This UserStory hasn't been registered & doesn't have an ID!");
        }
        return id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public double getPointValue() {
        return pointValue;
    }

    public void setPointValue(double pointValue) {
        this.pointValue = pointValue;
    }

    public double getBusinessValue() {
        return businessValue;
    }

    public void setBusinessValue(double businessValue) {
        this.businessValue = businessValue;
    }


    public List<ScrumObject> getChildren() {
        return new ArrayList<>(); // TODO: implement tasks
    }


    @Override
    public String toString() {
        if (isRegistered()) {
            return this.getId().toString() + " - " + name;
        }
        return "(unregistered) - " + getName();
    }


    public void changeState(BacklogState state) {
        this.state = state;
    }


    public BacklogState getBacklogState() {
        return state;
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public Player getOwner() {
        return this.owner;
    }
}
