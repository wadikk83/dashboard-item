package com.example.jira.plugin.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Еntity for list of projects
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  ProjectsListEntity {
    private String projectKey;
    private String projectName;
}
