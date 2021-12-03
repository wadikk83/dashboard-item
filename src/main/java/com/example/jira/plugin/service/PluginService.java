package com.example.jira.plugin.service;

import com.example.jira.plugin.model.XmlProjectsListModel;
import com.example.jira.plugin.model.XmlRestfulTableRowModel;

import java.util.List;

public interface PluginService {

    List<XmlRestfulTableRowModel> getAll();

    List<XmlRestfulTableRowModel> getListProjectsWithCustomFields(List<String> projectListKey);

    List<XmlProjectsListModel> getProjectsList();

}
