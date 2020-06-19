package com.td.reportgenerator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.reportgenerator.model.Project;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectUtil {
    public List<Project> parseToProjectArray(ResponseEntity<Object[]> allProjects) {
        System.out.println("from project util");
        Object[] projectList = allProjects.getBody();
        List<Project> projectArray = new ArrayList<Project>();
        for (Object project: projectList) {
            Project projectObject = new Project();
            try {
                projectObject = this.jsonToProject(this.responseToJSONProject(project));
            } catch (IOException e) {
                e.printStackTrace();
            }
            projectArray.add(projectObject);
        }
        System.out.println("after for bucle size: " + projectArray.size());

        return projectArray;
    }

    private JSONObject responseToJSONProject (Object project) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonProject = mapper.writeValueAsString(project);
        JsonNode node = new ObjectMapper().readTree(jsonProject);
        JSONObject json = new JSONObject();

        json.put("id", node.get("id").toString());
        json.put("name", node.get("name").toString());
        json.put("description", node.get("description").toString());
        json.put("webUrl", node.get("web_url").toString());

        return json;
    }

    private Project jsonToProject(JSONObject jsonProject){
        ObjectMapper mapper = new ObjectMapper();
        Project project = null;
        try {
            project = mapper.readValue(jsonProject.toString(), Project.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return project;
    }

    public Project parseToProject(ResponseEntity<String> projectResponse) throws JsonProcessingException {
        String projectResponseBody = projectResponse.getBody();
        JSONObject jsonProject = responseToJSONProject(projectResponseBody);
        Project project = jsonToProject(jsonProject);
        System.out.println(project);
//        return project;
        return null;
    }
}
