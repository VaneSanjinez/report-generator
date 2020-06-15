package com.td.reportgenerator.proxy;

import com.td.reportgenerator.model.Project;
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
        String url = GITLAB_BASE_URL + "projects";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<?> allProjects = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object[].class
        );
//        return null;
        System.out.println(allProjects);
        return allProjects;
    }

}
