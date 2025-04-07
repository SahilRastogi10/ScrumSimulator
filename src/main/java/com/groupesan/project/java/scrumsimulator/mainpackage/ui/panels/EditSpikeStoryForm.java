package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditSpikeStoryForm extends JFrame implements BaseComponent {

    Double[] pointsList = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0,20.0};
    Double[] businessValueList = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0,11.0};
    SpikeStoryListPane parentPanel;

    public EditSpikeStoryForm(SpikeStory spikeStory, SpikeStoryListPane parentPanel) {
        this.spikeStory = spikeStory;
        this.parentPanel = parentPanel;
        this.init();
    }

    private SpikeStory spikeStory;

    private JTextField nameField = new JTextField();
    private JTextArea descArea = new JTextArea();
    private JComboBox<Double> pointsCombo = new JComboBox<>(pointsList);
    private JComboBox<Double> businessValueCombo = new JComboBox<>(businessValueList);

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Edit Spike Story " + spikeStory.getId().toString());
        setSize(400, 300);

        nameField = new JTextField(spikeStory.getName());
        descArea = new JTextArea(spikeStory.getDescription());
        pointsCombo = new JComboBox<>(pointsList);
        pointsCombo.setSelectedItem(spikeStory.getPointValue());
        businessValueCombo = new JComboBox<>(pointsList);
        businessValueCombo.setSelectedItem(spikeStory.getBusinessValue());

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        BorderLayout myBorderLayout = new BorderLayout();

        setLayout(myBorderLayout);

        JLabel nameLabel = new JLabel("Name:");
        myJpanel.add(
                nameLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                nameField,
                new CustomConstraints(
                        1, 0, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel descLabel = new JLabel("Description:");
        myJpanel.add(
                descLabel,
                new CustomConstraints(
                        0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                new JScrollPane(descArea),
                new CustomConstraints(
                        1, 1, GridBagConstraints.EAST, 1.0, 0.3, GridBagConstraints.BOTH));

        JLabel pointsLabel = new JLabel("Points:");
        myJpanel.add(
                pointsLabel,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                pointsCombo,
                new CustomConstraints(
                        1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                        String description = descArea.getText();
                        Double points = (Double) pointsCombo.getSelectedItem();
                        Double businessValue = (Double) businessValueCombo.getSelectedItem();

                        spikeStory.setName(name);
                        spikeStory.setDescription(description);
                        spikeStory.setPointValue(points);
                        spikeStory.setBusinessValue(businessValue);
                        dispose();
                    }
                });

        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int confirmation = JOptionPane.showConfirmDialog(
                        EditSpikeStoryForm.this,
                        "Are you sure you want to delete this Spike Story?",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    SpikeStoryStore.getInstance().deleteSpikeStory(spikeStory);
                    dispose();
                    parentPanel.shutWindow();
                    JOptionPane.showMessageDialog(EditSpikeStoryForm.this, "Spike Story deleted successfully!");


                }
            }
        });

        myJpanel.add(
                cancelButton,
                new CustomConstraints(0, 3, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(1, 3, GridBagConstraints.WEST, GridBagConstraints.NONE));
        //JButton deleteButton = new JButton("Delete");
        myJpanel.add(
                deleteButton,
                new CustomConstraints(2, 3, GridBagConstraints.WEST, GridBagConstraints.NONE));
        add(myJpanel);
    }
}
