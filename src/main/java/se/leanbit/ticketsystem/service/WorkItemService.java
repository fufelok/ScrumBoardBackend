package se.leanbit.ticketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.leanbit.ticketsystem.model.WorkItem;
import se.leanbit.ticketsystem.repository.WorkItemRepository;
import se.leanbit.ticketsystem.service.interfaces.WorkItemServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class WorkItemService implements WorkItemServiceInterface
{
	@Autowired
	private WorkItemRepository workItemRepository;

	public WorkItem addWorkItem(final WorkItem workItem)
	{
		return workItemRepository.save(workItem);
	}

	public WorkItem getWorkItem(final String name)
	{
		return workItemRepository.getWorkItem(name);
	}

	public WorkItem updateWorkItem(final WorkItem workItem)
	{
		return workItemRepository.save(workItem);
	}

	public void removeWorkItem(final WorkItem workItem)
	{
		workItemRepository.removeWorkItem(workItem.getName());
	}

	public List<WorkItem> getAllWorkItems()
	{
		return (List<WorkItem>) workItemRepository.findAll();
	}

	public List<WorkItem> getWorkItemsWithIssue()
	{
		List<WorkItem> allWorkItems = getAllWorkItems();
		List<WorkItem> workItemsWithIssue = new ArrayList<>();

		for (WorkItem currentWorkItem : allWorkItems)
		{
			if (currentWorkItem.hasIssue())
			{
				workItemsWithIssue.add(currentWorkItem);
			}
		}
		return workItemsWithIssue;
	}

	public List<WorkItem> getAllWorkItemsWithStatus(final String status)
	{
		List<WorkItem> allWorkItems = getAllWorkItems();
		List<WorkItem> workItemsWithStatus = new ArrayList<>();

		for (WorkItem currentWorkItem : allWorkItems)
		{
			if (currentWorkItem.getStatus().equals(status))
			{
				workItemsWithStatus.add(currentWorkItem);
			}
		}
		return workItemsWithStatus;
	}

	public List<WorkItem> getWorkItemWithDescriptionLike(final String description)
	{
		return workItemRepository.getWorkItemWithDescriptionLike(description);
	}

	public WorkItem changeWorkItemStatus(WorkItem workItem, final String status)
	{
		workItem.setStatus(status);
		return updateWorkItem(workItem);
	}

}
