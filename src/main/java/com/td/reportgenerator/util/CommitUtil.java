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

//    public Commit jsonToCommit(ResponseEntity<Object> commitResponse)  {
//        Object commitResponseBody = commitResponse.getBody();
//        JSONObject jsonCommit = null;
//        try {
//            jsonCommit = objectToJSONObject(commitResponseBody);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        Commit commit = jsonObjectToCommit(jsonCommit);
//        return commit;
//    }

    private JSONObject objectToJSONObject(Object commitObject) throws JsonProcessingException { //Parse from object to JSON Object
        ObjectMapper mapper = new ObjectMapper();
        String jsonProject = mapper.writeValueAsString(commitObject);
        JsonNode node = new ObjectMapper().readTree(jsonProject);
        JSONObject json = new JSONObject();

        json.put("id", commitId(node));

        if(node.has("commit")){ //github parse
            JsonNode child = node.get("commit");
            JsonNode author = child.get("author");
            json.put("authorName", author.get("name").asText());
            json.put("authorEmail", author.get("email").asText());
            json.put("creationDate",convert(author.get("date").asText()));
            json.put("message", child.get("message").asText());
            json.put("webUrl", child.get("url").asText());

        }

        //parse to gitlab commit

        json.put("authorName", node.get("author_name").asText());
        json.put("authorEmail", node.get("author_email").asText());
        json.put("creationDate",convert(node.get("created_at").asText()));
        json.put("message", node.get("message").asText());
        json.put("webUrl", node.get("web_url").asText());
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

    private DateTime convert(String dateString) {//Use of DateTime from JodaTime library
        DateTime date;
        date = new DateTime(dateString);
        return date;
    }

    private String commitId(JsonNode bodyResponse){
        String commitId = bodyResponse.has("id") ? bodyResponse.get("id").asText() :
                          bodyResponse.has("sha")? bodyResponse.get("sha").asText(): null;
        return commitId;
    }

}
