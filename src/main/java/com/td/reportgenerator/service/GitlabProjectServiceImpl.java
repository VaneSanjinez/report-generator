package com.td.reportgenerator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.td.reportgenerator.interfaces.IProjects;
import com.td.reportgenerator.model.Project;
import com.td.reportgenerator.model.User;
import com.td.reportgenerator.proxy.GitlabDataProxy;
import com.td.reportgenerator.util.ProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GitlabProjectServiceImpl implements IProjects {

    @Autowired
    GitlabDataProxy gitlabDataProxy;

    @Autowired
    ProjectUtil projectUtil;

    public List<Project> getAllProjects() {
        List<Project> projectResponse = new ArrayList<>();
        try{
            ResponseEntity<Object[]> allProjects = gitlabDataProxy.getAllProjects();
            projectResponse = projectUtil.parseToProjectArray(allProjects);

        }
        catch (HTTPException e){
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());
        }
        return projectResponse;
    }

    public Project getProjectById(String projectID) {
        Project project = new Project();
        try {
            ResponseEntity<Object> projectResponse = gitlabDataProxy.getProjectById(projectID);
            try {
                project  = projectUtil.parseToProject(projectResponse);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        catch (HTTPException e){
            System.out.println(e.getStatusCode());
        }
        return project;
    }

    public List<Project> getProjectsByUsername(String username) {
        List<Project> projects = new ArrayList<>();
        try{
            ResponseEntity<Object[]> projectsByUserId = gitlabDataProxy.getProjectsByUserId(username);
            projects = projectUtil.parseToProjectArray(projectsByUserId);
        }
        catch (HTTPException e){
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());
        }

        return projects;
    }

    public ResponseEntity<Object[]> getProjectMembers(String projectId) {
        ResponseEntity<Object[]> projectMembers = null;
        try{
            projectMembers = gitlabDataProxy.getProjectMembers(projectId);
            return projectMembers;
        }catch (HTTPException e){
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());
        }
        return projectMembers;
    }
    public User getUserByEmail(String email){
        User user = null;
        try{
            user = gitlabDataProxy.getUserByEmail(email);
            return user;
        }catch (HTTPException e){
            System.out.println(e.getMessage());
        }
        return user;
    }
}
