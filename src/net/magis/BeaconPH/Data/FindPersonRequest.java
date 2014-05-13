package net.magis.BeaconPH.Data;


public class FindPersonRequest extends Request
{
	private String lastName;
	private String givenName;
	
	public FindPersonRequest(String lastName, String givenName)
	{
		this.type = Defs.REQUEST_TYPE_FIND_PERSON;
		this.lastName = lastName;
		this.givenName = givenName;
		
		return;
	}
	
	public String getQueryLastName()
	{
		return lastName;
	}
	
	public String getQueryGivenName()
	{
		return givenName;
	}
	
}
