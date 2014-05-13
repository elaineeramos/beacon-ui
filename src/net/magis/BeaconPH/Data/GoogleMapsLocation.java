package net.magis.BeaconPH.Data;


import com.google.android.gms.maps.model.LatLng;

import android.os.Parcel;
import android.os.Parcelable;

public class GoogleMapsLocation extends Location implements Parcelable
{
	private LatLng coordinates;
	
	public GoogleMapsLocation(int id, int type, String name, String addr, double lat, double lon)
	{
		super(id, type, name, addr, lat, lon);
	}
	
	public LatLng getCoordinates() {
		coordinates = new LatLng(lat + 0.094, lon);
		return coordinates;
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
    public static final GoogleMapsLocation.Creator<GoogleMapsLocation> CREATOR = new Parcelable.Creator<GoogleMapsLocation>() {
        public GoogleMapsLocation createFromParcel(Parcel in) {
            return new GoogleMapsLocation(in);
        }

        public GoogleMapsLocation[] newArray(int size) {
            return new GoogleMapsLocation[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private GoogleMapsLocation(Parcel in) {
    	super(in.readInt(), in.readInt(), in.readString(), in.readString(), in.readDouble(), in.readDouble());
    }
}

