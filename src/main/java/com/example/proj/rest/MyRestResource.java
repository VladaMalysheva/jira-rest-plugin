package com.example.proj.rest;


import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.opensymphony.workflow.QueryNotSupportedException;

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
    public Response getMessage(@DefaultValue("project = TEST") @QueryParam("jql") String jql, @QueryParam("fields")String fields)
    {
        ApplicationUser user = authenticationContext.getLoggedInUser();
        SearchService.ParseResult parseResult = searchService.parseQuery(user, jql);
        try {

        if(!parseResult.isValid())throw new QueryNotSupportedException();

        parseResult = searchService.parseQuery(user, jql);

        String username = user.getUsername();

        SearchResults searchResults = searchService.search(user, parseResult.getQuery(), PagerFilter.getUnlimitedFilter());

        return Response.ok(searchResults.getIssues()).build();

        } catch (SearchException e) {
            throw new RuntimeException(e);
        }

    }
}