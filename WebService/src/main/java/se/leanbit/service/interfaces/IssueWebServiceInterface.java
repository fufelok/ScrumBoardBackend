package se.leanbit.service.interfaces;

import se.leanbit.ticketsystem.model.Issue;

import javax.ws.rs.core.Response;

public interface IssueWebServiceInterface
{
    public Response addIssue(final Issue issue);

    public Response getIssue(final long id);

    public Response updateIssue(final Issue issue);

    public Response removeIssue(final long id);
}
