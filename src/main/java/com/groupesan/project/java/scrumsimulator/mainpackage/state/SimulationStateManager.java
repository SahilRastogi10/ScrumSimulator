package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.User;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.probability.ProbabilityPanel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

public class SimulationStateManager {
    private boolean running;
    private static final String JSON_FILE_PATH = "src/main/resources/simulation.JSON";

    public SimulationStateManager() {
        this.running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void startSimulation() {
        setRunning(true);
    }

    public void stopSimulation() {
        setRunning(false);
    }
    public static void updateSimulation(String simId, String name, String sprints, String duration) {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) return;

        JSONArray sims = simulationData.getJSONArray("Simulations");

        for (int i = 0; i < sims.length(); i++) {
            JSONObject sim = sims.getJSONObject(i);
            if (sim.getString("ID").equals(simId)) {
                sim.put("Name", name);
                sim.put("NumberOfSprints", sprints);
                sim.put("SprintDuration", duration);
                break;
            }
        }

        updateSimulationData(simulationData);
    }


    public static void saveNewSimulationDetails(
            String simId, String simName, String numberOfSprints, String sprintDuration) {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) {
            simulationData = new JSONObject();
        }

        JSONObject newSimulation = new JSONObject();
        newSimulation.put("ID", simId);
        newSimulation.put("Name", simName);
        newSimulation.put("Status", "New");
        newSimulation.put("NumberOfSprints", numberOfSprints);
        newSimulation.put("SprintDuration", sprintDuration);
        newSimulation.put("Sprints", new JSONArray());
        newSimulation.put("Events", new JSONArray());
        newSimulation.put("Users", new JSONArray());
        newSimulation.put("Blockers", new JSONArray());
        newSimulation.put("BlockerProbabilities", new JSONArray());
        newSimulation.put("SolutionProbabilities", new JSONArray());

        JSONArray simulations = simulationData.optJSONArray("Simulations");
        if (simulations == null) {
            simulations = new JSONArray();
            simulationData.put("Simulations", simulations);
        }
        simulations.put(newSimulation);

        updateSimulationData(simulationData);
    }

    public static boolean addUserToCurrentSimulation(User user) {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) return false;

        JSONArray simulations = simulationData.optJSONArray("Simulations");
        if (simulations == null || simulations.length() == 0) return false;

        JSONObject currentSim = simulations.getJSONObject(simulations.length() - 1);
        JSONArray users = currentSim.optJSONArray("Users");
        if (users == null) {
            users = new JSONArray();
            currentSim.put("Users", users);
        }

        for (int i = 0; i < users.length(); i++) {
            JSONObject existingUser = users.getJSONObject(i);
            if (existingUser.getString("name").equalsIgnoreCase(user.getName())) {
                return false;
            }
        }

        JSONObject userJson = new JSONObject();
        userJson.put("name", user.getName());
        userJson.put("role", user.getRole().toString());
        userJson.put("id", user.getId().toString());
        userJson.put("type", user instanceof Player ? "Player" : "Teacher");
        users.put(userJson);

        updateSimulationData(simulationData);
        return true;
    }

    public static JSONArray getCurrentSimulationUsers() {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) return new JSONArray();

        JSONArray simulations = simulationData.optJSONArray("Simulations");
        if (simulations == null || simulations.length() == 0) return new JSONArray();

        JSONObject currentSim = simulations.getJSONObject(simulations.length() - 1);
        return currentSim.optJSONArray("Users");
    }

    public static JSONArray getBlockersList() {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) return new JSONArray();

        JSONArray simulations = simulationData.optJSONArray("Simulations");
        if (simulations == null || simulations.length() == 0) return new JSONArray();

        JSONObject currentSim = simulations.getJSONObject(simulations.length() - 1);
        JSONArray blockers = currentSim.optJSONArray("Blockers");
        return blockers != null ? blockers : new JSONArray();
    }

    public static void refreshBlockersInProbabilityConfig() {
        // This method now triggers UI updates through the BlockerProbabilityPanel's refresh
        // The actual refresh is handled when getBlockersList() is called
    }

    public static void addBlocker(String description, double severity, double impact) {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) {
            JOptionPane.showMessageDialog(null, "Error: No simulation data found");
            return;
        }

        JSONArray simulations = simulationData.optJSONArray("Simulations");
        if (simulations == null || simulations.length() == 0) {
            JOptionPane.showMessageDialog(null, "Error: No active simulation");
            return;
        }

        JSONObject currentSim = simulations.getJSONObject(simulations.length() - 1);
        JSONArray blockers = currentSim.optJSONArray("Blockers");
        if (blockers == null) {
            blockers = new JSONArray();
            currentSim.put("Blockers", blockers);
        }

        // Check for duplicate blockers
        for (int i = 0; i < blockers.length(); i++) {
            JSONObject existing = blockers.getJSONObject(i);
            if (existing.getString("description").equalsIgnoreCase(description) &&
                    existing.getDouble("severity") == severity &&
                    existing.getDouble("impact") == impact) {
                return; // Skip adding duplicate
            }
        }

        JSONObject blocker = new JSONObject();
        blocker.put("id", UUID.randomUUID().toString());
        blocker.put("description", description); // Primary field
        blocker.put("name", description); // Add name as alias if needed
        blocker.put("severity", severity);
        blocker.put("impact", impact);
        blockers.put(blocker);

        updateSimulationData(simulationData);
        refreshBlockersInProbabilityConfig();
    }

    public static boolean removeBlocker(String blockerId) {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) return false;

        JSONArray simulations = simulationData.optJSONArray("Simulations");
        if (simulations == null || simulations.length() == 0) return false;

        JSONObject currentSim = simulations.getJSONObject(simulations.length() - 1);
        JSONArray blockers = currentSim.optJSONArray("Blockers");
        if (blockers == null) return false;

        for (int i = 0; i < blockers.length(); i++) {
            JSONObject blocker = blockers.getJSONObject(i);
            if (blocker.getString("id").equals(blockerId)) {
                blockers.remove(i);
                updateSimulationData(simulationData);
                return true;
            }
        }
        return false;
    }

    public static JSONArray getBlockerProbabilities() {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) return new JSONArray();

        JSONArray simulations = simulationData.optJSONArray("Simulations");
        if (simulations == null || simulations.length() == 0) return new JSONArray();

        JSONObject currentSim = simulations.getJSONObject(simulations.length() - 1);
        JSONArray probabilities = currentSim.optJSONArray("BlockerProbabilities");
        return probabilities != null ? probabilities : new JSONArray();
    }

    public static JSONArray getSolutionProbabilities() {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) return new JSONArray();

        JSONArray simulations = simulationData.optJSONArray("Simulations");
        if (simulations == null || simulations.length() == 0) return new JSONArray();

        JSONObject currentSim = simulations.getJSONObject(simulations.length() - 1);
        JSONArray probabilities = currentSim.optJSONArray("SolutionProbabilities");
        return probabilities != null ? probabilities : new JSONArray();
    }

    public static void saveBlockerProbabilities(List<ProbabilityPanel.ProbabilityRange> ranges) {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) return;

        JSONArray simulations = simulationData.optJSONArray("Simulations");
        if (simulations == null || simulations.length() == 0) return;

        JSONObject currentSim = simulations.getJSONObject(simulations.length() - 1);
        JSONArray probabilities = new JSONArray();

        for (ProbabilityPanel.ProbabilityRange range : ranges) {
            probabilities.put(range.toJson());
        }

        currentSim.put("BlockerProbabilities", probabilities);
        updateSimulationData(simulationData);
    }

    public static void saveSolutionProbabilities(List<ProbabilityPanel.ProbabilityRange> ranges) {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) return;

        JSONArray simulations = simulationData.optJSONArray("Simulations");
        if (simulations == null || simulations.length() == 0) return;

        JSONObject currentSim = simulations.getJSONObject(simulations.length() - 1);
        JSONArray probabilities = new JSONArray();

        for (ProbabilityPanel.ProbabilityRange range : ranges) {
            probabilities.put(range.toJson());
        }

        currentSim.put("SolutionProbabilities", probabilities);
        updateSimulationData(simulationData);
    }

    private static JSONObject getSimulationData() {
        try (FileInputStream fis = new FileInputStream(JSON_FILE_PATH)) {
            JSONTokener tokener = new JSONTokener(fis);
            return new JSONObject(tokener);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error reading simulation data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private static void updateSimulationData(JSONObject updatedData) {
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(JSON_FILE_PATH), StandardCharsets.UTF_8)) {
            writer.write(updatedData.toString(4));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error saving simulation data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static JSONObject getBlockerById(String blockerId) {
        JSONArray blockers = getBlockersList();
        for (int i = 0; i < blockers.length(); i++) {
            JSONObject blocker = blockers.getJSONObject(i);
            if (blocker.getString("id").equals(blockerId)) {
                return blocker;
            }
        }
        return null;
    }
}