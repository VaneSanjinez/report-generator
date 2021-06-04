package com.td.reportgenerator.service;

import com.td.reportgenerator.model.Commit;
import com.td.reportgenerator.model.Project;
import com.td.reportgenerator.model.Report;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GitlabReportService {

    @Autowired
    GitlabProjectServiceImpl gitlabProjectService;

    @Autowired
    GitlabCommitServiceImpl gitlabCommitService;

    public Report gitlabReport (String projectId, String author){
        Report gitlabReport = new Report();
        Project project = gitlabProjectService.getProjectById(projectId);
        List<Commit> commits =  gitlabCommitService.getAllProjectCommits(projectId);
        List<Commit> commitsByAuthor = gitlabCommitService.getCommitsByProjectIdAndAuthor(projectId,author);

        //build report
        LocalDate localTime = new LocalDate();
        Date today = new Date();
        today.getTime();
        System.out.println(today.getTime());

        gitlabReport.setReportDate(today);
        return gitlabReport;
//        return null;
    }
}
