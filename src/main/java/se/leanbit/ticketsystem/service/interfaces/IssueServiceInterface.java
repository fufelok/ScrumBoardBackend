package se.leanbit.ticketsystem.service.interfaces;

import se.leanbit.ticketsystem.model.Issue;

public interface IssueServiceInterface 
{
	public Issue addIssue(final Issue issue);

	public Issue getIssue(final Long id);

	public Issue updateIssue(final Issue issue);

	public void removeIssue(final Long id);
}
