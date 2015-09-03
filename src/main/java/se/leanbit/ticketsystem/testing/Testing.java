package se.leanbit.ticketsystem.testing;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.leanbit.ticketsystem.config.InfraStructureTestConfig;
import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.repository.TeamRepository;
import se.leanbit.ticketsystem.service.TicketSystemService;

import static org.junit.Assert.*;

@ContextConfiguration(classes={InfraStructureTestConfig.class})
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class Testing
{
    private static TicketSystemService ticketSystemService;
    @Before
    public void setUp() throws Exception
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("se.leanbit.ticketsystem.config");
        context.refresh();
        ticketSystemService = context.getBean(TicketSystemService.class);

    }

    @Test
    public void testaddTeam()
    {
        Team team = new Team("leanbit");
        Team team1 = ticketSystemService.addTeam(team);
        assertEquals(team, team1);


        //Team team2 = ticketSystemService.getTeam(team.getTeamName());
        //int i = 0;
          assertEquals(team, ticketSystemService.getTeam(team.getTeamName()));
     //   assertSame(team, ticketSystemService.getTeam(team.getTeamName()));

    }
}
