package net.tvalk.actortemplates.Classes;

/**
 * Created by Gebruiker on 10-4-2017.
 */

public class Template {
    private String name, description;
    private boolean archived;

    public Template() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
