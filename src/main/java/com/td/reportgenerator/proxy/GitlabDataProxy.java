package com.td.reportgenerator.proxy;

import com.td.reportgenerator.model.User;
import com.td.reportgenerator.util.GitlabUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
public class GitlabDataProxy extends GitlabBaseProxy{

    @Autowired
    GitlabUtil gitlabUtil;

    RestTemplate restTemplate = new RestTemplate();

    //Projects
    public ResponseEntity<Object []> getAllProjects(){
        //http://localhost:9090/api/projects
        String url = GITLAB_POC_BASE_URL + "projects";
        HttpEntity request = gitlabUtil.declareTemplate(this.personalToken);
        ResponseEntity<Object[]> allProjects = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                Object[].class
        );
        return allProjects;
    }

    public ResponseEntity<Object> getProjectById(String projectID) {
//        http://localhost:9090/api/projects/18625237
        String url = GITLAB_POC_BASE_URL + "projects/" + projectID;
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
        String url = GITLAB_POC_BASE_URL + "projects/users/" + userId;
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
        String url = GITLAB_POC_BASE_URL + "commits/" + projectId;
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

    public ResponseEntity<Object> getCommit(String projectId, String commitRef) {
//        http://localhost:9090/api/commits/18625237/master
        String url = GITLAB_POC_BASE_URL + "commits/" + projectId + "/" + commitRef;
        HttpEntity request = gitlabUtil.declareTemplate(personalToken);
        ResponseEntity<Object> commitByRef = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                Object.class
        );
        return commitByRef;
    }

    public ResponseEntity<Object[]> getCommitsSince(String projectId, String dateSince) {
        //http://localhost:9090/api/commits/18625237/since?since=2019-02-19T00:00:00
        String url = GITLAB_POC_BASE_URL + "commits/" + projectId + "/since";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        builder.queryParam("since", dateSince);
        String uri= builder.build().encode().toUriString();
        HttpEntity request = gitlabUtil.declareTemplate(personalToken);
        ResponseEntity<Object[]> commitsSince = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                request,
                Object[].class

        );
        System.out.println(commitsSince.getBody());
        return commitsSince;
    }

    public ResponseEntity<Object[]> getCommitsUntil(String projectId, String dateUntil) {
        String url = GITLAB_POC_BASE_URL + "commits/" +projectId + "/until";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        builder.queryParam("until", dateUntil);
        String uri = builder.build().encode().toUriString();
        HttpEntity request = gitlabUtil.declareTemplate(personalToken);
        ResponseEntity<Object[]> commitsUntil = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                request,
                Object[].class
        );
        return commitsUntil;
    }

    public ResponseEntity<Object[]> getCommitsSinceUntil(String projectId, String since, String until) {
        String url = GITLAB_POC_BASE_URL + "commits/" +projectId+"/dates";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        builder.queryParam("until", String.valueOf(until));
        builder.queryParam("since", String.valueOf(since));

        String uri = builder.build().encode().toUriString();
        HttpEntity request = gitlabUtil.declareTemplate(personalToken);
        ResponseEntity<Object[]> commitsSinceUntil = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                request,
                Object[].class
        );
        return commitsSinceUntil;
    }

    //Branches
    public ResponseEntity<Object[]> getAllBranchesByProjectId(String projectId) {
        //http://localhost:9090/api/branches/project/18625237/branches
        String url = GITLAB_POC_BASE_URL + "project/" +projectId+ "/branches";
        HttpEntity request = gitlabUtil.declareTemplate(personalToken);
        ResponseEntity<Object[]> projectBranches = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                Object[].class
        );
        return projectBranches;
    }

    public ResponseEntity<Object> getBranchDetails(String projectId, String branchName) {
        //http://localhost:9090/api/branches/project/18625237/branches/master
        String url = GITLAB_POC_BASE_URL + "/project/" + projectId + "/branches/" + branchName;
        HttpEntity request = gitlabUtil.declareTemplate(personalToken);

        ResponseEntity<Object> branchDetails = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                Object.class
        );
        return branchDetails;
    }

    public ResponseEntity<Object[]> getProjectMembers(String projectId) {
        String url = GITLAB_BASE_URL +"/projects/" + projectId + "/members";
        HttpEntity request = gitlabUtil.declareTemplate(personalToken);

        ResponseEntity<Object []> projectMembers = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                Object[].class
        );
        System.out.println(projectMembers);
        return projectMembers;
    }

    public User getUserByEmail(String email){
        String url = GITLAB_BASE_URL + "/users" +  "?search=" + email;
        HttpEntity request = gitlabUtil.declareTemplate(personalToken);

        ResponseEntity<User[]> user = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                User[].class
        );
        List<User> users = Arrays.asList(user.getBody());
        User singleUser = new User();
        singleUser = users.get(0);
        System.out.println("******************");
        System.out.println(users.get(0).getName());
        System.out.println(users.get(0).getId());
        System.out.println(users.get(0).getUsername());
        System.out.println("******************");
        return singleUser;
    }
}
