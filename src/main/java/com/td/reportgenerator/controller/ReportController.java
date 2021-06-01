package com.td.reportgenerator.controller;

import com.td.reportgenerator.model.Commit;
import com.td.reportgenerator.model.Report;
import com.td.reportgenerator.service.GitlabCommitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReportController {
    @Autowired
    GitlabCommitServiceImpl gitlabCommitService;

    @RequestMapping(value="/report", method = RequestMethod.GET)
    public Report getTodayReport(){
        return null;
    }
    //Gitlab report
    //get all commits of specific user
    @RequestMapping(value="/report/project/{projectId}/commits/{authorEmail}", method = RequestMethod.GET)
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
    
}
