package com.td.reportgenerator.interfaces;

import com.td.reportgenerator.model.Project;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProjects {
    List<Project> getAllProjects();

    Project getProjectById(String projectID);

    List<Project> getProjectsByUsername(String username);
}
