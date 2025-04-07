package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStoryFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStorySelectedState;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStoryUnselectedState;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.UserStoryWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NewSpikeStoryForm extends JFrame implements BaseComponent {

    Double[] businessValueList = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 11.0};
    Double[] pointsList = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0,20.0};
    List<UserStoryWidget> userStoryCombo = new ArrayList<>();

    public NewSpikeStoryForm() {
        this.init();
    }

    private JTextField nameField = new JTextField();
    private JTextArea descArea = new JTextArea();
    private JComboBox<Double> pointsCombo = new JComboBox<>(pointsList);
    private JComboBox<Double> businessValueCombo = new JComboBox<>(businessValueList);
    private JComboBox<UserStory> userStoryJComboBox = new JComboBox<>();
    private List<UserStoryWidget> widgets = new ArrayList<>();

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("New Spike Story");
        setSize(400, 400);

        nameField = new JTextField();
        descArea = new JTextArea();
        businessValueCombo = new JComboBox<>(businessValueList);
        pointsCombo = new JComboBox<>(pointsList);
        userStoryJComboBox = new JComboBox<>();

        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            if (userStory.getUserStoryState() instanceof UserStoryUnselectedState) {
                userStoryJComboBox.addItem(userStory);
            }
        }

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
                        0, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                new JScrollPane(descArea),
                new CustomConstraints(
                        1, 1, GridBagConstraints.EAST, 1.0, 0.3, GridBagConstraints.BOTH));

        JLabel businessValueLabel = new JLabel("Business Value:");
        myJpanel.add(
                businessValueLabel,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                businessValueCombo,
                new CustomConstraints(
                        1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel pointsLabel = new JLabel("Points:");
        myJpanel.add(
                pointsLabel,
                new CustomConstraints(
                        0, 3, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                pointsCombo,
                new CustomConstraints(
                        1, 3, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel USLabel = new JLabel("Linked User Story:");
        myJpanel.add(
                USLabel,
                new CustomConstraints(
                        0, 4, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                userStoryJComboBox,
                new CustomConstraints(
                        1, 4, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        JPanel availableSubPanel = new JPanel();
        availableSubPanel.setLayout(new GridBagLayout());
        int i = 0;
        for (UserStoryWidget widget : widgets) {
            availableSubPanel.add(
                    widget,
                    new CustomConstraints(
                            0,
                            i++,
                            GridBagConstraints.WEST,
                            1.0,
                            0.1,
                            GridBagConstraints.HORIZONTAL));
        }

        JPanel selectedSubPanel = new JPanel();
        selectedSubPanel.setLayout(new GridBagLayout());
        i = 0;

        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            if (userStory.getUserStoryState() instanceof UserStorySelectedState) {
                selectedSubPanel.add(
                        new UserStoryWidget(userStory),
                        new CustomConstraints(
                                0,
                                i++,
                                GridBagConstraints.WEST,
                                1.0,
                                0.1,
                                GridBagConstraints.HORIZONTAL));
            }
        }

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
                        dispose();
                    }
                });

        myJpanel.add(
                cancelButton,
                new CustomConstraints(0, 7, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(1, 7, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }

    public SpikeStory getSpikeStoryObject() {
        String name = nameField.getText();
        String description = descArea.getText();
        Double businessValue = (Double) businessValueCombo.getSelectedItem();
        Double points = (Double) pointsCombo.getSelectedItem();
        UserStory linkedUserStory = (UserStory) userStoryJComboBox.getSelectedItem();

        SpikeStoryFactory spikeStoryFactory = SpikeStoryFactory.getInstance();
        SpikeStory spikeStory = spikeStoryFactory.createNewSpikeStory(name, description, points, businessValue, linkedUserStory);
        spikeStory.doRegister();

        return spikeStory;
    }
}