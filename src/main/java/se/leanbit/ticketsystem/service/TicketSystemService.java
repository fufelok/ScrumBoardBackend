package se.leanbit.ticketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.leanbit.ticketsystem.exception.TicketSystemServiceExcption;
import se.leanbit.ticketsystem.model.Issue;
import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;
import se.leanbit.ticketsystem.service.interfaces.IssueServiceInterface;
import se.leanbit.ticketsystem.service.interfaces.TeamServiceInterface;
import se.leanbit.ticketsystem.service.interfaces.UserServiceInterface;
import se.leanbit.ticketsystem.service.interfaces.WorkItemServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class TicketSystemService implements UserServiceInterface,
											TeamServiceInterface,
                                            WorkItemServiceInterface,
                                            IssueServiceInterface
{
	@Autowired
	UserService userService;
	@Autowired
	WorkItemService workItemService;
	@Autowired
	TeamService teamService;
	@Autowired
	IssueService issueService;

	public TicketSystemService(){}

	// User service methods
	public User addUser(final User user)
	{
		if (null != user)
		{
			return userService.addUser(user);
		}
        else
		{
			throw new TicketSystemServiceExcption("addUser: Cannot add an empty user!");
		}
	}

	public User getUserWithID(final String userID)
	{
        if(!userID.isEmpty())
        {
            final User user = userService.getUserWithID(userID);
            if(null != user)
            {
                return user;
            }
            else
            {
                throw new TicketSystemServiceExcption("Cannot get user: No existing user with ID: " + user.getUserID());
            }
        }
        else
        {
            throw new TicketSystemServiceExcption("Cannot get user: empty string!");
        }
	}

	public User updateUser(final User user)
	{
		if (null != user)
		{
			if (null != getUserWithID(user.getUserID()))
			{
				return userService.updateUser(user);
			}
            else
			{
                throw new TicketSystemServiceExcption("Cannot update user: No existing user with ID: " + user.getUserID());
			}
		}
        else
		{
			throw new TicketSystemServiceExcption("Cannot update a user with an empty object!");
		}
	}

	public void removeUser(final String userID)
	{
		if (!userID.isEmpty())
		{
			if (null != userService.getUserWithID(userID))
			{
				userService.removeUser(userID);
			}
            else
			{
				throw new TicketSystemServiceExcption("Cannot remove user: No existing user with ID: " + userID);
			}
		} else
		{
			throw new TicketSystemServiceExcption("Cannot remove user: Remove what?");
		}
	}

	public User getUserWithUsername(final String userName)
	{
		return userService.getUserWithUsername(userName);
	}

	public List<User> getUsersWithFirstName(final String firstName)
	{
		return userService.getUsersWithFirstName(firstName);
	}

	public List<User> getUsersWithLastName(final String lastName)
	{
		return userService.getUsersWithLastName(lastName);
	}

	public List<User> getUsersByTeamName(final String teamName)
	{
		return userService.getUsersByTeamName(teamName);
	}

	public List<User> getAllusers()
	{
		return userService.getAllusers();
	}

	public List<User> getUsersWithWorkItem(WorkItem workItem)
	{
        return userService.getUsersWithWorkItem(workItem);
	}




	// Team service methods
	public Team addTeam(final Team team)
	{
		if (null != team)
		{
			return teamService.addTeam(team);
		}
        else
		{
			throw new TicketSystemServiceExcption("Cannot add Team: Cannot add an empty object!");
		}
	}

	public Team getTeam(final String teamName)
	{
		if (!teamName.isEmpty())
		{
            Team team = teamService.getTeam(teamName);
            if(null != team)
            {
                return team;
            }
            else
            {
                throw new TicketSystemServiceExcption("Cannot getTeam: There is no team with the name \"" + teamName +"\"");
            }
		}
        else
		{
			throw new TicketSystemServiceExcption("Cannot getTeam: Cannot get an empty object!");
		}
	}

	public Team updateTeam(final Team team)
	{
		if (null != team)
		{
			if (null != teamService.getTeam(team.getTeamName()))
			{
				return teamService.updateTeam(team);
			}
            else
			{
				throw new TicketSystemServiceExcption("Cannot update Team: There is no team with the name \"" + team.getTeamName() +"\"");
			}
		} else
		{
			throw new TicketSystemServiceExcption("Cannot update Team: Cannot update with an empty object!");
		}
	}

	public void removeTeam(final String teamName)
	{
		if (!teamName.isEmpty())
		{
			if (null != teamService.getTeam(teamName))
			{
				List<User> teamMembers = userService.getUsersByTeamName(teamName);
				for (User user : teamMembers)
				{
					user.setTeam(null);
					userService.updateUser(user);
				}
				teamService.removeTeam(teamName);
			}
            else
			{
				throw new TicketSystemServiceExcption("Cannot remove team: There is no team with the name \"" + teamName +"\"");
			}
		} else
		{
			throw new TicketSystemServiceExcption("Cannot remove Team: Cannot remove with an empty object!");
		}
	}

	public List<Team> getAllTeams()
	{
		return teamService.getAllTeams();
	}




	// WorkItems service methods
	public WorkItem addWorkItem(final WorkItem workItem)
	{
		if (null != workItem)
		{
			return workItemService.addWorkItem(workItem);
		}
        else
		{
			throw new TicketSystemServiceExcption("Cannot add WorkItem: Cannot add an empty object!");
		}
	}

	public WorkItem getWorkItem(final String workItemName)
	{
		if (!workItemName.isEmpty())
		{
            WorkItem workItem = workItemService.getWorkItem(workItemName);
            if(null != workItem)
            {
                return workItem;
            }
            else
            {
                throw new TicketSystemServiceExcption("Cannot getWorkItem: There is no WorkItem with the name \"" + workItemName + "\"");
            }
		}
        else
		{
			throw new TicketSystemServiceExcption("Cannot get WorkItem: can't get WorkItem with non existing workItem name");
		}
	}

	public WorkItem updateWorkItem(final WorkItem workItem)
	{
		if (null != workItem)
		{
			if (null != workItemService.getWorkItem(workItem.getName()))
			{
				return workItemService.updateWorkItem(workItem);
			}
            else
			{
				throw new TicketSystemServiceExcption("Cannot update workItem: There is no WorkItem with the name: \"" + workItem.getName() + "\"");
			}
		} else
		{
			throw new TicketSystemServiceExcption("Cannot update workItem: Cannot update WorkItem with an empty object!");
		}
	}

	public void removeWorkItem(final WorkItem workItem)
	{
		if (null != workItemService.getWorkItem(workItem.getName()))
		{
            if(null != getWorkItem(workItem.getName()))
            {
                List<User> workUsers = userService.getUsersWithWorkItem(workItem);
                for (User user : workUsers)
                {
                    user.removeWorkItem(workItem);
                    userService.updateUser(user);
                }

                workItemService.removeWorkItem(workItem);
            }
            else
            {
                throw new TicketSystemServiceExcption("Cannot remove WorkItem: There is no WorkItem with the name \"" + workItem.getName() + "\"");
            }

		} else
		{
			throw new TicketSystemServiceExcption("Cannot remove WorkItem: Cannot remove workItem with empty object");
		}
	}

	public List<WorkItem> getWorkItemsFromTeam(final Team team)
	{
        if(null != team)
        {
            if(null != getTeam(team.getTeamName()))
            {
                List<User> users = getUsersByTeamName(team.getTeamName());
                List<WorkItem> workItems = new ArrayList<>();

                for (User currentUser : users)
                {
                    workItems.addAll(currentUser.getAllWorkItems());
                }
                return workItems;
            }
            else
            {
                throw new TicketSystemServiceExcption("Cannot getWorkItems from team \"" + team.getTeamName() + "\": There is no such team.");
            }
        }
        else
        {
            throw new TicketSystemServiceExcption("Cannot getWorkItems from team: Cannot get WorkItems from an empty Team!");
        }

	}

	public User addWorkItemToUser(final User user, final WorkItem workItem)
	{
        if(null != user)
        {
            if(null != workItem)
            {
                if(null != getUserWithID(user.getUserID()))
                {
                    if(null != getWorkItem(workItem.getName()))
                    {
                        user.addWorkItem(workItem);
                        return userService.updateUser(user);
                    }
                    else
                    {
                        throw new TicketSystemServiceExcption("Cannot addWorkItemToUser: There is no WorkItem with the name: "+workItem.getName());
                    }
                }
                else
                {
                    throw new TicketSystemServiceExcption("Cannot addWorkItemToUser: There is no User with ID: "+user.getUserID());
                }
            }
            else
            {
                throw new TicketSystemServiceExcption("Cannot addWorkItemToUser: Cannot add an empty WorkItem!");
            }
        }
        else
        {
            throw new TicketSystemServiceExcption("Cannot addWorkItemToUser: The given User object is empty!");
        }
	}

	public List<WorkItem> getAllWorkItems()
	{
		return workItemService.getAllWorkItems();
	}

	public List<WorkItem> getWorkItemsWithIssue()
	{
		return workItemService.getWorkItemsWithIssue();
	}

	public List<WorkItem> getAllWorkItemsWithStatus(final String status)
	{
		return workItemService.getAllWorkItemsWithStatus(status);
	}

	public List<WorkItem> getWorkItemWithDescriptionLike(final String description)
	{
		if (!description.isEmpty())
		{
			return workItemService.getWorkItemWithDescriptionLike(description);
		}
        else
		{
			throw new TicketSystemServiceExcption("Can't get workItem: can't get workItem with empty description");
		}
	}

	public WorkItem changeWorkItemStatus(WorkItem workItem, String status)
	{
		if (null != workItem)
		{
            if(!status.isEmpty())
            {
                if(null != getWorkItem(workItem.getName()))
                {
                    return workItemService.changeWorkItemStatus(workItem, status);
                }
                else
                {
                    throw new TicketSystemServiceExcption("Cannot changeWorkItemStatus: There is no WorkItem with the name: " + workItem.getName());
                }
            }
            else
            {
                throw new TicketSystemServiceExcption("Cannot changeWorkItemStatus: The given status object is empty!");
            }
		}
        else
		{
			throw new TicketSystemServiceExcption("Cannot changeWorkItemStatus: Empty workItem given!");
		}

	}

	public List<WorkItem> getWorkItemsFromUser(String userID)
	{
		if (null != userID)
		{
			return userService.getWorkItemsFromUser(userID);

		} else
		{
			throw new TicketSystemServiceExcption("Can't get workItems: can't get workItems with an empty string");
		}
	}

	// Issue service methods
	@Override
	public Issue addIssue(Issue issue)
	{
		if (null != issue)
		{
			return issueService.addIssue(issue);
		} else
		{
			throw new TicketSystemServiceExcption("Can't add issue: can't add issue with an empty object");
		}
	}

	@Override
	public Issue getIssue(long id)
	{
		return issueService.getIssue(id);
	}

	@Override
	public Issue updateIssue(Issue issue)
	{
		if (null != issue)
		{
			if (null != issueService.getIssue(issue.getId()))
			{
				return issueService.updateIssue(issue);
			}
            else
			{
				throw new TicketSystemServiceExcption("Cannot update Issue: Cannot update a non-existing Issue!");
			}
		} else
		{
			throw new TicketSystemServiceExcption("Cannot update Issue: Empty Issue object given!");
		}
	}

	@Override
	public void removeIssue(final long id)
	{
        if (null != issueService.getIssue(id))
        {
            issueService.removeIssue(id);
        }
        else
        {
            throw new TicketSystemServiceExcption("Cannot remove Issue: Cannot remove a non-existing Issue!");
        }
	}
}
