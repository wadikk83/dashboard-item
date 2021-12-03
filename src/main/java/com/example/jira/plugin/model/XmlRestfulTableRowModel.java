package com.example.jira.plugin.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRestfulTableRowModel {

    @XmlElement(name = "project")
    private String project;

    @XmlElement(name = "fields")
    private List<String> fields;


    public XmlRestfulTableRowModel() {
    }

    public XmlRestfulTableRowModel(String project, List<String> fields) {
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