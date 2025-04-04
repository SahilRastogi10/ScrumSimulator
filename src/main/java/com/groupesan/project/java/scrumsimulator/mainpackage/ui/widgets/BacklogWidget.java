package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Backlog;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.EditBacklogForm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BacklogWidget extends JPanel implements BaseComponent {

    JLabel id;
    JLabel points;
    JLabel businessValue;
    JLabel name;
    JLabel desc;
    JButton readyForSprintButton;

//    TODO: This is a non transient field and this class is supposed to be serializable. this needs to be dealt with before this object can be serialized
    private Backlog backlog;

    ActionListener actionListener = e -> {};

    MouseAdapter openEditDialog =
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    EditBacklogForm form = new EditBacklogForm(backlog);
                    form.setVisible(true);

                    form.addWindowListener(
                            new java.awt.event.WindowAdapter() {
                                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                    init();
                                }
                            });
                }
            };


    public BacklogWidget(Backlog backlog) {
        this.backlog = backlog;
        this.init();
    }

    public void init() {
        removeAll();

        id = new JLabel(backlog.getId().toString());
        id.addMouseListener(openEditDialog);
        points = new JLabel(Double.toString(backlog.getPointValue()));
        points.addMouseListener(openEditDialog);
        businessValue = new JLabel(Double.toString(backlog.getPointValue()));
        businessValue.addMouseListener(openEditDialog);
        name = new JLabel(backlog.getName());
        name.addMouseListener(openEditDialog);
        desc = new JLabel(backlog.getDescription());
        desc.addMouseListener(openEditDialog);
        readyForSprintButton = new JButton("Ready for Sprint");

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
    }
}
