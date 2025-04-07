package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.controller.SimulationController;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;

public class ModifySimulationPane {

    private static JTextField simulationNameField;
    private static JTextField numberOfSprintsField;
    private static JTextField sprintDurationField;
    private static JLabel simulationIdDisplay;
    private static JComboBox<String> simulationSelector;

    public static JPanel create(boolean isModificationMode) {
        JPanel panel = new JPanel(new GridBagLayout());
        int row = 0;

        if (isModificationMode) {
            JLabel selectLabel = new JLabel("Select Simulation:");
            simulationSelector = new JComboBox<>(getSimulationNames());

            simulationSelector.addActionListener(e -> {
                String selected = (String) simulationSelector.getSelectedItem();
                if (selected != null) {
                    String simId = selected.split(" - ")[1];
                    JSONObject simData = getSimulationById(simId);
                    populateFieldsWithSimulationData(simData);
                }
            });

            panel.add(selectLabel, CustomConstraints.with(0, row));
            panel.add(simulationSelector, CustomConstraints.with(1, row++));
        }

        JLabel nameLabel = new JLabel("Simulation Name:");
        simulationNameField = new JTextField(20);

        JLabel sprintsLabel = new JLabel("Number of Sprints:");
        numberOfSprintsField = new JTextField(5);

        JLabel sprintDurationLabel = new JLabel("Sprint Duration (days):");
        sprintDurationField = new JTextField(5);

        simulationIdDisplay = new JLabel("Simulation ID: -");

        panel.add(nameLabel, CustomConstraints.with(0, row));
        panel.add(simulationNameField, CustomConstraints.with(1, row++));

        panel.add(sprintsLabel, CustomConstraints.with(0, row));
        panel.add(numberOfSprintsField, CustomConstraints.with(1, row++));

        panel.add(sprintDurationLabel, CustomConstraints.with(0, row));
        panel.add(sprintDurationField, CustomConstraints.with(1, row++));

        JButton submitButton = new JButton(isModificationMode ? "Apply Changes" : "Create Simulation");
        panel.add(submitButton, CustomConstraints.with(0, ++row));
        panel.add(simulationIdDisplay, CustomConstraints.with(1, row));

        submitButton.addActionListener(e -> {
            try {
                String name = simulationNameField.getText().trim();
                int numberOfSprints = Integer.parseInt(numberOfSprintsField.getText().trim());
                int sprintDuration = Integer.parseInt(sprintDurationField.getText().trim());
                String simulationId = simulationIdDisplay.getText().replace("Simulation ID: ", "").trim();

                if (name.isEmpty() || simulationId.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please select and fill in all simulation fields.");
                    return;
                }

                JSONObject updatedSimulation = new JSONObject();
                updatedSimulation.put("Name", name);
                updatedSimulation.put("NumberOfSprints", numberOfSprints);
                updatedSimulation.put("SprintDuration", sprintDuration);
                updatedSimulation.put("ID", simulationId);
                SimulationController.updateSimulation(updatedSimulation);

                JOptionPane.showMessageDialog(panel, "Simulation has been updated successfully.");
                if (isModificationMode) {
                    simulationSelector.setModel(new DefaultComboBoxModel<>(getSimulationNames()));
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please enter valid numbers for sprints and duration.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error updating simulation: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        return panel;
    }

    private static String[] getSimulationNames() {
        JSONArray simulations = SimulationController.getAllSimulations();
        String[] names = new String[simulations.length()];
        for (int i = 0; i < simulations.length(); i++) {
            JSONObject sim = simulations.getJSONObject(i);
            names[i] = sim.getString("Name") + " - " + sim.getString("ID");
        }
        return names;

    }

    private static JSONObject getSimulationById(String id) {
        JSONArray simulations = SimulationController.getAllSimulations();
        for (int i = 0; i < simulations.length(); i++) {
            JSONObject sim = simulations.getJSONObject(i);
            if (sim.getString("ID").equals(id)) {
                return sim;
            }
        }
        return null;
    }

    private static void populateFieldsWithSimulationData(JSONObject simData) {
        if (simData != null) {
            simulationNameField.setText(simData.getString("Name"));
            numberOfSprintsField.setText(String.valueOf(simData.getInt("NumberOfSprints")));
            sprintDurationField.setText(String.valueOf(simData.getInt("SprintDuration")));
            simulationIdDisplay.setText("Simulation ID: " + simData.getString("ID"));
        }
    }
}
