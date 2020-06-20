package com.td.reportgenerator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.td.reportgenerator.interfaces.IProjects;
import com.td.reportgenerator.model.Project;
import com.td.reportgenerator.proxy.GitlabDataProxy;
import com.td.reportgenerator.util.ProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitLabProjectServiceImpl implements IProjects {

    @Autowired
    GitlabDataProxy gitlabDataProxy;

    @Autowired
    ProjectUtil projectUtil;

    public List<Project> getAllProjects() {
        ResponseEntity<Object[]> allProjects = gitlabDataProxy.getAllProjects();
        System.out.println(allProjects.getBody());
        List<Project> projectResponse = projectUtil.parseToProjectArray(allProjects);
        return projectResponse;
    }

    public Project getProjectById(String projectID) {
        Project project = new Project();
        ResponseEntity<Object> projectResponse = gitlabDataProxy.getProjectById(projectID);
        System.out.println("*********************************");
        System.out.println(projectResponse);
        System.out.println("*********************************");

        try {
            project  = projectUtil.parseToProject(projectResponse);
            System.out.println(project);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        return project;
        return null;
    }

    public ResponseEntity<?> getProjectsByUsername(String username) {
        return null;
    }
}
