package net.magis.BeaconPH.Data;


public class ReportPersonRequest extends Request
{
	private Person personEntry;
	
	public ReportPersonRequest(Person personReport)
	{
		this.type = Defs.REQUEST_TYPE_REPORT_PERSON;
		personEntry = personReport;
		
		return;
	}
	
	public Person getPersonEntry()
	{
		return personEntry;
	}
}
