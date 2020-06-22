package com.td.reportgenerator.interfaces;

import org.springframework.http.ResponseEntity;


public interface ICommits {

    ResponseEntity<?> getAllProjectCommits(String projectId);
}
