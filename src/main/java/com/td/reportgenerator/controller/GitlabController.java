package com.td.reportgenerator.controller;

import com.td.reportgenerator.model.Project;
import com.td.reportgenerator.service.GitlabCommitServiceImpl;
import com.td.reportgenerator.service.GitlabProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequestMapping(value="/projects", method = RequestMethod.GET)
    public List<Project> getAllProjects (){
        List<Project> projects = gitlabProjectService.getAllProjects();
        return projects;

    }

    @RequestMapping(value = "/projects/{projectId}", method = RequestMethod.GET)
    public Project getProjectById(@PathVariable("projectId") String projectId){
        Project project = gitlabProjectService.getProjectById(projectId);
        return project;
    }

    @RequestMapping(value="/projects/users/{userId}", method = RequestMethod.GET)
    public List<Project> getProjectByUserID(@PathVariable("userId") String userId){
        List<Project> projects = gitlabProjectService.getProjectsByUsername(userId);
        return projects;
    }

//    Commits controller
    @RequestMapping(value="/commits/{projectId}", method = RequestMethod.GET)
    public List<Object> getCommitsByProjectId(@PathVariable("projectId") String projectId){
        ResponseEntity<?> commitsByProjectId = gitlabCommitService.getAllProjectCommits(projectId);

        return null;
     }

}
