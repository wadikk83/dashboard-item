package com.example.jira.plugin.service.impl;


import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.example.jira.plugin.entity.ProjectWithCFEntity;
import com.example.jira.plugin.entity.ProjectsListEntity;
import com.example.jira.plugin.service.ProjectCustomFieldsService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class ProjectCustomFieldsServiceImpl implements ProjectCustomFieldsService {

    private final CustomFieldManager customFieldManager;
    private final ProjectManager projectManager;

    @Inject
    public ProjectCustomFieldsServiceImpl(@ComponentImport CustomFieldManager customFieldManager,
                                          @ComponentImport ProjectManager projectManager) {
        this.customFieldManager = customFieldManager;
        this.projectManager = projectManager;
    }

    @Override
    public List<ProjectWithCFEntity> getListProjectsWithCustomFields1(List<String> projectKeyList) {
        return projectKeyList.stream()
                .filter(entry -> null != projectManager.getProjectByCurrentKey(entry))
                .map(entity -> new ProjectWithCFEntity(
                        entity,
                        getCustomFieldsFromProject(entity).toString()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectsListEntity> getProjectsList() {

        List<ProjectsListEntity> collect = projectManager.getProjects().stream()
                .map(project -> new ProjectsListEntity(project.getKey(), project.getName()))
                .collect(Collectors.toList());
        return collect;
    }

    private List<String> getCustomFieldsFromProject(String projectKey) {
        Project project = projectManager.getProjectByCurrentKey(projectKey);
        List<CustomField> customFieldObjectList =
                customFieldManager.getCustomFieldObjects(project.getId(), Collections.emptyList());
        return customFieldObjectList.stream()
                .map(object -> object.getName())
                .collect(Collectors.toList());
    }

}
