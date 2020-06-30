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

    public List<Commit> parseToCommitList(ResponseEntity<Object[]> commitsByProject){
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

//    private Commit jsonToCommit(ResponseEntity<Object> commitResponse) throws JsonProcessingException {
//        Object commitResponseBody = commitResponse.getBody();
//        JSONObject jsonCommit = objectToJSONObject(commitResponseBody);
//        Commit commit = jsonObjectToCommit(jsonCommit);
//        return commit;
//    }

    private JSONObject objectToJSONObject(Object commitObject) throws JsonProcessingException { //Parse from object to JSON Object
        ObjectMapper mapper = new ObjectMapper();
        String jsonProject = mapper.writeValueAsString(commitObject);
        JsonNode node = new ObjectMapper().readTree(jsonProject);
        JSONObject json = new JSONObject();

        //parse to gitlab commit
        json.put("id", node.get("id"));
        json.put("authorName", node.get("author_name"));
        json.put("authorEmail", node.get("author_email"));
        json.put("creationDate",convert(node.get("created_at").asText()));
        json.put("message", node.get("message"));
        json.put("webUrl", node.get("web_url"));
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

    public DateTime convert(String dateString) {
        DateTime date;
        date = new DateTime(dateString);
        return date;
        }
    }
