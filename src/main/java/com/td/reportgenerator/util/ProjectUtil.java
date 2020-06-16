package com.td.reportgenerator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.reportgenerator.model.Project;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer.Vanilla.std;

@Component
public class ProjectUtil {
    public ResponseEntity<Project[]> parseToProject(ResponseEntity<?> allProjects) {
        System.out.println("from project util");
        Object[] projectList = (Object[]) allProjects.getBody();
        System.out.println(projectList.length);
        JSONArray projectArray = new JSONArray();
        for (Object project: projectList) {
//            System.out.println(project);
            Project projectObject = new Project();
            ObjectMapper mapper = new ObjectMapper();
            try {
                String jsonProject = mapper.writeValueAsString(project);
                JsonNode node = new ObjectMapper().readTree(jsonProject);
                if (node.has("id")) {
                    projectObject.setId(node.get("id").toString());
                    System.out.println(projectObject.getId());
//                    System.out.println("has id!");
                } if (node.has("name")) {
//                    if (node.has("name") || node.path("values").has("name")) {
//                    if (node.has("name")) {
                        System.out.println("has name!");
//                        if(node.has("name")){
                            projectObject.setName(node.get("name").toString());
//                        }else{
//                            projectObject.setName(node.path("values").path("name").toString());
//                        }
                        System.out.println(projectObject.getName());
//                }
                }if (node.has("description") && !node.get("description").toString().isEmpty()) {
                    System.out.println("has description!");
                    projectObject.setName(node.get("description").toString());
                    System.out.println(projectObject.getDescription());
                }
//                System.out.println("at try part");
//                System.out.println(jsonProject);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                e.getMessage();
            }
        }


        return null;
    }
}
