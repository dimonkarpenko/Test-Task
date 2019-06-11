package com.example.testtask;

public class GitData {
    private String name;
    private String desc;
    private String forks;

    public GitData(String name, String desc, String forks) {
        this.name = name;
        this.desc = desc;
        this.forks = forks;
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
}
