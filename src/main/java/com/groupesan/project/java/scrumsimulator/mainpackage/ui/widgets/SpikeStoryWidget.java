package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.EditSpikeStoryForm;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.SpikeStoryListPane;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SpikeStoryWidget extends JPanel implements BaseComponent {

    JLabel id;
    JLabel points;
    JLabel businessValue;
    JLabel name;
    JLabel desc;

    // TODO: This is a non transient field and this class is supposed to be serializable. this needs
    // to be dealt with before this object can be serialized
    private SpikeStory spikeStory;
    private SpikeStoryListPane parentPanel;
    ActionListener actionListener = e -> {};

    public SpikeStoryWidget(SpikeStory spikeStory, SpikeStoryListPane parentPanel) {
        this.spikeStory = spikeStory;
        this.parentPanel = parentPanel;
        this.init();
    }

    public SpikeStoryWidget(SpikeStory spikeStory) {
        this.spikeStory = spikeStory;
        this.init();
    }

    public void init() {
        removeAll();

        MouseAdapter openEditDialog = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EditSpikeStoryForm form = new EditSpikeStoryForm(spikeStory, parentPanel);
                form.setVisible(true);

                form.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        init();
                    }
                });
            }
        };

        id = new JLabel(spikeStory.getId().toString());
        id.addMouseListener(openEditDialog);
        points = new JLabel(Double.toString(spikeStory.getPointValue()));
        points.addMouseListener(openEditDialog);
        businessValue = new JLabel(Double.toString(spikeStory.getBusinessValue()));
        businessValue.addMouseListener(openEditDialog);
        name = new JLabel(spikeStory.getName());
        name.addMouseListener(openEditDialog);
        desc = new JLabel(spikeStory.getDescription());
        desc.addMouseListener(openEditDialog);

        GridBagLayout myGridBagLayout = new GridBagLayout();
        setLayout(myGridBagLayout);

        add(
                id,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                points,
                new CustomConstraints(
                        1, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                businessValue,
                new CustomConstraints(
                        2, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                name,
                new CustomConstraints(
                        3, 0, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                desc,
                new CustomConstraints(
                        4, 0, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL));

        revalidate();
        repaint();
    }

}
