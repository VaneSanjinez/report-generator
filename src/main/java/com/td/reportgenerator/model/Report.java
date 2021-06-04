package com.td.reportgenerator.model;

import java.util.Date;

public class Report {
    public Date commitDate;
    public Date reportDate;
    public long hours;
    public String name;
    public String details;
    public int percentage;

    public Report(Date commitDate, Date reportDate, long hours, String name, String details, int percentage) {
        this.commitDate = commitDate;
        this.reportDate = reportDate;
        this.hours = hours;
        this.name = name;
        this.details = details;
        this.percentage = percentage;
    }

    public Report() {
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
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
