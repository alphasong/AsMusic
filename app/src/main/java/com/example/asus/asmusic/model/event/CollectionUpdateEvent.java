package com.example.asus.asmusic.model.event;

public class CollectionUpdateEvent {
    private boolean update;

    public CollectionUpdateEvent(boolean update) {
        this.update = update;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}