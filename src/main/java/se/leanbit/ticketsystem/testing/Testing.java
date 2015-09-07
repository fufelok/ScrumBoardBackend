package se.leanbit.ticketsystem.testing;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.leanbit.ticketsystem.config.testing.InfraStructureTestConfig;
import se.leanbit.ticketsystem.exception.TicketSystemServiceException;
import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;
import se.leanbit.ticketsystem.service.TicketSystemService;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@ContextConfiguration(classes={InfraStructureTestConfig.class})
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class Testing
{
    private static TicketSystemService ticketSystemService;
    private Team team;
    private static ArrayList<Team> teams;
    private User user;
    private static AnnotationConfigApplicationContext context;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception
    {
        context = new AnnotationConfigApplicationContext();
        context.scan("se.leanbit.ticketsystem.config.testing");
        context.refresh();
        ticketSystemService = context.getBean(TicketSystemService.class);

    }


    //*********** User tests **************************************

    @Test
    public void assertThatUserIsSavable(){
        user = new User("920406", "Kira", "Erik", "Welander", "Elfen", null);
        assertEquals("Added User should be returned", user, ticketSystemService.addUser(user));
    }

    @Test
    public void assertThatUserCanBeDeleted(){
        user = new User("920406", "Kira", "Erik", "Welander", "Elfen", null);
        ticketSystemService.addUser(user);
        ticketSystemService.removeUser("920406");
        assertEquals("Cannot get user: No existing user with ID: 920406", null, ticketSystemService.getUserWithID("920406"));

    }

    @Test
    public void assertThatGetUserWithId(){
        user = new User("920406", "Kira", "Erik", "Welander", "Elfen", null);
        ticketSystemService.addUser(user);
        assertEquals( "920406", ticketSystemService.getUserWithID("920406").getUserID());
    }

	@Test
	public void assertThatUserCanBeUpdated(){

        Team team = new Team("Leanbit");
        ticketSystemService.addTeam(team);

		user = new User("920406", "Kira", "Erik", "Welander", "Elfen", team);
		user = ticketSystemService.addUser(user);
        User userGet = ticketSystemService.getUserWithID("920406");

        user.setUserName("fufel");
        user.setFirstName("Vitalj");
        user.setLastName("Bajkov");

		User user2= ticketSystemService.updateUser(user);

        List<User> users = ticketSystemService.getTeam(team.getTeamName()).getTeamMembers();



		assertThat("UserName should now be changed",user,
				is(user2));
	}


    @Test
    public void assertThatUserIsFoundByFirstname(){
        user = new User("920406", "Kira", "Erik", "Welander", "Elfen", null);
        ticketSystemService.addUser(user);
        assertThat("User is found by it's firstname", "920406",
                is(ticketSystemService.getUsersWithFirstName("Erik").get(0).getUserID()));
    }

    @Test
    public void assertThatUserIsFoundByLastname(){
        user = new User("920406", "Kira", "Erik", "Welander", "Elfen", null);
        ticketSystemService.addUser(user);
        assertThat("User is found by it's firstname", "920406",
                is(ticketSystemService.getUsersWithLastName("Welander").get(0).getUserID()));
    }

    @Test
    public void assertThatUserIsFoundByUserName(){
        user = new User("920406", "Kira", "Erik", "Welander", "Elfen", null);
        ticketSystemService.addUser(user);
        assertThat("User is found by it's username", "920406", is(ticketSystemService.getUserWithUserName("Kira").getUserID()));
    }

    @Test
    public void assertThanUsersCanBeReceivedByWorkItem(){
        WorkItem workItem1 = new WorkItem("Fixdisshit", "BLAH", "DO_IT", 9001);
        WorkItem workItem2 = new WorkItem("Fixdisshit22", "BLAH222", "DO_IT222", 9001222);
        WorkItem workItem3 = new WorkItem("Fixdisshit33", "BLAH333", "DO_IT33", 1);
        ticketSystemService.addWorkItem(workItem1);
        ticketSystemService.addWorkItem(workItem2);
        ticketSystemService.addWorkItem(workItem3);
        user = new User("920406", "Kira", "Erik", "Welander", "Elfen", null);
        user.addWorkItem(workItem1);
        user.addWorkItem(workItem2);
        user.addWorkItem(workItem3);
        ticketSystemService.addUser(user);
        assertThat("the user should now have 3 workItems", user.getAllWorkItems().size(), is(ticketSystemService.getUserWithID("920406").getAllWorkItems().size()));
    }


    //************ Team tests **************************************
    @Test
    public void assertThatTeamCanBeSaved()
    {
        team = new Team("leanbit");
        Team team1 = ticketSystemService.addTeam(team);
        assertEquals(team, team1);
        assertEquals(team, ticketSystemService.getTeam(team.getTeamName()));
    }


    @Test
    public void assertThatTeamCanBeRemoved(){
        team = new Team("Leanbit_delete");
        ticketSystemService.addTeam(team);
        ticketSystemService.removeTeam("Leanbit_delete");
        exception.expect(TicketSystemServiceException.class);
        exception.expectMessage("Cannot getTeam: There is no team with the name: Leanbit_delete");
        assertEquals( null, ticketSystemService.getTeam("Leanbit_delete"));
    }

    @Test
    public void assertThatCanGetTeamWithTeamName()
    {
        team = new Team("leanbit");
        ticketSystemService.addTeam(team);

        assertThat("Team is gotten by name", team, is( ticketSystemService.getTeam("leanbit")));
    }

    //     Catch 22
/*	
	@Test
	public void assertThatTeamCanBeUpdated(){
		team = new Team("Leanbit");
		ticketSystemService.addTeam(team);
		team.setTeamName("NewLeanbit");
		Team updatedTeam = ticketSystemService.updateTeam(team);
		assertThat("Team is updated", updatedTeam, is(ticketSystemService.getTeam("NewLeanbit")));			
	}	
*/
    @Test
    public void assertThatAllTeamsCanBeReceived(){
        Team team1 = new Team("Leanbit1");
        Team team2 = new Team("Leanbit2");
        Team team3 = new Team("Leanbit3");
        ticketSystemService.addTeam(team1);
        ticketSystemService.addTeam(team2);
        ticketSystemService.addTeam(team3);
        teams = (ArrayList<Team>) ticketSystemService.getAllTeams();
        assertThat("Number of teams are matched", 3, is(teams.size()));
    }



    @AfterClass
    public static void tearDown(){
        context.close();
    }


}