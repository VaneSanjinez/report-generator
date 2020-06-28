package com.td.reportgenerator.proxy;

import com.td.reportgenerator.util.GitlabUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GitlabDataProxy extends GitlabBaseProxy{

    @Autowired
    GitlabUtil gitlabUtil;

    private String privateToken = null;
    RestTemplate restTemplate = new RestTemplate();

    //Projects
    public ResponseEntity<Object []> getAllProjects(){
        //http://localhost:9090/api/projects
        String url = GITLAB_BASE_URL + "projects";
        HttpEntity request = gitlabUtil.declareTemplate(this.personalToken);
        ResponseEntity<Object[]> allProjects = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object[].class
        );
        System.out.println(allProjects);
        return allProjects;
    }

    public ResponseEntity<Object> getProjectById(String projectID) {
//        http://localhost:9090/api/projects/18625237
        String url = GITLAB_BASE_URL + "projects/" + projectID;
        HttpEntity request = gitlabUtil.declareTemplate(this.personalToken);
        ResponseEntity<Object> project = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                Object.class
        );
        return project;
    }

    public ResponseEntity<Object[]> getProjectsByUserId(String userId) {
//        http://localhost:9090/api/projects/users/vane-sanjinez?private_token=cxXdxSAm8KmZZe7RZ7i6
        String url = GITLAB_BASE_URL + "projects/users/" + userId;
        HttpEntity request = gitlabUtil.declareTemplate(this.personalToken);
        ResponseEntity<Object[]> projectsByUserId = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                Object[].class
        );
        return projectsByUserId;
    }

    //Commits
    public ResponseEntity<Object[]> getAllCommitsByProjectId(String projectId){
//        http://localhost:9090/api/commits/{projectId}
        String url = GITLAB_BASE_URL + "commits/" + projectId;
        HttpEntity request = gitlabUtil.declareTemplate(this.personalToken);
        ResponseEntity<Object[]> commitsByProjectId = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                Object[].class
        );

        System.out.println(commitsByProjectId.getBody());
        return commitsByProjectId;
    }
}
