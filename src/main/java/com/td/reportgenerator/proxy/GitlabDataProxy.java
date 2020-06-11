package com.td.reportgenerator.proxy;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GitlabDataProxy extends GitlabBaseProxy{

    public ResponseEntity<?> getAllProjects(){
        //http://localhost:9090/api/projects
        String url = GITLABBASEURL + "projects";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<?> allProjects = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object[].class
        );
//        return null;
        return allProjects;
    }

}
