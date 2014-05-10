package com.example.listviewsample;

import com.google.android.gms.maps.model.LatLng;

import android.os.Parcel;
import android.os.Parcelable;

public class GoogleMapsPerson extends Person implements Parcelable
{
	private LatLng coordinates;
	
	public GoogleMapsPerson(String lastName, String givenName, String lastLoc, int status, double lat, double lon)
	{
		super(lastName, givenName, lastLoc, status, lat, lon);
	}
	
	public LatLng getCoordinates() {
		coordinates = new LatLng(lat, lon);
		return coordinates;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(lastName);
		arg0.writeString(givenName);
		arg0.writeString(lastLocation);
		arg0.writeInt(status);
		arg0.writeDouble(lat);
		arg0.writeDouble(lon);
	}
	
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final GoogleMapsLocation.Creator<GoogleMapsPerson> CREATOR = new Parcelable.Creator<GoogleMapsPerson>() {
        public GoogleMapsPerson createFromParcel(Parcel in) {
            return new GoogleMapsPerson(in);
        }

        public GoogleMapsPerson[] newArray(int size) {
            return new GoogleMapsPerson[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private GoogleMapsPerson(Parcel in) {
    	super(in.readString(), in.readString(), in.readString(), in.readInt(), in.readDouble(), in.readDouble());
    }
}

