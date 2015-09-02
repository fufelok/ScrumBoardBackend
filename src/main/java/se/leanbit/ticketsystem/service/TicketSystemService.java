package se.leanbit.ticketsystem.service;

import se.leanbit.ticketsystem.exception.TicketSystemServiceExcption;
import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;

import java.util.ArrayList;
import java.util.List;

public class TicketSystemService
{
    final UserService userService = new UserService();
    final WorkItemService workItemService = new WorkItemService();
    final TeamService teamService = new TeamService();

    public TicketSystemService(){}

    // user service methods
    public User addUser(final User user)
    {
        if(null != user)
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
        return userService.getUserWithID(userID);
    }

    public User updateUser(final User user)
    {
        if(null != user)
        {
            if(null != getUserWithID(user.getUserID()))
            {
                return userService.updateUser(user);
            }
            else
            {
                throw new TicketSystemServiceExcption("Cannot update user: No user with that userID exists!");
            }
        }
        else
        {
            throw new TicketSystemServiceExcption("Cannot update a user with an empty object!");
        }
    }

    public void removeUser(final String userID)
    {
        if(!userID.isEmpty())
        {
            if(null != userService.getUserWithID(userID))
            {
                userService.removeUser(userID);
            }
            else
            {
                throw new TicketSystemServiceExcption("Cannot remove user: No existing user with ID: "+userID);
            }
        }
        else
        {
            throw new TicketSystemServiceExcption("Cannot remove user: Remove what?");
        }
    }

    public User getUserWithUsername(final String userName)
    {
        return userService.getUserWithUsername(userName);
    }

    public List<User> getUsersWithFirstname(final String firstName)
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

    // Team service methods

    public Team addTeam(final Team team)
    {
        if(null != team)
        {
            return teamService.addTeam(team);
        }
        else
        {
            throw new TicketSystemServiceExcption("Can't add Team: can't add an empty object");
        }
    }

    public Team getTeam(final String teamName)
    {
        if(!teamName.isEmpty())
        {
            return teamService.getTeam(teamName);
        }
        else
        {
            throw new TicketSystemServiceExcption("Can't find team: can't find team with that name " + teamName);
        }
    }
    public Team uppdateTeam(final Team team)
    {
        if(null != team)
        {
            if(null != teamService.getTeam(team.getTeamName()))
            {
                return teamService.updateTeam(team);
            }
            else
            {
                throw new TicketSystemServiceExcption("can't update team: can't update an non existing team");
            }
        }
        else
        {
            throw new TicketSystemServiceExcption("can't update team: can't update with an empty object");
        }
    }

    public void removeTeam(final String teamName)
    {
        if(!teamName.isEmpty())
        {
            if(null != teamService.getTeam(teamName))
            {
                List<User> teamMembers = userService.getUsersByTeamName(teamName);
                for(User user: teamMembers)
                {
                    user.setTeam(null);
                    userService.updateUser(user);
                }
                teamService.removeTeam(teamName);
            }
            else
            {
                throw new TicketSystemServiceExcption("Can't remove team: can't remove non existing team");
            }
        }
        else
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
        if(null != workItem)
        {
           return workItemService.addWorkItem(workItem);
        }
        else
        {
            throw new TicketSystemServiceExcption("Can't add workItem: can't add workItem with an empty object");
        }
    }

    public WorkItem getWorkItem(final String workItemName)
    {
        if(!workItemName.isEmpty())
        {
            return workItemService.getWorkItem(workItemName);
        }
        else
        {
            throw new TicketSystemServiceExcption("Can't get WorkItem: can't get WorkItem with non existing workItem name");
        }
    }

    public WorkItem updateWorkItem(final WorkItem workItem)
    {
        if(null != workItem)
        {
            if(null != workItemService.getWorkItem(workItem.getName()))
            {
                return workItemService.updateWorkItem(workItem);
            }
            else
            {
                throw new TicketSystemServiceExcption("Can't update workItem: can't update an non existing workItem");
            }
        }
        else
        {
            throw new TicketSystemServiceExcption("Can't update workItem: can't update workItem with empty object");
        }
    }

    public void removeWorkItem(final WorkItem workItem)
    {
        if(null != workItemService.getWorkItem(workItem.getName()))
        {
            List<User> workUsers = userService.getUsersWithWorkItem(workItem);
            for(User user: workUsers)
            {
                user.removeWorkItem(workItem);
                userService.updateUser(user);
            }
            workItemService.removeWorkItem(workItem.getName());
        }
        else
        {
            throw new TicketSystemServiceExcption("Can't remove workItem: can't remove workItem with empty object");
        }
    }

    public List<WorkItem> getWorkItemsFromTeam(final Team team)
    {
        List<User> users = getUsersByTeamName(team.getTeamName());
        List<WorkItem> workItems = new ArrayList<>();

        for(User currentUser: users)
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





}
