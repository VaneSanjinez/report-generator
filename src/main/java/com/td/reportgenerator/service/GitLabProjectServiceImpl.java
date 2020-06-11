package com.td.reportgenerator.service;

import com.td.reportgenerator.interfaces.IProjects;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements IProjects {

    public ResponseEntity<?> getAllProjects() {

        return null;
    }

    public ResponseEntity<?> getProjectById(String projectID) {
        return null;
    }

    public ResponseEntity<?> getProjectsByUsername(String username) {
        return null;
    }
}
