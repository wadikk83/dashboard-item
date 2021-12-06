package com.example.jira.plugin.service;

import com.example.jira.plugin.model.XmlProjectsListModel;
import com.example.jira.plugin.model.XmlProjectWithCFListModel;

import java.util.List;

public interface PluginService {

    List<XmlProjectWithCFListModel> getAll();

    List<XmlProjectWithCFListModel> getListProjectsWithCustomFields(List<String> projectListKey);

    List<XmlProjectsListModel> getProjectsList();

}
