package com.td.reportgenerator.interfaces;

import com.td.reportgenerator.model.Project;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProjects {
//    public ResponseEntity<Project[]> getAllProjects();
//    public ResponseEntity<?> getAllProjects();
    public List<Project> getAllProjects();

    public ResponseEntity<?> getProjectById(String projectID);

    public ResponseEntity<?> getProjectsByUsername(String username);
}
