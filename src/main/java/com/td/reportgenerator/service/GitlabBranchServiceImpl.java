package com.td.reportgenerator.service;

import com.td.reportgenerator.interfaces.IBranches;
import com.td.reportgenerator.model.Branch;
import com.td.reportgenerator.model.Commit;
import com.td.reportgenerator.proxy.GitlabDataProxy;
import com.td.reportgenerator.util.BranchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GitlabBranchServiceImpl implements IBranches {

    @Autowired
    GitlabDataProxy gitlabDataProxy;

    @Autowired
    BranchUtil branchUtil;

    public List<Branch> getAllBranchesFromProject(String projectId) {
//        ResponseEntity<Object[]> allCommitsByProjectId = gitlabDataProxy.getAllCommitsByProjectId(projectId);
//        List<Commit> commits = commitUtil.parseResponseBodyToCommitList(allCommitsByProjectId);
        ResponseEntity<Object[]> allBranchesByProjectId = gitlabDataProxy.getAllBranchesByProjectId(projectId);
        List<Branch> branches = branchUtil.parseResponseBodyToCommitList(allBranchesByProjectId);
        System.out.println(branches);
        return branches;
    }

    public Branch getBranchById() {
        return null;
    }
}
