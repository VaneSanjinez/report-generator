package com.td.reportgenerator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.reportgenerator.model.Branch;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BranchUtil {
    // array

    public List<Branch> parseResponseBodyToCommitList(ResponseEntity<Object[]> branches){

        Object[] bodyOfBranches= branches.getBody();
        List<Branch> branchList = new ArrayList<>();
        for (Object branch: bodyOfBranches) {
            Branch branchObject = new Branch();
            try {
                branchObject = this.jsonObjectToBranch(this.objectToJSON(branch));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            branchList.add(branchObject);
        }
//        return branchList;
        return null;
    }
    private JSONObject objectToJSON(Object branchObject) throws JsonProcessingException { //Parse from object to JSON Object
        ObjectMapper mapper = new ObjectMapper();
        String jsonBranch = mapper.writeValueAsString(branchObject);
        JsonNode node = new ObjectMapper().readTree(jsonBranch);
        JSONObject json = new JSONObject();

        json.put("name", node.get("name").asText());
        json.put("merged", node.get("merged"));
        json.put("webUrl", node.get("web_url").asText());

        return json;
    }
    private Branch jsonObjectToBranch(JSONObject branchJSON){ //Parse from JSONOBJECT to Commit object
        ObjectMapper mapper = new ObjectMapper();
        Branch branch = null;
        try {
            branch = mapper.readValue(branchJSON.toString(), Branch.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return branch;
    }
}
