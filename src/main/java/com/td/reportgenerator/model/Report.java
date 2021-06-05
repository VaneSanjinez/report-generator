package com.td.reportgenerator.model;

import java.util.Date;
import java.util.List;

public class Report {
    public String projectName;
    public String projectUrl;
    public Date currentDate;
    public String projectMember;
    List<ReportInfo> reportDetails;

    public Report(String projectName, String projectUrl, Date currentDate, String projectMember, List<ReportInfo> reportDetails) {
        this.projectName = projectName;
        this.projectUrl = projectUrl;
        this.currentDate = currentDate;
        this.projectMember = projectMember;
        this.reportDetails = reportDetails;
    }

    public Report() {
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

    public List<ReportInfo> getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(List<ReportInfo> reportDetails) {
        this.reportDetails = reportDetails;
    }
}
