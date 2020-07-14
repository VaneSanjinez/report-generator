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
import java.util.Optional;


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

//    public ResponseEntity<Object[]> getCommitsByDates(String projectId, Optional<String> dateSince, Optional<String> dateUntil){
//        ResponseEntity<Object[]> commitsSince = gitlabDataProxy.getCommitsSince(projectId, String.valueOf(dateSince));
//        System.out.println(commitsSince);
//        return null;
//    }

    public List<Commit> getCommitsSinceDate(String projectId, String sinceDate) {
        ResponseEntity<Object[]> commitsSince = gitlabDataProxy.getCommitsSince(projectId, String.valueOf(sinceDate));
        List<Commit> commitsSinceDate = commitUtil.parseResponseBodyToCommitList(commitsSince);
        return commitsSinceDate;
    }

    public List<Commit> getCommitsUntilDate(String projectId, String untilDate) {
        ResponseEntity<Object[]> commitsUntil = gitlabDataProxy.getCommitsUntil(projectId,String.valueOf(untilDate));
        List<Commit> commitsUntilDate = commitUtil.parseResponseBodyToCommitList(commitsUntil);
        return commitsUntilDate;
//        return null;
    }

    public List<Commit> getCommitsSinceUntilDates(String projectId, String since, String until) {
        ResponseEntity<Object[]> commitsSinceUntil = gitlabDataProxy.getCommitsSinceUntil(projectId, since,until);
        return null;
    }

}
