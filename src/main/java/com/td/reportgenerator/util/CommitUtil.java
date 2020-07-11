package com.td.reportgenerator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.reportgenerator.model.Commit;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CommitUtil {

    public List<Commit> parseResponseBodyToCommitList(ResponseEntity<Object[]> commitsByProject){
        Object[] commitsByProjectBody = commitsByProject.getBody();
        List<Commit> commitList = new ArrayList<>();
        for (Object commit: commitsByProjectBody) {
            Commit commmitObject = new Commit();
            System.out.println(commit);
            try {
                commmitObject = this.jsonObjectToCommit(this.objectToJSONObject(commit));
                System.out.println(commmitObject);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            commitList.add(commmitObject);
        }
        return commitList;
    }

    public Commit parseToCommitObject(ResponseEntity<Object> commitByReferenceAndProjectId) {
        Object commit = commitByReferenceAndProjectId.getBody();
        JSONObject commitAsJSONObject = null;
        try {
            commitAsJSONObject = objectToJSONObject(commit);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Commit commitObject = jsonObjectToCommit(commitAsJSONObject);
        return commitObject;
    }

    private JSONObject objectToJSONObject(Object commitObject) throws JsonProcessingException { //Parse from object to JSON Object
        ObjectMapper mapper = new ObjectMapper();
        String jsonProject = mapper.writeValueAsString(commitObject);
        JsonNode node = new ObjectMapper().readTree(jsonProject);
        JSONObject json = new JSONObject();

        String service = validateService(node);

        JsonNode nodeCommit = node.has("commit")?node.get("commit") : null;
        JsonNode nodeAuthor = null;
        if(nodeCommit != null){
            nodeAuthor = nodeCommit.has("author")? nodeCommit.get("author"):null;
        }
        json.put("id", getCommitId(node));
        json.put("authorName", service.equals("github") ? nodeAuthor.get("name").asText():
                               service.equals("gitlab") ? node.get("author_name").asText() : null);

        json.put("authorEmail", service.equals("github") ? nodeAuthor.get("email").asText():
                                service.equals("gitlab")? node.get("author_email").asText(): null);

        json.put("creationDate", service.equals("github") ? convertToDate(nodeAuthor.get("date").asText()):
                                 service.equals("gitlab") ? convertToDate(node.get("created_at").asText()):null);

        json.put("message", service.equals("github") ? nodeCommit.get("message").asText():
                            service.equals("gitlab") ? node.get("message").asText() : null);

        json.put("webUrl", service.equals("github") ? nodeCommit.get("message").asText():
                           service.equals("gitlab") ? node.get("web_url").asText():null);

//        if(node.has("commit")){ //Parse to github
//            JsonNode child = node.get("commit");
//            JsonNode author = child.get("author");
//            json.put("authorName", author.get("name").asText());
//            json.put("authorEmail", author.get("email").asText());
//            json.put("creationDate",convertToDate(author.get("date").asText()));
//            json.put("message", child.get("message").asText());
//            json.put("webUrl", child.get("url").asText());
//
//        }
//
//        //Parse to gitlab commit
//        json.put("authorName", node.get("author_name").asText());
//        json.put("authorEmail", node.get("author_email").asText());
//        json.put("creationDate",convertToDate((node.get("created_at").asText())));
//        json.put("message", node.get("message").asText());
//        json.put("webUrl", node.get("web_url").asText());

//        json.put("authorName", node.has("value"));
        return json;
    }

    private Commit jsonObjectToCommit(JSONObject commitJSON){ //Parse from JSONOBJECT to Commit object
        ObjectMapper mapper = new ObjectMapper();
        Commit commit = null;
        try {
            commit = mapper.readValue(commitJSON.toString(), Commit.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return commit;
    }

    private DateTime convertToDate(String dateString) {//Use of DateTime from JodaTime library
        DateTime date;
        date = new DateTime(dateString);
        return date;
    }

    private String getCommitId(JsonNode bodyResponse){ //Id of the commit GITLAB and GITHUB
        String commitId = bodyResponse.has("id") ? bodyResponse.get("id").asText() :
                          bodyResponse.has("sha")? bodyResponse.get("sha").asText(): null;
        return commitId;
    }

    private String validateService(JsonNode jsonNode){
        String service;
        if(jsonNode.has("values")){
            service= "bitBucket";
        }else if(jsonNode.has("commit")){
            service= "github";
        }else{
            service =  "gitlab";
        }
        return service;
    }

    private String getCommitAuthor(JsonNode bodyResponse){
        String commitAuthor = bodyResponse.has("author_name") ? bodyResponse.get("author_name").asText():
                              bodyResponse.has("author") ? bodyResponse.get("author").asText() :null;
        return null;
    }
}
