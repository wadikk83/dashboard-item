package com.example.jira.plugin.controller;

import com.example.jira.plugin.model.XmlProjectsListModel;
import com.example.jira.plugin.model.XmlRestfulTableRowModel;
import com.example.jira.plugin.service.PluginService;
import com.example.jira.plugin.service.impl.PluginServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;


/**
 * Rest service for plugin
 */
@Path("/plugin-rest")
public class RestController {

    private final PluginService service;

    public RestController(PluginService service) {
        this.service = new PluginServiceImpl();
    }

    /**
     * @param request list of project keys
     * @return list of all projects with custom fields by passed keys
     */
    @POST
    @Path("/get")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getByList(@RequestBody String request) {
        List<String> projectKey = Arrays.asList(request.split(","));
        List<XmlRestfulTableRowModel> list = service.getListProjectsWithCustomFields(projectKey);
        final GenericEntity<List<XmlRestfulTableRowModel>> entity
                = new GenericEntity<List<XmlRestfulTableRowModel>>(list) {
        };
        return Response.ok(entity).build();
    }

    /**
     * left for further development
     *
     * @return list of all projects with custom fields
     */
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<XmlRestfulTableRowModel> list = service.getAll();
        final GenericEntity<List<XmlRestfulTableRowModel>> entity
                = new GenericEntity<List<XmlRestfulTableRowModel>>(list) {
        };
        return Response.ok(entity).build();
    }


    /**
     * @return list of all projects
     */
    @GET
    @Path("/projects-list")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProjectsList() {
        List<XmlProjectsListModel> projectsList = service.getProjectsList();
        final GenericEntity<List<XmlProjectsListModel>> entity
                = new GenericEntity<List<XmlProjectsListModel>>(projectsList) {
        };
        return Response.ok(entity).build();
    }
}
