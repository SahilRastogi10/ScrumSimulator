package com.groupesan.project.java.scrumsimulator.mainpackage.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;

public class SimulationController {

    private static final String FILE_NAME = "simulation.JSON";
    private static final File DATA_FILE = new File(System.getProperty("user.home"), FILE_NAME);
    public static JSONArray getAllSimulations() {
        try {
            if (!DATA_FILE.exists()) {
                initializeEmptySimulationsFile();
            }

            try (InputStream is = new FileInputStream(DATA_FILE)) {
                JSONTokener tokener = new JSONTokener(is);
                JSONObject jsonObject = new JSONObject(tokener);
                return jsonObject.optJSONArray("Simulations") != null ?
                        jsonObject.getJSONArray("Simulations") : new JSONArray();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }
    public static void updateSimulation(JSONObject updatedSimulation) {
        JSONArray simulations = getAllSimulations();
        boolean found = false;

        for (int i = 0; i < simulations.length(); i++) {
            JSONObject sim = simulations.getJSONObject(i);
            if (sim.getString("ID").equals(updatedSimulation.getString("ID"))) {
                simulations.put(i, updatedSimulation);
                found = true;
                break;
            }
        }
        if (!found) {
            simulations.put(updatedSimulation); // Add if not found
        }
        saveSimulations(simulations);
    }
    public static void saveSimulations(JSONArray simulations) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Simulations", simulations);

            if (!DATA_FILE.exists()) {
                Files.createFile(DATA_FILE.toPath());
            }
            try (OutputStream os = new FileOutputStream(DATA_FILE)) {
                os.write(jsonObject.toString(4).getBytes()); // Pretty-print with indentation
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void initializeEmptySimulationsFile() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Simulations", new JSONArray());

            Files.createFile(DATA_FILE.toPath());
            try (OutputStream os = new FileOutputStream(DATA_FILE)) {
                os.write(jsonObject.toString(4).getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
