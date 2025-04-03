package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Backlog;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BacklogStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BacklogWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.ReadyForSprintWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BacklogPane extends JFrame {

    private List<BacklogWidget> backlogWidgets = new ArrayList<>();
    private List<ReadyForSprintWidget> sprintWidgets = new ArrayList<>();

    public BacklogPane() {
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Backlog User Story list");
        setSize(400, 300);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        for (Backlog backlog : BacklogStore.getInstance().getBacklogs()) {
            BacklogWidget backlogWidget = new BacklogWidget(backlog);
            ReadyForSprintWidget sprintWidget = new ReadyForSprintWidget(backlog);
            backlogWidgets.add(backlogWidget);
            sprintWidgets.add(sprintWidget);
        }

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        int i = 0;

        // Adding both widgets and sprint buttons to the subPanel
        for (int j = 0; j < backlogWidgets.size(); j++) {
            BacklogWidget backlogWidget = backlogWidgets.get(j);
            ReadyForSprintWidget sprintWidget = sprintWidgets.get(j);

            subPanel.add(
                    backlogWidget,
                    new CustomConstraints(
                            0,
                            i,
                            GridBagConstraints.WEST,
                            0.7,
                            0.1,
                            GridBagConstraints.HORIZONTAL));

            subPanel.add(
                    sprintWidget,
                    new CustomConstraints(
                            1,
                            i++,
                            GridBagConstraints.EAST,
                            0.3,
                            0.1,
                            GridBagConstraints.HORIZONTAL));
        }

        myJpanel.add(
                new JScrollPane(subPanel),
                new CustomConstraints(
                        0, 0, GridBagConstraints.CENTER, 1.0, 0.8, GridBagConstraints.BOTH));


        JButton newBacklog = new JButton("New Backlog");
        newBacklog.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NewBacklogForm form = new NewBacklogForm();
                        form.setVisible(true);

                        form.addWindowListener(
                                new java.awt.event.WindowAdapter() {
                                    public void windowClosed(
                                            java.awt.event.WindowEvent windowEvent) {
                                        Backlog backlog = form.getBacklogObject();
                                        BacklogStore.getInstance().addBacklog(backlog);

                                        BacklogWidget backlogWidget = new BacklogWidget(backlog);
                                        ReadyForSprintWidget sprintWidget = new ReadyForSprintWidget(backlog);

                                        backlogWidgets.add(backlogWidget);
                                        sprintWidgets.add(sprintWidget);

                                        int idx = backlogWidgets.size() - 1;
                                        subPanel.add(
                                                backlogWidget,
                                                new CustomConstraints(
                                                        0,
                                                        idx,
                                                        GridBagConstraints.WEST,
                                                        0.7,
                                                        0.1,
                                                        GridBagConstraints.HORIZONTAL));

                                        subPanel.add(
                                                sprintWidget,
                                                new CustomConstraints(
                                                        1,
                                                        idx,
                                                        GridBagConstraints.EAST,
                                                        0.3,
                                                        0.1,
                                                        GridBagConstraints.HORIZONTAL));

                                        subPanel.revalidate();  // Refresh UI to display new components
                                        subPanel.repaint();
                                    }
                                });
                    }
                });

        myJpanel.add(
                newBacklog,
                new CustomConstraints(
                        0, 1, GridBagConstraints.CENTER, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }
}
