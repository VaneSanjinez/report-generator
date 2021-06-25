package com.td.reportgenerator.model;

import java.util.Date;
import java.util.List;

public class Report {
    public ReportInfo reportInfo;
    public List<ReportDetails> reportDetails;

    public Report(ReportInfo reportInfo, List<ReportDetails> reportDetails) {
        this.reportInfo = reportInfo;
        this.reportDetails = reportDetails;
    }

    public Report() {
    }

    public ReportInfo getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(ReportInfo reportInfo) {
        this.reportInfo = reportInfo;
    }

    public List<ReportDetails> getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(List<ReportDetails> reportDetails) {
        this.reportDetails = reportDetails;
    }
}
