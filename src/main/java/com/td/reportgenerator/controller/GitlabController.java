package com.td.reportgenerator.controller;

import com.td.reportgenerator.model.Project;
import com.td.reportgenerator.service.GitLabProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller("gitlabController")
@RestController
@RequestMapping(value="/gitlab")
public class GitlabController {
    @Autowired
    GitLabProjectServiceImpl gitlabService;

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello from report generator project";
    }

    @RequestMapping(value="/projects", method = RequestMethod.GET)
    public List<Project> getAllProjects (){
        List<Project> projects = gitlabService.getAllProjects();
        return projects;

    }

    @RequestMapping(value = "/projects/{projectId}", method = RequestMethod.GET)
    public Project getProjectById(@PathVariable("projectId") String projectId){
        Project project = gitlabService.getProjectById(projectId);
        return project;
    }

    @RequestMapping(value="/projects/users/{userId}", method = RequestMethod.GET)
    public List<Project> getProjectByUserID(@PathVariable("userId") String userId){
        List<Project> projects = gitlabService.getProjectsByUsername(userId);
        return projects;
    }
}
