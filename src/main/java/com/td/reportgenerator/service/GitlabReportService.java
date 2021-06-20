package com.td.reportgenerator.service;

import com.td.reportgenerator.model.Commit;
import com.td.reportgenerator.model.Project;
import com.td.reportgenerator.model.Report;
import com.td.reportgenerator.model.ReportInfo;
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

    public Report gitlabReport (String projectId, String authorEmail){
        Report gitlabReport = new Report();
        Project project = gitlabProjectService.getProjectById(projectId);
        List<Commit> commits =  gitlabCommitService.getAllProjectCommits(projectId);
        List<Commit> commitsByAuthor = gitlabCommitService.getCommitsByProjectIdAndAuthorEmail(projectId,authorEmail);

        String projectMember = commitsByAuthor.get(0).getAuthorName();

        //build gitlab report
        Date today = new Date();
        today.getTime();
        System.out.println(today.getTime());

        gitlabReport.setCurrentDate(today);
        gitlabReport.setProjectName(project.getName());
        gitlabReport.setProjectUrl(project.getWebUrl());
        gitlabReport.setProjectMember(projectMember);

        //report detail
//        List<ReportInfo> reportInfo = new ArrayList<>();
//        for (int i =0; i < commitsByAuthor.size();i++){
//            ReportInfo info = new ReportInfo();
//            info.setAuthorName(commitsByAuthor.get(i).getAuthorName());
//            info.setCommitDate(commitsByAuthor.get(i).getCreationDate());
//            info.setDetails(commitsByAuthor.get(i).getMessage());
//            reportInfo.add(info);
//        }
//
//        gitlabReport.setReportDetails(reportInfo);
        return gitlabReport;
    }
}
