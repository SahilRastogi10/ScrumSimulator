package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Backlog;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BacklogFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;


public class NewBacklogForm extends JFrame implements BaseComponent {
    Double[] pointsList = {1.0, 2.0, 3.0, 5.0, 8.0, 11.0, 19.0, 30.0, 49.0};
    public NewBacklogForm() {
        this.init();
    }

    private JTextField nameField = new JTextField();
    private JTextArea descArea = new JTextArea();
    private JComboBox<Double> pointsCombo = new JComboBox<>(pointsList);
    private JComboBox<Double> businessValueCombo = new JComboBox<>(pointsList);

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("New user story");
        setSize(400, 300);

        nameField = new JTextField();
        descArea = new JTextArea();
        pointsCombo = new JComboBox<>(pointsList);

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

        JLabel businessValueLabel = new JLabel("Business Value:");
        myJpanel.add(
                businessValueLabel,
                new CustomConstraints(
                        0, 3, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                businessValueCombo,
                new CustomConstraints(
                        1, 3, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

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
                new CustomConstraints(0, 4, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(1, 4, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }

    public Backlog getBacklogObject() {
        String name = nameField.getText();
        String description = descArea.getText();
        Double points = (Double) pointsCombo.getSelectedItem();
        Double businessValue = (Double) businessValueCombo.getSelectedItem();

        BacklogFactory backlogFactory = BacklogFactory.getInstance();

        Backlog backlog = backlogFactory.createNewBacklog(name, description, points,businessValue);

        backlog.doRegister();

        System.out.println(backlog);

        return backlog;
    }
}
