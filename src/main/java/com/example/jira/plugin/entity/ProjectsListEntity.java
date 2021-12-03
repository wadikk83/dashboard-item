package com.example.jira.plugin.entity;

/**
 * Еntity for list of projects
 */

public class ProjectsListEntity {
    private String projectKey;
    private String projectName;

    public ProjectsListEntity(String projectKey, String projectName) {
        this.projectKey = projectKey;
        this.projectName = projectName;
    }

    public ProjectsListEntity() {
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
