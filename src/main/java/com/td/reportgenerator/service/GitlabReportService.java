package com.td.reportgenerator.service;

import com.td.reportgenerator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GitlabReportService {

    @Autowired
    GitlabProjectServiceImpl gitlabProjectService;

    @Autowired
    GitlabCommitServiceImpl gitlabCommitService;

    public ReportInfo getGitlabReportInfo(String email, String projectId){
        String projectMember = gitlabProjectService.getUserByEmail(email).getName();
        Project project = gitlabProjectService.getProjectById(projectId);
        Date today = new Date();
        today.getTime();

        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setProjectMember(projectMember);
        reportInfo.setCurrentDate(today);
        reportInfo.setProjectName(project.getName());
        reportInfo.setProjectUrl(project.getWebUrl());

        return reportInfo;
    }

    public List<ReportDetails> getGitlabReportDetails(String projectId, String authorEmail){
//        List<Commit> commitsByAuthor = gitlabCommitService.getCommitsByProjectIdAndAuthorEmail(projectId,authorEmail);
        List<Commit> commitsByAuthor = gitlabCommitService.getCommitsByProjectIdAndAuthorName(projectId,authorEmail);
        List<ReportDetails> reportDetails = new ArrayList<>();

        for (int i =0; i < commitsByAuthor.size();i++){
            ReportDetails details = new ReportDetails();
            details.setCommitDate(commitsByAuthor.get(i).getCreationDate());
            details.setDetails(commitsByAuthor.get(i).getMessage());
            reportDetails.add(details);
        }

        return reportDetails;

    }
}
