package se.leanbit.ticketsystem.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import se.leanbit.ticketsystem.model.abstra.ModelEntity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
public class Issue extends ModelEntity
{
    private String name;
    private String description;
    private int priority;

    protected Issue(){}
    public Issue(String name, String description, int priority)
    {
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
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
        else if (other instanceof Issue)
        {
            final Issue otherIssue = (Issue) other;
            if (this.getId() == otherIssue.getId()
                    && this.getName().equals(otherIssue.getName())
                    && this.getDescription().equals(otherIssue.getDescription())
                    && this.getPriority() == otherIssue.getPriority())
            {
                return true;
            }
        }
        return false;
    }
}
