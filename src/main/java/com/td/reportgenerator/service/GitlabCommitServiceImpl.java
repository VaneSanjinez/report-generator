package com.td.reportgenerator.service;

import com.td.reportgenerator.interfaces.ICommits;
import com.td.reportgenerator.model.Commit;
import com.td.reportgenerator.proxy.GitlabDataProxy;
import com.td.reportgenerator.util.CommitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.util.ArrayList;
import java.util.List;


@Service
public class GitlabCommitServiceImpl implements ICommits{

    @Autowired
    GitlabDataProxy gitlabDataProxy;

    @Autowired
    CommitUtil commitUtil;

    public List<Commit> getAllProjectCommits(String projectId) {
        List<Commit> commits = new ArrayList<>();
        try{
            ResponseEntity<Object[]> allCommitsByProjectId = gitlabDataProxy.getAllCommitsByProjectId(projectId);
            commits = commitUtil.parseResponseBodyToCommitList(allCommitsByProjectId);
        }
        catch(HTTPException e){
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());

        }
        return commits;
    }

    public Commit getCommitByReference(String projectId, String commitRef) {
        Commit commitByProjectIdAndRef = new Commit();
        try{
            ResponseEntity<Object> commitByReferenceAndProjectId = gitlabDataProxy.getCommit(projectId,commitRef);
            commitByProjectIdAndRef = commitUtil.parseToCommitObject(commitByReferenceAndProjectId);
        }
        catch(HTTPException e){
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());
        }
        return commitByProjectIdAndRef;
    }

    public List<Commit> getCommitsSinceDate(String projectId, String sinceDate) {
        List<Commit> commitsSinceDate = new ArrayList<>();
        try{
            ResponseEntity<Object[]> commitsSince = gitlabDataProxy.getCommitsSince(projectId, String.valueOf(sinceDate));
            commitsSinceDate = commitUtil.parseResponseBodyToCommitList(commitsSince);
        }
        catch(HTTPException e){
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());

        }
        return commitsSinceDate;
    }

    public List<Commit> getCommitsUntilDate(String projectId, String untilDate) {
        List<Commit> commitsUntilDate = new ArrayList<>();
        try{
            ResponseEntity<Object[]> commitsUntil = gitlabDataProxy.getCommitsUntil(projectId,String.valueOf(untilDate));
            commitUtil.parseResponseBodyToCommitList(commitsUntil);
        }

        catch(HTTPException e){
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());

        }
        return commitsUntilDate;
    }

    public List<Commit> getCommitsSinceUntilDates(String projectId, String since, String until) {
        List<Commit> commitsSinceUntilDates = new ArrayList<>();
        try{
            ResponseEntity<Object[]> commitsSinceUntil = gitlabDataProxy.getCommitsSinceUntil(projectId, since,until);
            commitsSinceUntilDates = commitUtil.parseResponseBodyToCommitList(commitsSinceUntil);
        }
        catch(HTTPException e){
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());

        }
        return commitsSinceUntilDates;
    }

    public List<Commit> getCommitsByProjectIdAndAuthorEmail(String projectId, String authorEmail){
        List<Commit> commitListByAuthor = new ArrayList<>();
        List<Commit> projectCommits = this.getAllProjectCommits(projectId);
        for(int i = 0; i< projectCommits.size(); i++){
            if(projectCommits.get(i).authorEmail.equals(authorEmail)){
                commitListByAuthor.add(projectCommits.get(i));
            }
        }
        return commitListByAuthor;
    }
}
