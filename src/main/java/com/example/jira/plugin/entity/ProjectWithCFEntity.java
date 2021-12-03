package com.example.jira.plugin.entity;

import java.util.List;

/**
 * Еntity for a project with a list of keys
 */
public class ProjectWithCFEntity {
    private String projectKey;
    private List<String> fieldsList;

    public ProjectWithCFEntity(String projectKey, List<String> fieldsList) {
        this.projectKey = projectKey;
        this.fieldsList = fieldsList;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public List<String> getFieldsList() {
        return fieldsList;
    }

    public void setFieldsList(List<String> fieldsList) {
        this.fieldsList = fieldsList;
    }
}
