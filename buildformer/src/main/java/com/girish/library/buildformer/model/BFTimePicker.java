package com.girish.library.buildformer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BFTimePicker extends BFView {
    @JsonIgnore
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
