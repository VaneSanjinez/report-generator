package com.td.reportgenerator.model;

public class Project {

    public String id;
    public String description;
    public String name;
    public String webUrl;

    public Project(String id, String description, String name, String webUrl) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.webUrl = webUrl;
    }

    public Project() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

}
