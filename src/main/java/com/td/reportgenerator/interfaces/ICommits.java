package com.td.reportgenerator.interfaces;

import com.td.reportgenerator.model.Commit;

import java.util.List;


public interface ICommits {

    List<Commit> getAllProjectCommits(String projectId);

    Commit getCommitByReference(String projectId, String commitRef);

    List<Commit> getCommitsSinceDate(String projectId, String sinceDate);

    List<Commit> getCommitsUntilDate(String projectId, String untilDate);

    List<Commit> getCommitsSinceUntilDates(String projectId, String since, String until);

}
