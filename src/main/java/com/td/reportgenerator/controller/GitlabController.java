package com.td.reportgenerator.controller;

import com.td.reportgenerator.model.Commit;
import com.td.reportgenerator.model.Project;
import com.td.reportgenerator.service.GitlabCommitServiceImpl;
import com.td.reportgenerator.service.GitlabProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Controller("gitlabController")
@RestController
@RequestMapping(value="/gitlab")
public class GitlabController {
    //Projects
    @Autowired
    GitlabProjectServiceImpl gitlabProjectService;

    //Commits
    @Autowired
    GitlabCommitServiceImpl gitlabCommitService;

//    hello controller
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello from report generator project";
    }

//    Projects controller
    //get all projects
    @RequestMapping(value="/projects", method = RequestMethod.GET)
    public List<Project> getAllProjects (){
        List<Project> projects = gitlabProjectService.getAllProjects();
        return projects;

    }

    //get by project id
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

    //get commits since
    @RequestMapping(value="/commits/{projectId}/dates", method=RequestMethod.GET)
    public List<Commit> getCommitsSinceDate(@PathVariable("projectId") String projectId,
                                                        @RequestParam("since") Optional<String> since,
                                                        @RequestParam("until") Optional<String> until){
//        ResponseEntity<Object[]> commitsSince = gitlabCommitService.getCommitsByDates(projectId,since,until);
        List<Commit> commitsResponse = new ArrayList<>();
        if(since.isPresent() && !until.isPresent()){
            System.out.println("only since is present");
            commitsResponse = gitlabCommitService.getCommitsSinceDate(projectId, String.valueOf(since));
        }else if (until.isPresent() && !since.isPresent()){
            commitsResponse = gitlabCommitService.getCommitsUntilDate(projectId, String.valueOf(until));
            System.out.println("only until is present");
        }
        else if (since.isPresent() && until.isPresent()){
            commitsResponse = gitlabCommitService.getCommitsSinceUntilDates(projectId, String.valueOf(since), String.valueOf(until));
            System.out.println("since and until are present!");
        }
//        System.out.println(commitsSince.getBody());
        return commitsResponse;
    }
}
