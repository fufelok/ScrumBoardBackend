package se.leanbit.ticketsystem.service;

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

public class TicketSystemService
		implements UserServiceInterface, TeamServiceInterface, WorkItemServiceInterface, IssueServiceInterface
{
	final UserService userService = new UserService();
	final WorkItemService workItemService = new WorkItemService();
	final TeamService teamService = new TeamService();
	final IssueService issueService = new IssueService();

	public TicketSystemService()
	{
	}

	// user service methods
	public User addUser(final User user)
	{
		if (null != user)
		{
			return userService.addUser(user);
		} else
		{
			throw new TicketSystemServiceExcption("addUser: Cannot add an empty user!");
		}
	}

	public User getUserWithID(final String userID)
	{
		return userService.getUserWithID(userID);
	}

	public User updateUser(final User user)
	{
		if (null != user)
		{
			if (null != getUserWithID(user.getUserID()))
			{
				return userService.updateUser(user);
			} else
			{
				throw new TicketSystemServiceExcption("Cannot update user: No user with that userID exists!");
			}
		} else
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
			} else
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
		if (null != workItem)
		{
			return userService.getUsersWithWorkItem(workItem);
		} else
		{
			throw new TicketSystemServiceExcption(
					"Can't get users with workitem: can't get all users with an empty workItem object");
		}
	}

	// Team service methods

	public Team addTeam(final Team team)
	{
		if (null != team)
		{
			return teamService.addTeam(team);
		} else
		{
			throw new TicketSystemServiceExcption("Can't add Team: can't add an empty object");
		}
	}

	public Team getTeam(final String teamName)
	{
		if (!teamName.isEmpty())
		{
			return teamService.getTeam(teamName);
		} else
		{
			throw new TicketSystemServiceExcption("Can't find team: can't find team with that name " + teamName);
		}
	}

	public Team updateTeam(final Team team)
	{
		if (null != team)
		{
			if (null != teamService.getTeam(team.getTeamName()))
			{
				return teamService.updateTeam(team);
			} else
			{
				throw new TicketSystemServiceExcption("can't update team: can't update an non existing team");
			}
		} else
		{
			throw new TicketSystemServiceExcption("can't update team: can't update with an empty object");
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
			} else
			{
				throw new TicketSystemServiceExcption("Can't remove team: can't remove non existing team");
			}
		} else
		{
			throw new TicketSystemServiceExcption("Can't remove team: can't remove team with empty object");
		}
	}

	public List<Team> getAllTeams()
	{
		return teamService.getAllTeams();
	}

	// WorkItema service methods

	public WorkItem addWorkItem(final WorkItem workItem)
	{
		if (null != workItem)
		{
			return workItemService.addWorkItem(workItem);
		} else
		{
			throw new TicketSystemServiceExcption("Can't add workItem: can't add workItem with an empty object");
		}
	}

	public WorkItem getWorkItem(final String workItemName)
	{
		if (!workItemName.isEmpty())
		{
			return workItemService.getWorkItem(workItemName);
		} else
		{
			throw new TicketSystemServiceExcption(
					"Can't get WorkItem: can't get WorkItem with non existing workItem name");
		}
	}

	public WorkItem updateWorkItem(final WorkItem workItem)
	{
		if (null != workItem)
		{
			if (null != workItemService.getWorkItem(workItem.getName()))
			{
				return workItemService.updateWorkItem(workItem);
			} else
			{
				throw new TicketSystemServiceExcption("Can't update workItem: can't update an non existing workItem");
			}
		} else
		{
			throw new TicketSystemServiceExcption("Can't update workItem: can't update workItem with empty object");
		}
	}

	public void removeWorkItem(final WorkItem workItem)
	{
		if (null != workItemService.getWorkItem(workItem.getName()))
		{
			List<User> workUsers = userService.getUsersWithWorkItem(workItem);
			for (User user : workUsers)
			{
				user.removeWorkItem(workItem);
				userService.updateUser(user);
			}
			workItemService.removeWorkItem(workItem);
		} else
		{
			throw new TicketSystemServiceExcption("Can't remove workItem: can't remove workItem with empty object");
		}
	}

	public List<WorkItem> getWorkItemsFromTeam(final Team team)
	{
		List<User> users = getUsersByTeamName(team.getTeamName());
		List<WorkItem> workItems = new ArrayList<>();

		for (User currentUser : users)
		{
			workItems.addAll(currentUser.getAllWorkItems());
		}
		return workItems;
	}

	public User addWorkItemToUser(final User user, final WorkItem workItem)
	{
		user.addWorkItem(workItem);
		return userService.updateUser(user);
	}

	public List<WorkItem> getAllWorkItems()
	{
		return workItemService.getAllWorkItems();
	}

	public List<WorkItem> getWorkItemsWithIssue()
	{
		return workItemService.getWorkItemsWithIssue();
	}

	public List<WorkItem> getAllWorkItemsWithStatus(String status)
	{
		return workItemService.getAllWorkItemsWithStatus(status);
	}

	public List<WorkItem> getWorkItemWithDescriptionLike(String description)
	{
		if (null != description)
		{
			return workItemService.getWorkItemWithDescriptionLike(description);
		} else
		{
			throw new TicketSystemServiceExcption("Can't get workItem: can't get workItem with empty description");
		}
	}

	public WorkItem changeWorkItemStatus(WorkItem workItem, String status)
	{

		if (null != workItem && null != status)
		{
			return workItemService.changeWorkItemStatus(workItem, status);
		} else
		{
			throw new TicketSystemServiceExcption(
					"Can't change status: can't change status with an empty object or status String");
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
	public Issue getIssue(Long id)
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
			} else
			{
				throw new TicketSystemServiceExcption("Can't update issue: can't update an non existing issue");
			}
		} else
		{
			throw new TicketSystemServiceExcption("Can't update issue: can't update issue with empty object");
		}
	}

	@Override
	public void removeIssue(Long id)
	{
		if (null != id)
		{
			if (null != issueService.getIssue(id))
			{
				issueService.removeIssue(id);
			} else
			{
				throw new TicketSystemServiceExcption("Can't remove issue: can't remove a non existing issue");
			}
		} else
		{
			throw new TicketSystemServiceExcption("Can't remove issue: can't remove issue with no id");
		}

	}

}
