package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class SpikeStoryStore {

    private static SpikeStoryStore spikeStoryStore;

    /**
     * returns the shared instance of the SpikeStoryStore which contains all spike stories in the
     * system.
     *
     * @return
     */
    public static SpikeStoryStore getInstance() {
        if (spikeStoryStore == null) {
            spikeStoryStore = new SpikeStoryStore();
        }
        return spikeStoryStore;
    }


    private List<SpikeStory> spikeStories;

    private SpikeStoryStore() {
        spikeStories = new ArrayList<>();
    }
    public void setSpikeStories(List<SpikeStory> spikeStories){
        this.spikeStories = spikeStories;
    }
    public void addSpikeStory(SpikeStory spikeStory) {
        spikeStories.add(spikeStory);
    }

    public List<SpikeStory> getSpikeStories() {
        return new ArrayList<>(spikeStories);
    }
    public void deleteSpikeStory(SpikeStory spikeStory){
        List<SpikeStory>  list= getSpikeStories();
        int indexOfSpikeStoryToBeDeleted = -1 ;
        for (SpikeStory US:list){
            if (US.getId().getValue()== spikeStory.getId().getValue()){
                System.out.println("Deleting this Spike Story:"+ spikeStory.getId().toString());
                indexOfSpikeStoryToBeDeleted = list.indexOf(US);
            }
        }
        System.out.println("After Deletion");
        list.remove(indexOfSpikeStoryToBeDeleted);

        setSpikeStories(list);

    }
}
