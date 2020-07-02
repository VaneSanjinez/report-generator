package com.td.reportgenerator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.reportgenerator.model.Project;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectUtil {

    public List<Project> parseToProjectArray(ResponseEntity<Object[]> allProjects) {
        Object[] projectList = allProjects.getBody();
        List<Project> projectArray = new ArrayList<>();
        for (Object project: projectList) {
            Project projectObject = new Project();
            try {
                projectObject = this.jsonToProject(this.responseToJSONProject(project));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            projectArray.add(projectObject);
        }

        return projectArray;
    }

    public Project parseToProject(ResponseEntity<Object> projectResponse) throws JsonProcessingException {
        Object projectResponseBody = projectResponse.getBody();
        JSONObject jsonProject = responseToJSONProject(projectResponseBody);
        Project project = jsonToProject(jsonProject);
        return project;
    }

    private JSONObject responseToJSONProject (Object project) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonProject = mapper.writeValueAsString(project);
        JsonNode node = new ObjectMapper().readTree(jsonProject);

        JSONObject json = new JSONObject();

        //validate if object comming from bitbucket
        if(node.has("values")){
            JsonNode child = node.get("values");
            json.put("id", child.get("id").asText());
            json.put("name", child.get("name").asText());
            json.put("description", child.get("description").asText());
            json.put("webUrl", child.path("link").get("url").asText());

        }else{ //github and gitlab
            json.put("id", node.get("id"));
            json.put("name", node.get("name").asText());
            json.put("description", verifyDescription(node));
            json.put("webUrl", (node.has("web_url"))? node.get("web_url").asText():
                                node.has("url")? node.get("url").asText():null);
        }
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

    private String verifyDescription (JsonNode project){
        String projectDescription = null;
        if(project.has("description")){
            projectDescription = validateDescriptionContent(project.get("description"));
        }else if(project.has("body")) {
            projectDescription = validateDescriptionContent(project.get("body"));
        }
        return projectDescription;
    }

    private String validateDescriptionContent(JsonNode field){
        String fieldValidated = null;
        if(field == null || field.asText().equals("null")){
            fieldValidated = "";
        }else{
            fieldValidated = field.asText();
        }
        return fieldValidated;
    }
}
