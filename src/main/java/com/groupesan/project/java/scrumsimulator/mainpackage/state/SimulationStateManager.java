package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.User;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

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

    private static JSONObject getSimulationData() {
        try (FileInputStream fis = new FileInputStream(JSON_FILE_PATH)) {
            JSONTokener tokener = new JSONTokener(fis);
            return new JSONObject(tokener);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading from simulation.JSON");
            return null;
        }
    }

    private static void updateSimulationData(JSONObject updatedData) {
        try (OutputStreamWriter writer =
                     new OutputStreamWriter(
                             new FileOutputStream(JSON_FILE_PATH), StandardCharsets.UTF_8)) {
            writer.write(updatedData.toString(4));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to simulation.JSON");
        }
    }
}