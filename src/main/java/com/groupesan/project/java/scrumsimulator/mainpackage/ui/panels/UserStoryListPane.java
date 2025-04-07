package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.EditUserStoryForm;

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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class UserStoryListPane extends JFrame implements BaseComponent {
    private JPanel subPanel;
    private List<UserStoryWidget> widgets = new ArrayList<>();

    public UserStoryListPane() {
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("User Story list");
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window on the screen
        
        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);
        subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());

        // demo/testing widgets
        //        UserStory aUserStory = UserStoryFactory.getInstance().createNewUserStory("foo",
        // "bar", 2);
        //        UserStory aUserStory2 =
        //                UserStoryFactory.getInstance().createNewUserStory("foo2", "bar2", 4);
        //        widgets.add(new UserStoryWidget(aUserStory));
        //        widgets.add(new UserStoryWidget(aUserStory2));
        
        int i = 0;
        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            System.out.println("Displaying User Story: " + userStory.getId());
            widgets.add(new UserStoryWidget(userStory, this));
        }
        
        subPanel.setLayout(new GridBagLayout());
        i = 0;
        for (UserStoryWidget widget : widgets) {
            subPanel.add(widget, new CustomConstraints(
            0, i++, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));
        }

        myJpanel.add(new JScrollPane(subPanel),
            new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

        JButton newSprintButton = new JButton("New User Story");
        newSprintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewUserStoryForm form = new NewUserStoryForm();
                form.setVisible(true);
                form.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        UserStory userStory = form.getUserStoryObject();
                        UserStoryStore.getInstance().addUserStory(userStory);
                        widgets.add(new UserStoryWidget(userStory, UserStoryListPane.this));
                                        int idx = widgets.size() - 1;
                                        subPanel.add(widgets.get(idx),
                                                new CustomConstraints(
                                                        0,
                                                        idx,
                                                        GridBagConstraints.WEST,
                                                        1.0,
                                                        0.1,
                                                        GridBagConstraints.HORIZONTAL));
                                        subPanel.revalidate();
                                        subPanel.repaint();
                                        refreshUserStoryList();
                    }
                });
            }
        });
        myJpanel.add(
                newSprintButton,
                new CustomConstraints(
                    0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));
        JButton updateUserStoryButton = new JButton("Edit User Story");
        updateUserStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<UserStory> stories = UserStoryStore.getInstance().getUserStories();
                if (stories.isEmpty()) {
                    JOptionPane.showMessageDialog(UserStoryListPane.this, "No user stories available to edit.");
                } else {
                    String[] storyNames = new String[stories.size()];
                    for (int i = 0; i < stories.size(); i++) {
                        storyNames[i] = stories.get(i).getName();
                    }
                                
                    String selectedStoryName = (String) JOptionPane.showInputDialog(
                        UserStoryListPane.this,
                            "Select a User Story to Edit:",
                            "Edit User Story",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            storyNames,
                            storyNames[0]  // default selection
                    );
                    if (selectedStoryName != null) {
                         UserStory selectedStory = null;
                        for (UserStory story : stories) {
                            if (story.getName().equals(selectedStoryName)) {
                                selectedStory = story;
                                break;
                            }
                        }
                        if (selectedStory != null) {
                            EditUserStoryForm editForm = new EditUserStoryForm(selectedStory, UserStoryListPane.this);
                            editForm.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(UserStoryListPane.this, "User story not found.");
                        }
                    }
                }
            }
        });
                                        
                myJpanel.add(updateUserStoryButton,
                    new CustomConstraints(0, 2, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));
        add(myJpanel);

    }
    public void shutWindow(){
        dispose();
    }
    public void refreshUserStoryList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refreshUserStoryList'");
    }
}
