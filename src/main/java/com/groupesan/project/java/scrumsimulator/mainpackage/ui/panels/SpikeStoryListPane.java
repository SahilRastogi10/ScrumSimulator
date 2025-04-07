package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.SpikeStoryWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SpikeStoryListPane extends JFrame implements BaseComponent {

    private List<SpikeStoryWidget> widgets = new ArrayList<>();

    public SpikeStoryListPane() {
        init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        JPanel subPanel = new JPanel();

        for (SpikeStory spikeStory : SpikeStoryStore.getInstance().getSpikeStories()) {
            widgets.add(new SpikeStoryWidget(spikeStory, this));
        }

        subPanel.setLayout(new GridBagLayout());
        int i = 0;
        for (SpikeStoryWidget widget : widgets) {
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

        myJpanel.add(
                subPanel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

        JButton newSprintButton = new JButton("New Spike Story");
        newSprintButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NewSpikeStoryForm form = new NewSpikeStoryForm();
                        form.setVisible(true);

                        form.addWindowListener(
                                new java.awt.event.WindowAdapter() {
                                    public void windowClosed(
                                            java.awt.event.WindowEvent windowEvent) {
                                        SpikeStory spikeStory = form.getSpikeStoryObject();
                                        SpikeStoryStore.getInstance().addSpikeStory(spikeStory);
                                        SpikeStoryWidget newWidget = new SpikeStoryWidget(spikeStory, SpikeStoryListPane.this);
                                        widgets.add(newWidget);
                                        int idx = widgets.size() - 1;
                                        subPanel.add(
                                                newWidget,
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
        myJpanel.add(
                newSprintButton,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }

    public void shutWindow() {
        dispose();
    }
}