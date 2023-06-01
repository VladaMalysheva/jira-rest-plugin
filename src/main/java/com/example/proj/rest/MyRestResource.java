package com.example.proj.rest;

import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * A resource of message.
 */
@Path("/message")
public class MyRestResource {

    @JiraImport
    private JiraAuthenticationContext authenticationContext;

    public MyRestResource(JiraAuthenticationContext authenticationContext, SearchService searchService) {
        this.authenticationContext = authenticationContext;
        this.searchService = searchService;
    }

    @JiraImport
    private SearchService searchService;




    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage(@QueryParam("URL") String URL, @QueryParam("jql") String jql, @QueryParam("fields")String fields)
    {

        ApplicationUser user = authenticationContext.getLoggedInUser();
        SearchService.ParseResult parseResult = searchService.parseQuery(user, jql);
        return Response.ok(new MyRestResourceModel("Hello World")).build();
    }
}