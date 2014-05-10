package com.example.listviewsample;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.beacon.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;

public class MapView extends FragmentActivity {
	private GoogleMap mMap;
	private UiSettings mUiSettings;
	
	@SuppressWarnings("unchecked")
	@Override
	   public void onCreate(Bundle savedInstanceState)
	   {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.mapview);
	      setUpMapIfNeeded();
	      
	      Intent i = getIntent();
	      GoogleMapsLocation myParcelableObject = (GoogleMapsLocation) i.getParcelableExtra("Location_parcel");
	      ArrayList<GoogleMapsLocation> items = new ArrayList<GoogleMapsLocation>();
	      items.add(myParcelableObject);
	      items.add(myParcelableObject);
	      items.add(myParcelableObject);
	      
	      DrawPolylineFromLatLng drawer = new DrawPolylineFromLatLng(this, mMap, true, true, R.drawable.ic_launcher);
	      drawer.execute(items);
	   }
	   /* Google Map Related */
	   @SuppressLint("NewApi")
		private void setUpMapIfNeeded()
	   {
	       // Do a null check to confirm that we have not already instantiated the map.
	       if (mMap == null) {
	           // Try to obtain the map from the SupportMapFragment.
	       	System.out.println("viaje call fragment manager");
	       	mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	           // Check if we were successful in obtaining the map.
	           if (mMap != null) {
	           	System.out.println("viaje map is not null");
	               setUpMap();
	           }
	       }
	   }
	   
	   private void setUpMap()
	   {   
	   	mUiSettings = mMap.getUiSettings();
	   	mUiSettings.setMyLocationButtonEnabled(false);	
			GoogleMapOptions options = new GoogleMapOptions();
			MapFragment.newInstance(options);
	   }
}
