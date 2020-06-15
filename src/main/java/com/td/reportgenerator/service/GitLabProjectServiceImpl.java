package com.td.reportgenerator.service;

import com.td.reportgenerator.interfaces.IProjects;
import com.td.reportgenerator.model.Project;
import com.td.reportgenerator.proxy.GitlabDataProxy;
import com.td.reportgenerator.util.ProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GitLabProjectServiceImpl implements IProjects {

    @Autowired
    GitlabDataProxy gitlabDataProxy;

    @Autowired
    ProjectUtil projectUtil;

    public ResponseEntity<Project[]> getAllProjects() {
        ResponseEntity<?> allProjects = gitlabDataProxy.getAllProjects();
        System.out.println(allProjects.getBody());
//        TODO parse the response from gitlabDataProxy to Project java object
        ResponseEntity<Project[]> projectResponse = projectUtil.parseToProject(allProjects);

        int a  = 5;
        int c = a+7;
        System.out.println(c);
        return projectResponse;
//        return allProjects;
    }

    public ResponseEntity<?> getProjectById(String projectID) {
        return null;
    }

    public ResponseEntity<?> getProjectsByUsername(String username) {
        return null;
    }
}
