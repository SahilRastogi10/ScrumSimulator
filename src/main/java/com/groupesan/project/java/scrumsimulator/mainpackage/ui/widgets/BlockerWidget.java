package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blockers;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.SprintBlockerPane;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlockerWidget extends JPanel implements BaseComponent {

    JLabel id;
    JLabel severity;
    JLabel impact;
    JLabel desc;

    // TODO: This is a non transient field and this class is supposed to be serializable. this needs
    // to be dealt with before this object can be serialized
    private Blockers blockers;
    private SprintBlockerPane parentPanel;


    public BlockerWidget(Blockers blockers, SprintBlockerPane parentPanel) {
        this.blockers = blockers;
        this.parentPanel = parentPanel;
        this.init();
    }

    public void init() {
        removeAll();

        id = new JLabel(blockers.getId());
        severity = new JLabel(Double.toString(blockers.getSeverity()));
        impact = new JLabel(Double.toString(blockers.getImpact()));
        desc = new JLabel(blockers.getDescription());
        GridBagLayout myGridBagLayout = new GridBagLayout();

        setLayout(myGridBagLayout);

        add(
                id,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                severity,
                new CustomConstraints(
                        1, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                impact,
                new CustomConstraints(
                        2, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                desc,
                new CustomConstraints(
                        4, 0, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL));
    }
}
