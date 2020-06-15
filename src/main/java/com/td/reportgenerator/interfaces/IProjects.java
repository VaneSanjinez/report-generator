package com.td.reportgenerator.interfaces;

import com.td.reportgenerator.model.Project;
import org.springframework.http.ResponseEntity;

public interface IProjects {
//    public ResponseEntity<Project[]> getAllProjects();
    public ResponseEntity<?> getAllProjects();

    public ResponseEntity<?> getProjectById(String projectID);

    public ResponseEntity<?> getProjectsByUsername(String username);
}
