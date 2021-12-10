package com.example.jira.plugin.controller;


import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.example.jira.plugin.mapper.Mapper;
import com.example.jira.plugin.service.ProjectCustomFieldsService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AnonymousAllowed
@Path("project-cf")
public class RestController {
    private final ProjectCustomFieldsService service;
    private final Mapper mapper;

    public RestController(ProjectCustomFieldsService service, Mapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GET
    @Path("/get-list-projects")
    @Produces("application/json")
    public Response getProjectsList() {
        return Response.ok(mapper.toJSON(service.getProjectsList())).build();
    }

    @POST
    @Path("/get-list-projects-with-cf")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getByList(@RequestBody final Map<String, String> params) {
        List<String> collect = params.entrySet().stream().map(t -> t.getKey()).collect(Collectors.toList());
        return Response.ok(mapper.toJSON(
                service.getListProjectsWithCustomFields1(collect))).build();
    }
}