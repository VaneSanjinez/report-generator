package com.td.reportgenerator.service;

import com.td.reportgenerator.interfaces.ICommits;
import com.td.reportgenerator.model.Commit;
import com.td.reportgenerator.model.Project;
import com.td.reportgenerator.proxy.GitlabDataProxy;
import com.td.reportgenerator.util.CommitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GitlabCommitServiceImpl implements ICommits{

    @Autowired
    GitlabDataProxy gitlabDataProxy;

    @Autowired
    CommitUtil commitUtil;

    public List<Commit> getAllProjectCommits(String projectId) {
        ResponseEntity<Object[]> allCommitsByProjectId = gitlabDataProxy.getAllCommitsByProjectId(projectId);
        List<Commit> commits = commitUtil.parseResponseBodyToCommitList(allCommitsByProjectId);
        return commits;
    }

    public Commit getCommitByReference(String projectId, String commitRef) {
        ResponseEntity<Object> commitByReferenceAndProjectId = gitlabDataProxy.getCommit(projectId,commitRef);
        Commit commitByProjectIdAndRef = commitUtil.parseToCommitObject(commitByReferenceAndProjectId);
        return commitByProjectIdAndRef;
    }

    public ResponseEntity<Object[]> getCommitsSinceDate(String projectId, String dateSince){
        ResponseEntity<Object[]> commitsSince = gitlabDataProxy.getCommitsSince(projectId, dateSince);
        return null;
    }
}
