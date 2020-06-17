package com.td.reportgenerator.controller;

import com.td.reportgenerator.model.Project;
import com.td.reportgenerator.proxy.GitlabDataProxy;
import com.td.reportgenerator.service.GitLabProjectServiceImpl;
import org.apache.coyote.Response;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
