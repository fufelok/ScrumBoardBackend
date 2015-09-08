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
import java.net.URI;

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
    private static Gson gson = new GsonBuilder().create();

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
        final User gotUser = ticketSystemService.updateUser(user);
        final URI location = uriInfo.getAbsolutePathBuilder().path(user.getUserID()).build();

        return Response.created(location).build();
    }

    @Override
    public Response removeUser(String userID)
    {
        return null;
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
    public Response getUserWithUserName(String userName)
    {
        return null;
    }

    @Override
    public Response getUsersWithFirstName(String firstName)
    {
        return null;
    }

    @Override
    public Response getUsersWithLastName(String lastName)
    {
        return null;
    }

    @Override
    public Response getUsersByTeamName(String teamName)
    {
        return null;
    }

    @Override
    public Response getWorkItemsFromUser(String userID)
    {
        return null;
    }

    @Override
    public Response getUsersWithWorkItem(WorkItem workItem)
    {
        return null;
    }

    @Override
    public Response getAllUsers()
    {
        return null;
    }


    @Override
    public Response getWorkItemsFromTeam(Team team)
    {

        return null;
    }

    @Override
    public Response addWorkItemToUser(User user, WorkItem workItem)
    {
        return null;
    }

    @Override
    public Response addIssue(Issue issue)
    {
        return null;
    }

    @Override
    public Response getIssue(long id)
    {
        return null;
    }

    @Override
    public Response updateIssue(Issue issue)
    {
        return null;
    }

    @Override
    public Response removeIssue(long id)
    {
        return null;
    }

    @Override
    public Response addTeam(Team team)
    {
        return null;
    }

    @Override
    public Response getTeam(String teamName)
    {
        return null;
    }

    @Override
    public Response removeTeam(String teamName)
    {
        return null;
    }

    @Override
    public Response getAllTeams()
    {
        return null;
    }

    @Override
    public Response getAllUsersFromTeam(String teamName)
    {
        return null;
    }

    @Override
    public Response addWorkItem(WorkItem workItem)
    {
        return null;
    }

    @Override
    public Response getWorkItem(String name)
    {
        return null;
    }

    @Override
    public Response updateWorkItem(WorkItem workItem)
    {
        return null;
    }

    @Override
    public Response removeWorkItem(WorkItem workItem)
    {
        return null;
    }

    @Override
    public Response getAllWorkItems()
    {
        return null;
    }

    @Override
    public Response getWorkItemsWithIssue()
    {
        return null;
    }

    @Override
    public Response getAllWorkItemsWithStatus(String status)
    {
        return null;
    }

    @Override
    public Response getWorkItemWithDescriptionLike(String description)
    {
        return null;
    }

    @Override
    public Response changeWorkItemStatus(WorkItem workItem, String status)
    {
        return null;
    }
}
