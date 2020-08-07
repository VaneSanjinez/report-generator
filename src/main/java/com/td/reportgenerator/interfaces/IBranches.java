package com.td.reportgenerator.interfaces;

import com.td.reportgenerator.model.Branch;

import java.util.List;

public interface IBranches {
    List<Branch> getAllBranchesFromProject(String projectId);
    
    Branch getBranchById(String projectId, String branchName);
}