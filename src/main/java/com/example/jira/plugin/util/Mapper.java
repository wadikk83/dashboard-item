package com.example.jira.plugin.util;


import com.example.jira.plugin.entity.ProjectWithCFEntity;
import com.example.jira.plugin.entity.ProjectsListEntity;
import com.example.jira.plugin.model.XmlProjectsListModel;
import com.example.jira.plugin.model.XmlRestfulTableRowModel;

public class Mapper {

    public static XmlRestfulTableRowModel toXml(ProjectWithCFEntity entity) {

        XmlRestfulTableRowModel model = new XmlRestfulTableRowModel();
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
