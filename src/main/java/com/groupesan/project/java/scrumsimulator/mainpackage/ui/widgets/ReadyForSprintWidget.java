package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.*;

public class ReadyForSprintWidget extends JPanel implements BaseComponent {

    private Backlog backlog;
    private JButton readyForSprintButton;

    public ReadyForSprintWidget(Backlog backlog) {
        this.backlog = backlog;
        this.init();
    }

    public void init() {
        readyForSprintButton = new JButton("Send to Sprint");

        readyForSprintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                UserStory newUS = UserStoryFactory.getInstance()
                                .createNewUserStory(backlog.getName(),
                                        backlog.getDescription(),
                                        backlog.getPointValue(),
                                        backlog.getBusinessValue());
                newUS.doRegister();
                UserStoryStore.getInstance().addUserStory(newUS);

                BacklogStore.getInstance().removeBacklog(backlog);

                readyForSprintButton.setText("Sent to Sprint");
                readyForSprintButton.setEnabled(false);
            }
        });

        add(readyForSprintButton);
    }
}
