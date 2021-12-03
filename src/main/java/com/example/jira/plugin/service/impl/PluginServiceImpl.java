package com.example.jira.plugin.service.impl;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.example.jira.plugin.entity.ProjectWithCFEntity;
import com.example.jira.plugin.entity.ProjectsListEntity;
import com.example.jira.plugin.model.XmlProjectsListModel;
import com.example.jira.plugin.model.XmlRestfulTableRowModel;
import com.example.jira.plugin.service.PluginService;
import com.example.jira.plugin.util.Mapper;

import java.util.*;
import java.util.stream.Collectors;


public class PluginServiceImpl implements PluginService {

    private Map<String, List<String>> projectFieldsMap = new HashMap<>();
    private CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
    private ProjectManager projectManager = ComponentAccessor.getProjectManager();

    @Override
    public List<XmlRestfulTableRowModel> getAll() {

        List<Project> projectList = projectManager.getProjectObjects();
        return getListXmlEntityFromProjects(projectList);
    }

    @Override
    public List<XmlRestfulTableRowModel> getListProjectsWithCustomFields(List<String> projectListKey) {

        List<Project> projectList = projectListKey.stream()
                .map(pk -> projectManager.getProjectByCurrentKey(pk))
                //.map(pk -> projectManager.getProjectObjByName(pk))
                .collect(Collectors.toList());

        return getListXmlEntityFromProjects(projectList);
    }

    @Override
    public List<XmlProjectsListModel> getProjectsList() {
        List<Project> projectsList = ComponentAccessor.getProjectManager()
                .getProjects();
        return getXmlProjectsListModel(projectsList);
    }

    /*
     * Get list custom field from project
     * */
    private List<String> getCustomFieldsFromProject(Project project) {
        List<CustomField> customFieldObjectList = customFieldManager.getCustomFieldObjects(project.getId(), Collections.emptyList());
        return customFieldObjectList.stream()
                .map(object -> object.getName())
                .collect(Collectors.toList());
    }

    /*
     *  Get entity ProjectWithCFEntity from project
     * */
    private ProjectWithCFEntity getEntityFromProject(Project project) {
        return new ProjectWithCFEntity(project.getKey(), getCustomFieldsFromProject(project));
    }

    /*
     * Convert to list XML
     * */
    private List<XmlRestfulTableRowModel> getListXmlEntityFromProjects(List<Project> projectList) {

        List<XmlRestfulTableRowModel> xmlRestfulTableRowModels = new ArrayList<>();
        xmlRestfulTableRowModels.clear();

        //ProjectWithCFEntity entityFromProject = getEntityFromProject(projectList.get(0));

        List<ProjectWithCFEntity> listEntity = projectList.stream()
                .map(project -> getEntityFromProject(project))
                .collect(Collectors.toList());


        listEntity.stream().map(Mapper::toXml).forEach(xmlRestfulTableRowModels::add);
        return xmlRestfulTableRowModels;
    }

    private List<XmlProjectsListModel> getXmlProjectsListModel(List<Project> projectsList) {

        List<ProjectsListEntity> listEntities = projectsList.stream()
                .map(project -> new ProjectsListEntity(project.getKey(), project.getName()))
                .collect(Collectors.toList());

        return listEntities.stream()
                .map(e -> Mapper.toXmlProjectListModel(e))
                .collect(Collectors.toList());
    }
}
