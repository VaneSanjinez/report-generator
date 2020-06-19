package com.td.reportgenerator.proxy;

import com.td.reportgenerator.model.Project;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GitlabDataProxy extends GitlabBaseProxy{

    public ResponseEntity<Object []> getAllProjects(){
        //http://localhost:9090/api/projects
        String url = GITLAB_BASE_URL + "projects";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object[]> allProjects = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object[].class
        );
//        return null;
        System.out.println(allProjects);
        return allProjects;
    }

    public ResponseEntity<String> getProjectById(String projectID) {
//        http://localhost:9090/api/projects/18625237
        String url = GITLAB_BASE_URL + "projects/" + projectID;
        String privateToken = personalToken;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Private-Token",privateToken);
        HttpEntity request = new HttpEntity(headers);
        System.out.println(request);
        ResponseEntity<String> project = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class
        );
        System.out.println(project.toString());

        return project;
    }
}
