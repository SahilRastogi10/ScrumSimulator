package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.UserStoryWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BacklogPane extends JFrame implements BaseComponent {
    private List<UserStoryWidget> widgets = new ArrayList<>();

    public BacklogPane() {
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Product Backlog");
        setSize(500, 350);

        GridBagLayout layout = new GridBagLayout();
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(layout);

        // Load existing user stories
        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            widgets.add(new UserStoryWidget(userStory));
        }

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        int i = 0;
        for (UserStoryWidget widget : widgets) {
            subPanel.add(
                    widget,
                    new CustomConstraints(
                            0,
                            i++,
                            GridBagConstraints.WEST,
                            1.0,
                            0.1,
                            GridBagConstraints.HORIZONTAL));
        }

        panel.add(
                new JScrollPane(subPanel),
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

        JButton addStoryButton = new JButton("Add User Story");
        addStoryButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NewUserStoryForm form = new NewUserStoryForm();
                        form.setVisible(true);

                        form.addWindowListener(
                                new java.awt.event.WindowAdapter() {
                                    public void windowClosed(
                                            java.awt.event.WindowEvent windowEvent) {
                                        UserStory userStory = form.getUserStoryObject();
                                        UserStoryStore.getInstance().addUserStory(userStory);
                                        widgets.add(new UserStoryWidget(userStory));
                                        int idx = widgets.size() - 1;
                                        subPanel.add(
                                                widgets.get(idx),
                                                new CustomConstraints(
                                                        0,
                                                        idx,
                                                        GridBagConstraints.WEST,
                                                        1.0,
                                                        0.1,
                                                        GridBagConstraints.HORIZONTAL));
                                        subPanel.revalidate();
                                        subPanel.repaint();
                                    }
                                });
                    }
                });

        panel.add(
                addStoryButton,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(panel);
    }
}
