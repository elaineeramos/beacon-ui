package net.magis.BeaconPH.Data;

import java.util.ArrayList;
import java.util.List;

import net.magis.BeaconPH.Controller.Util;

public class LocationResponse extends Response
{
	private List<Location> locationList = null;
	public LocationResponse() 
	{
		locationList = new ArrayList<Location>();
		this.type = Defs.RESPONSE_TYPE_LOCATION;
		return;
	}
	
	public void addLocation(Location loc)
	{
		if (locationList == null)
		{
			Util.log(this.getClass().getSimpleName(), "Error: LocationList is null!");
			return;
		}
		
		locationList.add(loc);
		
		return;
	}
	
	public List<Location> getLocationList()
	{
		return locationList;
	}
}
