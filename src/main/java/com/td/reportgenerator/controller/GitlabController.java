package com.td.reportgenerator.controller;

import com.td.reportgenerator.model.Branch;
import com.td.reportgenerator.model.Commit;
import com.td.reportgenerator.model.Project;
import com.td.reportgenerator.service.GitlabBranchServiceImpl;
import com.td.reportgenerator.service.GitlabCommitServiceImpl;
import com.td.reportgenerator.service.GitlabProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.AbstractDocument;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/gitlab")
public class GitlabController {

    //Projects Service Implementation
    @Autowired
    GitlabProjectServiceImpl gitlabProjectService;

    //Commits Service Implementation
    @Autowired
    GitlabCommitServiceImpl gitlabCommitService;

    //Branches Service Implementation
    @Autowired
    GitlabBranchServiceImpl gitlabBranchService;

//    hello controller
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello from report generator project";
    }

//    Projects controller

    //Get all projects
    @RequestMapping(value="/projects", method = RequestMethod.GET)
    public List<Project> getAllProjects (){
        List<Project> projects = gitlabProjectService.getAllProjects();
        return projects;

    }

    //Get by project id
    @RequestMapping(value = "/projects/{projectId}", method = RequestMethod.GET)
    public Project getProjectById(@PathVariable("projectId") String projectId){
        Project project = gitlabProjectService.getProjectById(projectId);
        return project;
    }

//    get projects by user id
    @RequestMapping(value="/projects/users/{userId}", method = RequestMethod.GET)
    public List<Project> getProjectByUserID(@PathVariable("userId") String userId){
        List<Project> projects = gitlabProjectService.getProjectsByUsername(userId);
        return projects;
    }

//    Commits controller

    //Get all commits from a project (by project id)
    @RequestMapping(value="/commits/{projectId}", method = RequestMethod.GET)
    public List<Commit> getCommitsByProjectId(@PathVariable("projectId") String projectId){
        List<Commit> commitsByProjectId = gitlabCommitService.getAllProjectCommits(projectId);
        return commitsByProjectId;
     }

     //Get commit by id,branch
    @RequestMapping(value="/commits/{projectId}/{commitRef}", method = RequestMethod.GET)
    public Commit getCommitByReference(@PathVariable("projectId") String projectId,
                                                  @PathVariable("commitRef") String commitRef){
        Commit commit = gitlabCommitService.getCommitByReference(projectId, commitRef);
        return commit;
    }

    //Get commits by dates with params for since, until & since until
    //Validation to build the request according of what is present on call
    @RequestMapping(value="/commits/{projectId}/dates", method=RequestMethod.GET)
    public List<Commit> getCommitsSinceDate(@PathVariable("projectId") String projectId,
                                                        @RequestParam("since") Optional<String> since,
                                                        @RequestParam("until") Optional<String> until){
        List<Commit> commitsResponse = new ArrayList<>();
        if(since.isPresent() && !until.isPresent()){
            commitsResponse = gitlabCommitService.getCommitsSinceDate(projectId, String.valueOf(since));
        }else if (until.isPresent() && !since.isPresent()){
            commitsResponse = gitlabCommitService.getCommitsUntilDate(projectId, String.valueOf(until));
        }
        else if (since.isPresent() && until.isPresent()){
            commitsResponse = gitlabCommitService.getCommitsSinceUntilDates(projectId, String.valueOf(since), String.valueOf(until));
        }
        return commitsResponse;
    }

    @RequestMapping(value = "/branches/{projectId}", method = RequestMethod.GET)
    public List<Branch> getAllProjectBranches (@PathVariable("projectId") String projectId){
        List<Branch> projectBranches = gitlabBranchService.getAllBranchesFromProject(projectId);
        return null;
    }
}
