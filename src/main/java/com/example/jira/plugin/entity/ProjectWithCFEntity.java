package com.example.jira.plugin.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Еntity for a project with a list of keys
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectWithCFEntity {
    private @NotNull
    String projectKey;
    //private List<String> fieldsList;
    private String fieldsList;
}
