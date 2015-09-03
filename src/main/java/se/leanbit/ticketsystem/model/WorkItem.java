package se.leanbit.ticketsystem.model;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import se.leanbit.ticketsystem.model.abstra.ModelEntity;

import javax.persistence.*;

@Entity
public class WorkItem extends ModelEntity
{
    private String name;
    private String description;
    private String status;
    private int priority;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(unique = false)
    private Issue issue;

    protected WorkItem(){}
    public WorkItem(String name, String description, String status, int priority)
    {
        this.name = name;
        this.description = description;
        this.status = status;
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

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public void setIssue(final Issue issue)
    {
        this.issue = issue;
    }
    public Issue getIssue()
    {
        return this.issue;
    }

    public boolean hasIssue()
    {
        if(null != issue)
        {
            return true;
        }
        return false;
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
        else if (other instanceof WorkItem)
        {
            final WorkItem otherWorkItem = (WorkItem) other;
            if (this.getId() == otherWorkItem.getId()
                    && this.name.equals(otherWorkItem.name)
                    && this.description.equals(otherWorkItem.description)
                    && this.priority == otherWorkItem.priority
                    && this.issue.equals(otherWorkItem.issue))
            {
                return true;
            }
        }
        return false;
    }
}
