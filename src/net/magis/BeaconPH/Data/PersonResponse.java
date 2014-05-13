package net.magis.BeaconPH.Data;

import java.util.ArrayList;
import java.util.List;

import net.magis.BeaconPH.Controller.Util;

public class PersonResponse  extends Response
{
	private List<Person> personList = null;
	public PersonResponse() 
	{
		personList = new ArrayList<Person>();
		this.type = Defs.RESPONSE_TYPE_PERSON;
		return;
	}
	
	public void addPerson(Person person)
	{
		if (personList == null)
		{
			Util.log(this.getClass().getSimpleName(), "Error: PersonList is null!");
			return;
		}
		
		personList.add(person);
		
		return;
	}
	
	public List<Person> getPersonList()
	{
		return personList;
	}
}
