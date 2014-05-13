package net.magis.BeaconPH.Controller;

import net.magis.BeaconPH.Data.FindLocationRequest;
import net.magis.BeaconPH.Data.FindPersonRequest;
import net.magis.BeaconPH.Data.Person;
import net.magis.BeaconPH.Data.ReportPersonRequest;
import net.magis.BeaconPH.Data.Request;

public class RequestFactory
{
	public static Request createFindLocRequest(int locationType, 
											   boolean isNearby, 
											   boolean isOpsCenter)
	{
		return new FindLocationRequest(locationType, isNearby, isOpsCenter);
	}
	
	public static Request createFindPersonRequest(String lastName, 
												  String givenName)
	{
		return new FindPersonRequest(lastName, givenName);
	}
	
	public static Request createReportPersonRequest(String lastName,
													String givenName,
													int status,
													String lastLocation,
													double lat,
													double lon)
	{
		return new ReportPersonRequest(new Person(Person.NO_RECORD_ID, lastName, givenName, lastLocation, status, lat, lon));
	}
}
