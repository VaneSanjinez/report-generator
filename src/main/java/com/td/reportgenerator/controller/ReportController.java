package com.td.reportgenerator.controller;

import com.td.reportgenerator.model.Commit;
import com.td.reportgenerator.model.Report;
import com.td.reportgenerator.model.ReportDetails;
import com.td.reportgenerator.model.ReportInfo;
import com.td.reportgenerator.service.GitlabCommitServiceImpl;
import com.td.reportgenerator.service.GitlabReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/report")
public class ReportController {
    @Autowired
    GitlabCommitServiceImpl gitlabCommitService;

    @Autowired
    GitlabReportService gitlabReportService;

    @RequestMapping(value="/report", method = RequestMethod.GET)
    public Report getTodayReport(){
        return null;
    }
    //Gitlab report
    //get all commits of specific user
    @RequestMapping(value="/project/{projectId}/commits/{authorEmail}", method = RequestMethod.GET)
    public List<Commit> getAllCommitsByAuthorEmail(@PathVariable("projectId") String projectId, @PathVariable("authorEmail") String authorEmail){
        List<Commit> commitListByAuthor = new ArrayList<>();
        List<Commit> projectCommits = gitlabCommitService.getAllProjectCommits(projectId);
        for(int i = 0; i< projectCommits.size(); i++){
            if(projectCommits.get(i).authorEmail.equals(authorEmail)){
                commitListByAuthor.add(projectCommits.get(i));
            }
        }
        return commitListByAuthor;
    }

    @RequestMapping(value="/{service}/{projectId}/{author}", method = RequestMethod.GET)
    public Report getReport(@PathVariable("service") String service,
                            @PathVariable("projectId") String projectId,
                            @PathVariable("author") String author){
        Report report = new Report();
        ReportInfo reportInfo = gitlabReportService.getGitlabReportInfo(author,projectId);
        List<ReportDetails> reportDetails = gitlabReportService.getGitlabReportDetails(projectId,author);

        switch (service){
            case "gitlab":
                report.setReportInfo(reportInfo);
                report.setReportDetails(reportDetails);
                break;
            case "github":
                return null;
            case "bitbucket":
                return null;
        }

        return report;
    }
    @RequestMapping(value = "/reportinfo/{projectId}/{author}", method = RequestMethod.GET)
    public ReportInfo getReportInfo(@PathVariable("projectId") String projectId,
                                    @PathVariable("author") String author){
        ReportInfo reportInfo = gitlabReportService.getGitlabReportInfo(author, projectId);
        return reportInfo;
    }

    @RequestMapping(value = "reportdetails/{projectId}/{author}", method = RequestMethod.GET)
    public List<ReportDetails> getReportDetails(@PathVariable("projectId") String projectId,
                                                @PathVariable("author") String author){
        List<ReportDetails> reportDetails = gitlabReportService.getGitlabReportDetails(projectId, author);
        return reportDetails;
    }

    @RequestMapping(value="gitlab/{projectId}/{authorName}", method = RequestMethod.GET)
    public List<Commit> getCommitsByAuthorName(@PathVariable("projectId") String projectId,
                                               @PathVariable("authorName") String authorName){
        List<Commit> authorNameCommits = gitlabCommitService.getCommitsByProjectIdAndAuthorName(projectId, authorName);
        return authorNameCommits;
    }
}
