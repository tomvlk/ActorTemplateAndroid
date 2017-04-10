package net.tvalk.actortemplates.Classes;

/**
 * Created by Gebruiker on 10-4-2017.
 */

import com.google.firebase.database.Exclude;

import static android.R.attr.name;

public class User {
    private String name;
    @Exclude
    private String actorId;
    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
