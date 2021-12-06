package com.example.jira.plugin.util;


import com.example.jira.plugin.entity.ProjectWithCFEntity;
import com.example.jira.plugin.entity.ProjectsListEntity;
import com.example.jira.plugin.model.XmlProjectsListModel;
import com.example.jira.plugin.model.XmlProjectWithCFListModel;

public class Mapper {

    public static XmlProjectWithCFListModel toXml(ProjectWithCFEntity entity) {

        XmlProjectWithCFListModel model = new XmlProjectWithCFListModel();
        model.setProject(entity.getProjectKey());
        model.setFields(entity.getFieldsList());

        return model;
    }

    public static XmlProjectsListModel toXmlProjectListModel(ProjectsListEntity entity) {
        XmlProjectsListModel model = new XmlProjectsListModel();
        model.setProjectKey(entity.getProjectKey());
        model.setProjectName(entity.getProjectName());

        return model;
    }
}
