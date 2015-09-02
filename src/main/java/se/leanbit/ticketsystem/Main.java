package se.leanbit.ticketsystem;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.leanbit.ticketsystem.model.Issue;
import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;
import se.leanbit.ticketsystem.repository.IssueRepository;
import se.leanbit.ticketsystem.repository.TeamRepository;
import se.leanbit.ticketsystem.repository.UserRepository;
import se.leanbit.ticketsystem.repository.WorkItemRepository;
import se.leanbit.ticketsystem.service.TeamService;
import se.leanbit.ticketsystem.service.UserService;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext())
        {
            context.scan("se.leanbit.ticketsystem.config");
            context.refresh();

            UserService us = context.getBean(UserService.class);
            UserRepository ur = context.getBean(UserRepository.class);
            TeamRepository tr = context.getBean(TeamRepository.class);
            WorkItemRepository wr = context.getBean(WorkItemRepository.class);

            //IssueRepository ir = context.getBean(IssueRepository.class);

            TeamService teamService = context.getBean(TeamService.class);

            Team team = new Team("Leanbit");
            Team team2 = new Team("Leanbit2");

            tr.save(team);
            tr.save(team2);

            User user1 = new User("920406", "Kira", "Erik", "Welander", "Elfen", team);
            User user2 = new User("920406222", "Kira2", "Erik2", "Welander", "Elfen2", team);

            user1 = ur.save(user1);
            System.out.println(""+user1.getId());

            WorkItem wi = new WorkItem("Fixdisshit", "BLAH", "DO_IT", 9001);
            WorkItem wi2 = new WorkItem("Fixdisshit22", "BLAH222", "DO_IT222", 9001222);

            Issue issue = new Issue("Issue", "FIXA", 90001);
            Issue issue2 = new Issue("Issue2", "FIXA2", 90003);

            wi.setIssue(issue);
            wi2.setIssue(issue2);

            wr.save(wi);
            wr.save(wi2);

            List<WorkItem> workItems = wr.getWorkItemWithDescriptionLike("BLAH");
/*
            team.addTeamMember(user1);
            team.addTeamMember(user2);

            tr.save(team);
            tr.save(team2);

            User user3 = new User(user1.getId(), user1.getUserID(), user1.getUserName(), user1.getFirstName(), user1.getLastName(), user1.getPassword(), user1.getTeam());

            user3.setFirstName("Viktor");
            us.updateUser(user3);

            //System.out.println(us.getUserWithUsername("Kira"));
            //System.out.println(us.getUsersByTeamName("leanbit"));
            //System.out.println(us.getUserWithFirstname("Viktor"));
            //System.out.println(us.getUsersWithLastname("Welander"));

            //System.out.println(tr.getTeam("leanbit"));
            //System.out.println(tr.getUsersFromTeam("leanbit"));

            //teamService.removeTeam("leanbit");

            /*
            for(Team loopTeam: tr.findAll())
            {
                System.out.println(loopTeam);
            }
*/
            /*

            WorkItem wi = new WorkItem("Fixdisshit", "BLAH", "DO_IT", 9001);
            WorkItem wi2 = new WorkItem("Fixdisshit22", "BLAH222", "DO_IT222", 9001222);

            Issue issue = new Issue("Issue", "FIXA", 90001);
            Issue issue2 = new Issue("Issue2", "FIXA2", 90003);

            wi.setIssue(issue);
            wi2.setIssue(issue2);

            wr.save(wi);
            wr.save(wi2);

            team.addWorkItem(wi);
            team.addWorkItem(wi2);

            tr.save(team);

            //ir.save(issue);
            //ir.save(issue2);

            //for(Issue issueLoop: ir.findAll())
           // {
              //  System.out.println(issueLoop);
           // }

            wi.setIssue(issue);
            wi2.setIssue(issue2);

            for(WorkItem workItem: wr.findAll())
            {
                System.out.println(workItem);
            }

            user1.addWorkItem(wi);
            user1.addWorkItem(wi2);

            ur.save(user1);
            ur.save(user2);

            for(User user: ur.findAll())
            {
            //    System.out.println(user);
            }
            */
        }

    }
}
