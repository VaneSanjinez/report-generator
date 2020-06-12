package com.td.reportgenerator.service;

import com.td.reportgenerator.interfaces.IProjects;
import com.td.reportgenerator.model.Project;
import com.td.reportgenerator.proxy.GitlabDataProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GitLabProjectServiceImpl implements IProjects {

    @Autowired
    GitlabDataProxy gitlabDataProxy;

    public ResponseEntity<Project[]> getAllProjects() {
        ResponseEntity<?> allProjects = gitlabDataProxy.getAllProjects();
//        TODO parse the response from gitlabDataProxy to Project java object
        return null;
//        return allProjects;
    }

    public ResponseEntity<?> getProjectById(String projectID) {
        return null;
    }

    public ResponseEntity<?> getProjectsByUsername(String username) {
        return null;
    }
}
