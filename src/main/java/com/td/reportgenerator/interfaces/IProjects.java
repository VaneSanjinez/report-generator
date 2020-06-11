package com.td.reportgenerator.interfaces;

import org.springframework.http.ResponseEntity;

public interface IProjects {
    public ResponseEntity<?> getAllProjects();

    public ResponseEntity<?> getProjectById(String projectID);

    public ResponseEntity<?> getProjectsByUsername(String username);
}
