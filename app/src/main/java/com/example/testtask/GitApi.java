package com.example.testtask;

import android.graphics.Bitmap;


public class GitApi {

    private final Bitmap userAvatar;

    private final String repoName;

    private final String descRepo;

    private final Integer numberOfForks;

    public GitApi(Bitmap userAvatar, String repoName, String descRepo, Integer numberOfForks) {
        this.userAvatar = userAvatar;
        this.repoName = repoName;
        this.descRepo = descRepo;
        this.numberOfForks = numberOfForks;
    }

    public Bitmap getUserAvatar() {
        return userAvatar;
    }

    public String getRepoName() {
        return repoName;
    }

    public String getDescRepo() {
        return descRepo;
    }

    public Integer getNumberOfForks() {
        return numberOfForks;
    }
}
