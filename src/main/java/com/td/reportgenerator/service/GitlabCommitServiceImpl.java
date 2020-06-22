package com.td.reportgenerator.service;

import com.td.reportgenerator.interfaces.ICommits;
import com.td.reportgenerator.proxy.GitlabDataProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class GitlabCommitServiceImpl implements ICommits{

    @Autowired
    GitlabDataProxy gitlabDataProxy;

    public ResponseEntity<?> getAllProjectCommits(String projectId) {
        ResponseEntity<Object[]> allCommitsByProjectId = gitlabDataProxy.getAllCommitsByProjectId(projectId);
//        System.out.println(allCommitsByProjectId.getBody());
        System.out.println("after calling gitlab data proxy getallcommiutsbyprojectid");
//        return projectResponse;
        return null;
    }
}
