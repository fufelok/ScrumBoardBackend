package se.leanbit.ticketsystem.model.abstra;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ModelEntity
{
	@Id
	@GeneratedValue
	private Long id;
	
	protected ModelEntity(){}
	public ModelEntity(Long id)
	{
		this.id = id;
	}
	public Long getId()
	{
		return id;
	}

}
