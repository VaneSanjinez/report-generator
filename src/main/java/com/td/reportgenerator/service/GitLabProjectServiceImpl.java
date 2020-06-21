package com.td.reportgenerator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.td.reportgenerator.interfaces.IProjects;
import com.td.reportgenerator.model.Project;
import com.td.reportgenerator.proxy.GitlabDataProxy;
import com.td.reportgenerator.util.ProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        try {
            project  = projectUtil.parseToProject(projectResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return project;
    }

    public List<Project> getProjectsByUsername(String username) {
        ResponseEntity<Object[]> projectsByUserId = gitlabDataProxy.getProjectsByUserId(username);
        List<Project> projects = projectUtil.parseToProjectArray(projectsByUserId);
        return projects;
    }
}
