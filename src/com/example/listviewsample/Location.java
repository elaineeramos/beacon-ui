package com.example.listviewsample;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeInt(id);
		arg0.writeInt(type);
		arg0.writeString(name);
		arg0.writeString(address);
		arg0.writeDouble(lat);
		arg0.writeDouble(lon);
	}
	
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Location.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Location(Parcel in) {
		id = in.readInt();
		type = in.readInt();
		name = in.readString();
		address = in.readString();
		lat = in.readDouble();
		lon = in.readDouble();
    }
}

