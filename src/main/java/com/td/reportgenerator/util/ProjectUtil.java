package com.td.reportgenerator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.reportgenerator.model.Project;
import jdk.nashorn.internal.ir.ObjectNode;
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
        System.out.println(projectList);

//        for (int i = 0; i < projectList.length; i ++){
////            projectList[i].to
//
////                System.out.println(projectList[i]);
//            System.out.println(projectList[i].getClass().getDeclaredFields().toString());
//        }

        int i = 0;
        for (Object project: projectList){
            System.out.println(project);
            ObjectMapper mapper = new ObjectMapper();
            try {
                String jsonProject = mapper.writeValueAsString(project);
                JsonNode node = new ObjectMapper().readTree(jsonProject);
//                JSONObject node =   new JSONObject(jsonProject);
                if(node.has("description")){
//                    System.out.println(jsonProject);
//                    System.out.println(node.getString("description"));
                    System.out.println("has description!");
                    if(node.get("description").asText() != null || node.get("description").asText() != ""){
                        System.out.println(node.get("description").asText());
                        System.out.println(node.get("description").textValue());
                    }
//                    System.out.println(node.get("description").asText());
//                    System.out.println(node.get("description").textValue());
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
