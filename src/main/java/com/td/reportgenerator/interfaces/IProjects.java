package com.td.reportgenerator.interfaces;

import com.td.reportgenerator.model.Project;

import java.util.List;

public interface IProjects {
    List<Project> getAllProjects();

    Project getProjectById(String projectID);

    List<Project> getProjectsByUsername(String username);
}
