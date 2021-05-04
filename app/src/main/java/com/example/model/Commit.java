package com.example.model;

import com.google.gson.annotations.SerializedName;

public class Commit {

    @SerializedName("commit")
    private Message commit;

    public Commit(Message commit) {
        this.commit = commit;
    }

    public Message getCommit() {
        return commit;
    }

    public void setCommit(Message commit) {
        this.commit = commit;
    }
}
