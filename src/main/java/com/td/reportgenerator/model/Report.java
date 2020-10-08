package com.td.reportgenerator.model;

import java.util.Date;

public class Report {
    public Date date;
    public long hours;
    public String name;
    public String details;
    public int percentage;

    public Report(Date date, long hours, String name, String details, int percentage) {
        this.date = date;
        this.hours = hours;
        this.name = name;
        this.details = details;
        this.percentage = percentage;
    }

    public Report(Date date){
        this.date = date;
    }

    public Report() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
