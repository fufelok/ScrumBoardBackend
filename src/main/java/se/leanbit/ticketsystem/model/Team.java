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
import java.util.*;

@Entity
public class Team extends ModelEntity
{
    @Column(name = "team_name", unique = true)
    String teamName;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Collection<User> users;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Team_Workitems")
    @MapKeyColumn(name = "ID")
    private Map<String, WorkItem> workItems;

    protected Team(){}
    public Team(final String teamName)
    {
        this.teamName = teamName;
        users = new ArrayList<>();
        workItems = new HashMap<>();
    }

    public String getTeamName()
    {
        return teamName;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public void addTeamMember(final User user)
    {
        users.add(user);
    }

    public void addWorkItem(final WorkItem workItem)
    {
        workItems.put(UUID.randomUUID().toString(), workItem);
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public final boolean equals(final Object other)
    {
        if (this == other)
        {
            return true;
        }
        else if (other instanceof Team)
        {
            final Team otherTeam = (Team) other;
            if (this.getId() == otherTeam.getId()
                    && this.getTeamName().equals(otherTeam.getTeamName())
                    && this.users.equals(otherTeam.users)
                    && this.workItems.equals(otherTeam.workItems))
            {
                return true;
            }
        }
        return false;
    }
}
