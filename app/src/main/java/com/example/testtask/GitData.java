package com.example.testtask;

import android.widget.ImageView;

public class GitData {
    private String name;
    private String desc;
    private String forks;
    private String thumbnail;

    private String link;

    public GitData(String name, String desc, String forks, String thumbnail, String link) {
        this.name = name;
        this.desc = desc;
        this.forks = forks;
        this.thumbnail = thumbnail;
        this.link = link;
    }


    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getForks() {
        return forks;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getLink() {
        return link;
    }
}
