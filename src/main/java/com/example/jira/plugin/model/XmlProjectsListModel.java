package com.example.jira.plugin.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * XML model for ProjectsListEntity
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlProjectsListModel {

    @XmlElement(name = "projectKey")
    private String projectKey;

    @XmlElement(name = "projectName")
    private String projectName;

    public XmlProjectsListModel(String projectKey, String projectName) {
        this.projectKey = projectKey;
        this.projectName = projectName;
    }

    public XmlProjectsListModel() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }
}
