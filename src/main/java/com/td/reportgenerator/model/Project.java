package com.td.reportgenerator.model;

import java.util.Date;

public class Project {

    public String id;
    public String description;
    public String name;
    public Date createdAt;
    public String webUrl;
    public Date lastActivity;

    public Project(String id, String description, String name, Date createdAt, String webUrl, Date lastActivity) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.createdAt = createdAt;
        this.webUrl = webUrl;
        this.lastActivity = lastActivity;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }
}
