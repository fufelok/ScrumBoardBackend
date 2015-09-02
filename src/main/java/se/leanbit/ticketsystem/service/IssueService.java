package se.leanbit.ticketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.leanbit.ticketsystem.model.Issue;
import se.leanbit.ticketsystem.repository.IssueRepository;

public class IssueService
{
    @Autowired
    private IssueRepository issueRepository;

    public Issue addIssue(final Issue issue)
    {
        return issueRepository.save(issue);
    }

    public Issue getIssue(final Long id)
    {
        return issueRepository.findOne(id);
    }

    public Issue updateIssue(final Issue issue)
    {
        return issueRepository.save(issue);
    }

    public void removeIssue(final Long id)
    {
        issueRepository.delete(id);
    }




}
