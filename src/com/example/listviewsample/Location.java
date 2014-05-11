package com.example.listviewsample;

public class Location
{
	public static int TYPE_ANY 			= 0;
	public static int TYPE_SCHOOL 		= 1;
	public static int TYPE_CHURCH   	= 2;
	public static int TYPE_FIRE_STN 	= 3;
	public static int TYPE_POLICE_STN	= 4;
	public static int TYPE_PUBLIC_OFC	= 5;
	
	protected int id;
	protected int type;
	protected String name;
	protected String address;
	protected double lat;
	protected double lon;
	
	public Location(int id, int type, String name, String addr, double lat, double lon)
	{
		this.id = id;
		this.type = type;
		this.name = name;
		this.address = addr;
		this.lat = lat;
		this.lon = lon;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getType()
	{
		return type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAddress()
	{
		return address;
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

