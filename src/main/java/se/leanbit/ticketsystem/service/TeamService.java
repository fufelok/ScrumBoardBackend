package se.leanbit.ticketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.repository.TeamRepository;
import se.leanbit.ticketsystem.service.interfaces.TeamServiceInterface;


import java.util.List;

public class TeamService implements TeamServiceInterface
{
	@Autowired
	private TeamRepository teamRepository;

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
		return teamRepository.save(team);
	}

	public void removeTeam(final String teamName)
	{
		teamRepository.removeTeam(teamName);
	}

	public List<Team> getAllTeams()
	{
		return (List<Team>) teamRepository.findAll();
	}

}
