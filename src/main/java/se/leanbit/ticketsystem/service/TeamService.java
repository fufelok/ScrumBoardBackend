package se.leanbit.ticketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.repository.TeamRepository;
import se.leanbit.ticketsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.repository.UserRepository;

import java.util.List;

public class TeamService
{
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    public Team addTeam(final Team team)
    {
        return teamRepository.save(team);
    }

    public Team getTeam(final String teamName)
    {
        return teamRepository.getTeam(teamName);
    }

    public Team updateTeam(final Team team)
    {
        if(null != team && null != getTeam(team.getTeamName()))
        {
            return teamRepository.save(team);
        }
        return null;
    }

    public void removeTeam(final String teamName)
    {
        teamRepository.removeTeam(teamName);
    }

    public List<Team> getAllTeams()
    {
        return (List<Team>)teamRepository.findAll();
    }


}
