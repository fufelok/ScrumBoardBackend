package se.leanbit.ticketsystem.service.interfaces;

import java.util.List;

import se.leanbit.ticketsystem.model.Team;

public interface TeamServiceInterface 
{
	public Team addTeam(final Team team);

	public Team getTeam(final String teamName);

	public Team updateTeam(final Team team);

	public void removeTeam(final String teamName);

	public List<Team> getAllTeams();
}
