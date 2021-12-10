package com.example.jira.plugin.service;

import com.example.jira.plugin.entity.ProjectWithCFEntity;
import com.example.jira.plugin.entity.ProjectsListEntity;

import java.util.List;

public interface ProjectCustomFieldsService {

    List<ProjectsListEntity> getProjectsList();

    List <ProjectWithCFEntity> getListProjectsWithCustomFields1(List<String> projectKeyList);

}
