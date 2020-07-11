package com.td.reportgenerator.interfaces;

import com.td.reportgenerator.model.Commit;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ICommits {

    List<Commit> getAllProjectCommits(String projectId);
    Commit getCommitByReference(String projectId, String commitRef);
    ResponseEntity<Object[]> getCommitsSinceDate(String projectId, String sinceDate);
    ResponseEntity<Object[]> getCommitsUntilDate(String projectId, String untilDate);
    ResponseEntity<Object[]> getCommitsSinceUntilDates(String projectId, String since, String until);
}
