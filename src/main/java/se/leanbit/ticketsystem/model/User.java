package se.leanbit.ticketsystem.model;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import se.leanbit.ticketsystem.model.abstra.ModelEntity;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
public class User extends ModelEntity
{
    @Column(name = "user_id", unique = true)
    private String userID;
    @Column(name = "user_name", unique = true)
    private String userName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    private Team team;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "User_Workitems")
    @MapKeyColumn(name = "ID")
    private Map<String, WorkItem> workItems;


    protected User(){}
    public User(
            String userID,
            String userName,
            String firstName,
            String lastName,
            String password,
            Team team
            )
    {
        this.userID = userID;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.team = team;

        this.workItems = new HashMap<>();
    }

    public User(
            Long id,
            String userID,
            String userName,
            String firstName,
            String lastName,
            String password,
            Team team
    )
    {
        super(id);
        this.userID = userID;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.team = team;

        this.workItems = new HashMap<>();
    }

    public String getUserID()
    {
        return userID;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Team getTeam()
    {
        return team;
    }

    public void setTeam(Team team)
    {
        this.team = team;
    }

    public void addWorkItem(final WorkItem workItem)
    {
        workItems.put(UUID.randomUUID().toString(), workItem);
    }

    public void removeWorkItem(final WorkItem workItem)
    {
        while( workItems.values().remove(workItem) );
    }

    public List<WorkItem> getAllWorkItems()
    {
        return (List<WorkItem>)this.workItems.values();
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
