package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Simulation;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationManager;
import java.util.UUID;

public class SimulationFactory {
    private static SimulationFactory simulationFactory;
    private SimulationManager simulationManager;
    //todo

    public static SimulationFactory getSimulationFactory() {
        if (simulationFactory == null) {
            simulationFactory = new SimulationFactory();
        }

        return simulationFactory;
    }

    private SimulationFactory() {
        this.simulationManager = new SimulationManager();
    }

    public Simulation createNewSimulation(Simulation newSimulation) {
        String simId = UUID.randomUUID().toString();
        String simName = newSimulation.getSimulationName();
        int sprintCount = newSimulation.getSprintCount();
        int sprintDuration = newSimulation.getSprintDuration();
        simulationManager.createSimulation(simId, simName, Integer.toString(sprintCount), Integer.toString(sprintDuration));
        // todo
        return newSimulation;
    }
}
