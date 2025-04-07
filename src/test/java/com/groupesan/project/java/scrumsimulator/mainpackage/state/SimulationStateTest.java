package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Simulation;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Teacher;
import com.groupesan.project.java.scrumsimulator.mainpackage.resources.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationStateTest {

    private Simulation simulation;
    private Teacher teacher;
    private Player player1;
    private Player player2;
    private final Constants constants = new Constants();
    private SimulationStateManager simulationStateManager;

    @BeforeEach
    public void setUpTests(){
        teacher = null;
        player1 = new Player("Alice", new ScrumRole("ScrumMaster"));
        player2 = new Player("Bob", new ScrumRole("Developer"));
        simulation = new Simulation(
                constants.SIMULATION_NAME,
                null,
                constants.SPRINT_COUNT,
                constants.SPRINT_DURATION);
        simulationStateManager = new SimulationStateManager();
    }

    @AfterEach
    public void tearDown() {
        simulationStateManager = null;
    }

    @Test
    public void testInitialState() {
        assertFalse(simulationStateManager.isRunning());
    }

    @Test
    public void testStartSimulation() {
        simulationStateManager.startSimulation();
        assertTrue(simulationStateManager.isRunning());
    }

    @Test
    public void testStopSimulation() {
        simulationStateManager.stopSimulation();
        assertFalse(simulationStateManager.isRunning());
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(constants.SIMULATION_NAME, simulation.getSimulationName());
        assertEquals(null, simulation.getTeacher());
        assertEquals(3, simulation.getSprintCount());
        assertEquals(5, simulation.getSprintDuration());
    }

    @Test
    public void testAddPlayer() {
        simulation.addPlayer(player1);
        List<Player> players = simulation.getPlayers();
        assertEquals(1, players.size());
        assertTrue(players.contains(player1));
    }

    @Test
    public void testRemovePlayer() {
        simulation.addPlayer(player1);
        simulation.addPlayer(player2);
        simulation.removePlayer(player1);
        List<Player> players = simulation.getPlayers();
        assertEquals(1, players.size());
        assertFalse(players.contains(player1));
        assertTrue(players.contains(player2));
    }

    @Test
    public void testToString() {
        simulation.addPlayer(player1);
        String expected = "[Simulation] Temporary Simulation\n" +
                "Sprints: 3\n" +
                "Sprints: 5\n" +
                "[Player] Alice ScrumMaster (unregistered) (unregistered)\n";
        assertEquals(expected, simulation.toString());
    }
}
