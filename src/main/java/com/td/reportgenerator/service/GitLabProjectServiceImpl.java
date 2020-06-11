package com.td.reportgenerator.service;

import com.td.reportgenerator.interfaces.IProjects;
import com.td.reportgenerator.proxy.GitlabDataProxy;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GitLabProjectServiceImpl implements IProjects {

    @Autowired
    GitlabDataProxy gitlabDataProxy;

    public ResponseEntity<?> getAllProjects() {
        ResponseEntity<?> allProjects = gitlabDataProxy.getAllProjects();
        return allProjects;
    }

    public ResponseEntity<?> getProjectById(String projectID) {
        return null;
    }

    public ResponseEntity<?> getProjectsByUsername(String username) {
        return null;
    }
}
