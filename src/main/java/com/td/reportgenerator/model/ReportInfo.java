package com.td.reportgenerator.model;

import java.util.Date;

public class ReportInfo {
    public String projectName;
    public String projectUrl;
    public Date currentDate;
    public String projectMember;

    public ReportInfo(String projectName, String projectUrl, Date currentDate, String projectMember) {
        this.projectName = projectName;
        this.projectUrl = projectUrl;
        this.currentDate = currentDate;
        this.projectMember = projectMember;
    }

    public ReportInfo() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getProjectMember() {
        return projectMember;
    }

    public void setProjectMember(String projectMember) {
        this.projectMember = projectMember;
    }
}
