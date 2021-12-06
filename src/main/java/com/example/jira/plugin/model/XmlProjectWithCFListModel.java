package com.example.jira.plugin.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Xml model for project with custom field entity
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlProjectWithCFListModel {

    @XmlElement(name = "project")
    private String project;

    @XmlElement(name = "fields")
    private List<String> fields;


    public XmlProjectWithCFListModel() {
    }

    public XmlProjectWithCFListModel(String project, List<String> fields) {
        this.project = project;
        this.fields = fields;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
}