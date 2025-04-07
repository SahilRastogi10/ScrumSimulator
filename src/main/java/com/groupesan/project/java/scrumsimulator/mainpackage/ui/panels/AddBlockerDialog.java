package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blockers;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBlockerDialog extends JDialog {
    private JTextField severityField;
    private JTextField impactField;
    private JTextField descriptionField;
    private JComboBox<UserStory> userStoryComboBox;
    private JComboBox<SpikeStory> spikeStoryComboBox;  // New JComboBox for SpikeStory
    private boolean confirmed = false;

    public AddBlockerDialog(JFrame parent) {
        super(parent, "Add New Blocker", true);
        setLayout(new GridLayout(6, 2, 10, 10)); // Increased rows for SpikeStory combo box

        // Initialize input fields
        severityField = new JTextField();
        impactField = new JTextField();
        descriptionField = new JTextField();

        // Populate user stories into the combo box
        userStoryComboBox = new JComboBox<>(UserStoryStore.getInstance().getUserStories().toArray(new UserStory[0]));

        // Initialize SpikeStory combo box but leave it empty initially
        spikeStoryComboBox = new JComboBox<>();

        // Add input components
        add(new JLabel("Severity:"));
        add(severityField);
        add(new JLabel("Impact:"));
        add(impactField);
        add(new JLabel("Description:"));
        add(descriptionField);
        add(new JLabel("Associated User Story:"));
        add(userStoryComboBox);
        add(new JLabel("Associated Spike Story:"));
        add(spikeStoryComboBox);

        // Add listener to update SpikeStory combo box when UserStory changes
        userStoryComboBox.addActionListener(e -> updateSpikeStoryComboBox());

        // Buttons
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    String description = descriptionField.getText().trim();
//                    double severity = Double.parseDouble(severityField.getText());
//                    double impact = Double.parseDouble(impactField.getText());
//
//
//                    if (description.isEmpty()) {
//                        throw new IllegalArgumentException("Description cannot be empty");
//                    }
//                    if (severity < 1 || severity > 10 || impact < 1 || impact > 10) {
//                        throw new IllegalArgumentException("Values must be between 1-10");
//                    }
//
//                    // Check for duplicates in BlockerStore
//                    if (isDuplicateBlocker(description, severity, impact)) {
//                        JOptionPane.showMessageDialog(AddBlockerDialog.this,
//                                "This blocker already exists",
//                                "Error", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//
//                    // Create and store blocker
//                    Blockers newBlocker = BlockerFactory.getInstance().createNewBlocker(
//                            description, severity, impact);
//                    BlockerStore.getInstance().addBlocker(newBlocker);
//                    SimulationStateManager.addBlocker(description, severity, impact);
//
//                    confirmed = true;
//                    dispose();
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(AddBlockerDialog.this,
//                            "Please enter valid numbers for severity and impact",
//                            "Error", JOptionPane.ERROR_MESSAGE);
//                } catch (IllegalArgumentException ex) {
//                    JOptionPane.showMessageDialog(AddBlockerDialog.this,
//                            ex.getMessage(),
//                            "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });

        addButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());

        add(addButton);
        add(cancelButton);

        pack();
        setLocationRelativeTo(parent);
    }

//    private boolean isDuplicateBlocker(String description, double severity, double impact) {
//        // Check in-memory store first
//        for (Blockers blocker : BlockerStore.getInstance().getBlockers()) {
//            if (blocker.getDescription().equalsIgnoreCase(description) &&
//                    blocker.getSeverity() == severity &&
//                    blocker.getImpact() == impact) {
//                return true;
//            }
//        }
//
//        // Check persistent storage
//        JSONArray blockers = SimulationStateManager.getBlockersList();
//        if (blockers != null) {
//            for (int i = 0; i < blockers.length(); i++) {
//                JSONObject blocker = blockers.getJSONObject(i);
//                if (blocker.optString("description", "").equalsIgnoreCase(description) &&
//                        blocker.optDouble("severity", 0) == severity &&
//                        blocker.optDouble("impact", 0) == impact) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    private void updateSpikeStoryComboBox() {
        UserStory selectedUserStory = getSelectedUserStory();
        DefaultComboBoxModel<SpikeStory> spikeStoryModel = new DefaultComboBoxModel<>();
        if (selectedUserStory != null) {
            // Add SpikeStories that are linked to the selected UserStory
            for (SpikeStory spike : SpikeStoryStore.getInstance().getSpikeStories()) {
                if (spike.getLinkedUserStory() != null && spike.getLinkedUserStory().equals(selectedUserStory)) {
                    spikeStoryModel.addElement(spike);
                }
            }
        }
        spikeStoryComboBox.setModel(spikeStoryModel);
    }

    public String getSeverity() {
        return severityField.getText();
    }

    public String getImpact() {
        return impactField.getText();
    }

    public String getDescription() {
        return descriptionField.getText();
    }

    public UserStory getSelectedUserStory() {
        return (UserStory) userStoryComboBox.getSelectedItem();
    }

    public SpikeStory getSelectedSpikeStory() {
        return (SpikeStory) spikeStoryComboBox.getSelectedItem();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
