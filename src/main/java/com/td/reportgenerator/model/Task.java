package com.td.reportgenerator.model;

public class Task {
    public long hours;
    public String name;
    public String details;
    public int percentage;

    public Task() {
    }

    public Task(long hours, String name, String details, int percentage) {
        this.hours = hours;
        this.name = name;
        this.details = details;
        this.percentage = percentage;
    }

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
