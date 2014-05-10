package com.example.listviewsample;

public class Person
{
	public static int STATUS_NEEDS_INFO = 2001;
	public static int STATUS_IS_AUTHOR = 2002;
	public static int STATUS_BELIEVED_ALIVE = 2003;
	public static int STATUS_BELIEVED_MISSING = 2004;
	public static int STATUS_BELIEVED_DEAD = 2005;
	
	protected String lastName; 
	protected String givenName;
	protected String lastLocation;
	protected int status;
	protected String details;
	protected double lat;
	protected double lon;
	
	public Person(String lastName, String givenName, String lastLoc, int status, double lat, double lon)
	{
		this.lastName = lastName;
		this.givenName = givenName;
		this.lastLocation = lastLoc;
		this.status = status;
		this.lat = lat;
		this.lon = lon;
		this.details = "";
		
		return;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getGivenName()
	{
		return givenName;
	}
	
	public String getLastLocation()
	{
		return lastLocation;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	/* Optional */
	public void setStatusDetails(String details)
	{
		this.details = details;
	}
	
	public String getStatusDetails()
	{
		return details;
	}
	
	public double getLat()
	{
		return lat;
	}
	
	public double getLon()
	{
		return lon;
	}
}
