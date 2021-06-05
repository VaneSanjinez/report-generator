package com.td.reportgenerator.model;

import java.util.Date;

public class ReportInfo {

    public Date commitDate;
    public int hours;
    public String authorName;
    public String details;
    public int percent;

    public ReportInfo() {
    }

    public ReportInfo(Date commitDate, int hours, String authorName, String details, int percent) {
        this.commitDate = commitDate;
        this.hours = hours;
        this.authorName = authorName;
        this.details = details;
        this.percent = percent;
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
