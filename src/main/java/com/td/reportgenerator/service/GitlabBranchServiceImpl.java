package com.td.reportgenerator.service;

import com.td.reportgenerator.interfaces.IBranches;
import com.td.reportgenerator.model.Branch;
import com.td.reportgenerator.model.Commit;
import com.td.reportgenerator.proxy.GitlabDataProxy;
import com.td.reportgenerator.util.BranchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.xml.ws.http.HTTPException;
import java.util.ArrayList;
import java.util.List;
@Component
public class GitlabBranchServiceImpl implements IBranches {

    @Autowired
    GitlabDataProxy gitlabDataProxy;

    @Autowired
    BranchUtil branchUtil;

    public List<Branch> getAllBranchesFromProject(String projectId) {
        List<Branch> branches=  new ArrayList<>();
        try{
            ResponseEntity<Object[]> allBranchesByProjectId = gitlabDataProxy.getAllBranchesByProjectId(projectId);
            branches = branchUtil.parseResponseBodyToCommitList(allBranchesByProjectId);
        }
        catch(HTTPException e){
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());

        }
        return branches;
    }

    public Branch getBranchById(String projectId, String branchName) {
        Branch branchDetails = new Branch();
        try{
            ResponseEntity<Object> branchResponse = gitlabDataProxy.getBranchDetails(projectId,branchName);
            branchDetails = branchUtil.parseResponseBodyToBranch(branchResponse);
        }
        catch(HTTPException e){
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());
        }
        return branchDetails;
    }
}
