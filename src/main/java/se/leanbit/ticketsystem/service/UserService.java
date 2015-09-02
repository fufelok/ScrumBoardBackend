package se.leanbit.ticketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;
import se.leanbit.ticketsystem.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public User addUser(final User user)
    {
        return userRepository.save(user);
    }

    public User updateUser(final User user)
    {
        if(null != user && null != getUserWithID(user.getUserID()))
        {
            return userRepository.save(user);
        }
        return null;
    }

    public void removeUser(final String userID)
    {
        userRepository.remove(userID);
    }

    public User getUserWithID(final String userID)
    {
        return userRepository.getUser(userID);
    }

    public User getUserWithUsername(final String userName)
    {
        return userRepository.getUserWithUsername(userName);
    }

    public List<User> getUsersWithFirstName(final String firstName)
    {
        return userRepository.getUsersWithFirstName(firstName);
    }

    public List<User> getUsersWithLastName(final String lastName)
    {
        return userRepository.getUsersWithLastName(lastName);
    }

    public List<User> getUsersByTeamName(final String teamName)
    {
        return userRepository.getUsersByTeamName(teamName);
    }

    public List<WorkItem> getWorkItemsFromUser(final String userID)
    {
        return getUserWithID(userID).getAllWorkItems();
    }

    public List<User> getUsersWithWorkItem(final WorkItem workItem)
    {
        List<User> users = getAllusers();
        List<User> usersWithItem = new ArrayList<>();

        for(User user: users)
        {
            for(WorkItem currentWorkItem: user.getAllWorkItems())
            {
                if(currentWorkItem.getName().equals(workItem.getName()))
                {
                    usersWithItem.add(user);
                }
            }
        }
        return usersWithItem;
    }

    public List<User> getAllusers()
    {
        return (List<User>)userRepository.findAll();
    }
}
