package se.leanbit.ticketsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.leanbit.ticketsystem.repository.IssueRepository;
import se.leanbit.ticketsystem.service.TeamService;
import se.leanbit.ticketsystem.service.UserService;

@Configuration
public class ServiceConfig
{

	@Bean
	public UserService userService()
	{
		return new UserService();
	}

	@Bean
	public TeamService teamService()
	{
		return new TeamService();
	}

}
