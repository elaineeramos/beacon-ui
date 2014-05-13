package net.magis.BeaconPH.Data;


public class FindLocationRequest extends Request
{
	private int locType;
	private boolean isNearby;
	private boolean isOpCenter;
	
	public FindLocationRequest(int locationType, boolean isNearby, boolean isOpCenter)
	{
		this.type = Defs.REQUEST_TYPE_FIND_LOCATION;
		this.locType = locationType;
		this.isNearby = isNearby;
		this.isOpCenter = isOpCenter;
		
		return;
	}
	
	public int getLocationType()
	{
		return locType;
	}
	
	public boolean getNearby()
	{
		return isNearby;
	}
	
	public boolean getActive()
	{
		return isOpCenter;
	}

}
