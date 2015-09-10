package se.leanbit.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.leanbit.service.interfaces.SystemServiceInterface;
import se.leanbit.ticketsystem.model.Issue;
import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;
import se.leanbit.ticketsystem.service.TicketSystemService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.lang.annotation.Retention;
import java.net.URI;
import java.util.List;

@Path("ticketsystem")
@Produces(
{ MediaType.APPLICATION_JSON })
@Consumes(
{ MediaType.APPLICATION_JSON })
public class SystemService implements SystemServiceInterface
{
	@Context
	public UriInfo uriInfo;

	private static TicketSystemService ticketSystemService;
	private static Gson gson = new GsonBuilder().serializeNulls().create();

	public SystemService()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("se.leanbit.ticketsystem.config");
		context.refresh();
		ticketSystemService = context.getBean(TicketSystemService.class);
	}

	@Override
	@POST
	@Path("/user")
	public Response addUser(final User user)
	{
		ticketSystemService.addUser(user);
		final URI location = uriInfo.getAbsolutePathBuilder().path(user.getUserID()).build();

		return Response.created(location).build();
	}

	@Override
	@PUT
	@Path("/user")
	public Response updateUser(final User user)
	{
		final User updateUser = ticketSystemService.updateUser(user);
		return Response.status(200).entity(gson.toJson(updateUser)).build();
	}

	@Override
	@DELETE
	@Path("/user/{userID}")
	public Response removeUser(@PathParam("userID") final String userID)
	{
		ticketSystemService.removeUser(userID);
		return Response.status(200).build();
	}

	@Override
	@GET
	@Path("/user/{userID}")
	public Response getUserWithID(@PathParam("userID") final String userID)
	{
		final User user = ticketSystemService.getUserWithID(userID);
		return Response.status(200).entity(gson.toJson(user)).build();
	}

	@Override
	@GET
	@Path("/user/username/{userName}")
	public Response getUserWithUserName(@PathParam("userName") final String userName)
	{
		final User user = ticketSystemService.getUserWithUserName(userName);
		return Response.status(200).entity(gson.toJson(user)).build();
	}

	@Override
	@GET
	@Path("/user/firstname/{firstName}")
	public Response getUsersWithFirstName(@PathParam("firstName") final String firstName)
	{
        final List<User> users = ticketSystemService.getUsersWithFirstName(firstName);
        return Response.status(200).entity(gson.toJson(users)).build();
	}

	@Override
	@GET
	@Path("/user/lastname/{lastName}")
	public Response getUsersWithLastName(@PathParam("lastname")final String lastName)
	{
        final List<User> users = ticketSystemService.getUsersWithLastName(lastName);
        return Response.status(200).entity(gson.toJson(users)).build();
	}

	@Override
	@GET
	@Path("/user/team/{teamName}")
	public Response getUsersByTeamName(@PathParam("teamName") final String teamName)
	{
        final List<User> users = ticketSystemService.getUsersByTeamName(teamName);
        return Response.status(200).entity(gson.toJson(users)).build();
	}

	@Override
	@GET
	@Path("/user/getall")
	public Response getAllUsers()
	{
		final List<User> users = ticketSystemService.getAllUsers();
		return Response.status(200).entity(users.toString()).build();
	}

	@Override
	@POST
	@Path("/issue")
	public Response addIssue(final Issue issue)
	{
		ticketSystemService.addIssue(issue);
		final URI location = uriInfo.getAbsolutePathBuilder().path(issue.getId().toString()).build();
		return Response.created(location).build();
	}

	@Override
	@GET
	@Path("/issue/{issueID}")
	public Response getIssue(@PathParam("issueID")final long id)
	{
		Issue issue = ticketSystemService.getIssue(id);
		return Response.status(200).entity(gson.toJson(issue)).build();
	}

	@Override
	@PUT
	@Path("/issue/{issueID}")
	public Response updateIssue(Issue issue, @PathParam("issueID") final Long issueID)
	{
		Issue updatedIssue = ticketSystemService.updateIssue(issue, issueID);
		return Response.status(200).entity(gson.toJson(updatedIssue)).build();
	}

	@Override
	@DELETE
	@Path("/issue/{issueID}")
	public Response removeIssue(@PathParam("issueID")final long id)
	{
		ticketSystemService.removeIssue(id);
		return Response.status(200).build();
	}

	@Override
	@POST
	@Path("/team")
	public Response addTeam(final Team team)
	{
		ticketSystemService.addTeam(team);
		final URI location = uriInfo.getAbsolutePathBuilder().path(team.getTeamName()).build();
		return Response.created(location).build();
	}

	@Override
	@GET
	@Path("/team/{teamName}")
	public Response getTeam(@PathParam("teamName") final String teamName)
	{
		Team team = ticketSystemService.getTeam(teamName);
		return Response.status(200).entity(team.toString()).build();
	}

	@Override
	@DELETE
	@Path("/team/{teamName}")
	public Response removeTeam(@PathParam("teamName")final String teamName)
	{
		ticketSystemService.removeTeam(teamName);
		return Response.status(200).build();
	}

	@Override
	@GET
	@Path("/team/getall")
	public Response getAllTeams()
	{
		List<Team> allTeams = ticketSystemService.getAllTeams();
		return Response.status(200).entity(allTeams.toString()).build();
	}

	@Override
	@GET
	@Path("/team/getallusers/{teamName}")
	public Response getAllUsersFromTeam(@PathParam("teamName") final String teamName)
	{
		List<User> teamMembers = ticketSystemService.getUsersByTeamName(teamName);
		return Response.status(200).entity(teamMembers.toString()).build();
	}

	@Override
	@POST
	@Path("/workitem")
	public Response addWorkItem(WorkItem workItem)
	{
		ticketSystemService.addWorkItem(workItem);
		final URI location = uriInfo.getAbsolutePathBuilder().path(workItem.getName()).build();
		return Response.created(location).build();
	}

	@Override
	@GET
	@Path("/workitem/{workItemName}")
	public Response getWorkItem(@PathParam("workItemName")final String workItemName)
	{
		WorkItem workItem = ticketSystemService.getWorkItem(workItemName);
		return Response.status(200).entity(gson.toJson(workItem)).build();
	}

	@Override
	@PUT
	@Path("/workitem")
	public Response updateWorkItem(WorkItem workItem)
	{	
		WorkItem updatedWorkItem = ticketSystemService.updateWorkItem(workItem);
		return Response.status(200).entity(gson.toJson(updatedWorkItem)).build();
	}

	@Override
	@DELETE
	@Path("/workitem/{workItemName}") //fix problem with equals in user class can't remove from user.workItem map
	public Response removeWorkItem(@PathParam("workItemName") final String workItemName)
	{
		WorkItem workItem = ticketSystemService.getWorkItem(workItemName);
		ticketSystemService.removeWorkItem(workItem);
		return Response.status(200).build();
	}

	@Override
	@GET
	@Path("/workitem/getall")
	public Response getAllWorkItems()
	{
		List<WorkItem> workItems = ticketSystemService.getAllWorkItems(); 
		return Response.status(200).entity(gson.toJson(workItems)).build();
	}

	@Override
	@GET
	@Path("/workitem/getallwithissue")
	public Response getWorkItemsWithIssue()
	{
		List<WorkItem> workItems = ticketSystemService.getWorkItemsWithIssue();
		return Response.status(200).entity(gson.toJson(workItems)).build();
	}

	@Override
	@GET
	@Path("/workitem/getallwithstatus/{statusName}")
	public Response getAllWorkItemsWithStatus(@PathParam("statusName") final String statusName)
	{
		List<WorkItem> workItems = ticketSystemService.getAllWorkItemsWithStatus(statusName);
		return Response.status(200).entity(gson.toJson(workItems)).build();
	}

	@Override
	@GET
	@Path("/workitem/descriptionlike/{description}")
	public Response getWorkItemWithDescriptionLike(@PathParam("description") final String description)
	{
		List<WorkItem> workItems = ticketSystemService.getWorkItemWithDescriptionLike(description);
		return Response.status(200).entity(gson.toJson(workItems)).build();
	}

	@Override
	@PUT
	@Path("/workitem/changestatus/{workItemName}/{status}")
	public Response changeWorkItemStatus(@PathParam("workItemName") final String workItemName, @PathParam("status") final String status)
	{
		WorkItem updateWorkItem = ticketSystemService.getWorkItem(workItemName);
		updateWorkItem = ticketSystemService.changeWorkItemStatus(updateWorkItem, status);
		return Response.status(200).entity(gson.toJson(updateWorkItem)).build();
	}
	
	@Override
	@GET
	@Path("user/workitem/{workItemName}")
	public Response getUsersWithWorkItem(@PathParam("workItemName")final String workItemName)
	{
		WorkItem workItem = ticketSystemService.getWorkItem(workItemName);
		List<User> users = ticketSystemService.getUsersWithWorkItem(workItem);
		return Response.status(200).entity(users.toString()).build();
	}
	
	@Override
	@GET
	@Path("/workitem/itemsfromuser/{userID}")
	public Response getWorkItemsFromUser(@PathParam("userID") final String userID)
	{
		List<WorkItem> workItems = ticketSystemService.getWorkItemsFromUser(userID);
		return Response.status(200).entity(gson.toJson(workItems)).build();
	}



	@Override
	@GET
	@Path("/workitem/itemsfromteam/{teamName}")
	public Response getWorkItemsFromTeam(@PathParam("teamName")final Team teamName)
	{
		List<WorkItem> workItems = ticketSystemService.getWorkItemsFromTeam(teamName);
		return Response.status(200).entity(gson.toJson(workItems)).build();
	}

	@Override
	@PUT
	@Path("/workitem/addtouser/{userID}")
	public Response addWorkItemToUser(WorkItem workItem, @PathParam("userID") final String userID)
	{
		User user = ticketSystemService.addWorkItemToUser(userID, workItem);
		return Response.status(200).entity(user.toString()).build();
	}
}
