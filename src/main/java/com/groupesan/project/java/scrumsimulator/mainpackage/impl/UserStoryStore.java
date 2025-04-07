package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class UserStoryStore {

    private static UserStoryStore userStoryStore;

    public static UserStoryStore getInstance() {
        if (userStoryStore == null) {
            userStoryStore = new UserStoryStore();
        }
        return userStoryStore;
    }

    private List<UserStory> userStories;

    private UserStoryStore() {
        userStories = new ArrayList<>();
    }

    public void setUserStories(List<UserStory> userStories){
        this.userStories = userStories;
    }

    public void addUserStory(UserStory userStory) {
        userStories.add(userStory);
    }

    public List<UserStory> getUserStories() {
        return new ArrayList<>(userStories);
    }

    public void deleteUserStory(UserStory userStory){
        List<UserStory> list = getUserStories();
        int indexOfUserStoryToBeDeleted = -1;
        for (UserStory us : list) {
            if (us.getId().getValue() == userStory.getId().getValue()) {
                System.out.println("Deleting this User Story: " + userStory.getId().toString());
                indexOfUserStoryToBeDeleted = list.indexOf(us);
            }
        }
        System.out.println("After Deletion");
        list.remove(indexOfUserStoryToBeDeleted);
        setUserStories(list);
    }
}
