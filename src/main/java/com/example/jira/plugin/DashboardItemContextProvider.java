package com.example.jira.plugin;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.ContextProvider;
import com.google.common.collect.Maps;

import java.util.Map;

public class DashboardItemContextProvider implements ContextProvider {

    @Override
    public void init(final Map<String, String> params) throws PluginParseException {
    }

    @Override
    public Map<String, Object> getContextMap(final Map<String, Object> context) {
        final Map<String, Object> newContext = Maps.newHashMap(context);
        newContext.put("projects", ComponentAccessor.getProjectManager().getProjects());
        return newContext;
    }

}
