package com.example.listviewsample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Needs a list of LatLng (List<LatLng> as input)
 * @author Administrator
 * @return none
 */
public class DrawPolylineFromLatLng extends AsyncTask<List<GoogleMapsLocation>, Void, List<GoogleMapsLocation>> 
implements DialogInterface.OnCancelListener {
	private Context mContext;
	private Boolean mOption1;
	private Boolean mOption2;
	private int mAsset;
	private LatLng dest = null;
	private LatLng current = null;
	private LatLng src = null;
	protected List<GoogleMapsLocation> locations = new ArrayList<GoogleMapsLocation>();
	protected List<GoogleMapsLocation> pt_loc = null;
	private ProgressDialog mDialog;
	private GoogleMap mMap;
	private int listSize = 0;
	private String locationName = "";
	
  public DrawPolylineFromLatLng(Context context, GoogleMap map, 
		  Boolean option1, Boolean option2,
		  int asset) {
	  mContext = context;
	  mMap = map;
	  mOption1 = option1;
	  mOption2 = option2; //for now if using markers
	  mAsset = asset;
  }
 
  @Override
  protected void onPreExecute() {
      super.onPreExecute();
	    mDialog = new ProgressDialog(mContext, android.R.style.Theme_Translucent);
	    mDialog.setMessage("Please wait...");
	    mDialog.setCancelable(false);
	    mDialog.show();
  }
  
@Override
protected List<GoogleMapsLocation> doInBackground(List<GoogleMapsLocation>... locationsList) {

			return locationsList[0];
}

 protected void onPostExecute(List<GoogleMapsLocation> locationsList) {
		pt_loc = locationsList;
		System.out.println(pt_loc.get(0).getCoordinates());
		 listSize = pt_loc.size() - 1;
			Log.d("JPT", listSize + "");
			if(!mOption1) {
				 mMap.clear();
			 }
			 Random rnd = new Random(); 
			 int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));  
			 for (int i = 0; i < listSize; i++) {
					src = pt_loc.get(0).getCoordinates();
					dest = pt_loc.get(listSize).getCoordinates();
					current = pt_loc.get(i).getCoordinates();
					locationName = pt_loc.get(i).getName();
					Log.d("JPT", locationName);
					Log.d("JPT", src + "");
					
					if(mOption2) {
						mMap.addMarker(new MarkerOptions() //mMap is the Map Object
						.title(locationName)
						.position(src)
						.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
						
						mMap.addMarker(new MarkerOptions() //mMap is the Map Object
						.title(locationName)
						.position(current)
						.icon(BitmapDescriptorFactory.fromResource(mAsset)));
						
						mMap.addMarker(new MarkerOptions() //mMap is the Map Object
						.title(locationName)
						.position(dest)
						.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
					} else {
						mMap.addMarker(new MarkerOptions() //mMap is the Map Object
						.title(locationName)
						.position(src)
						.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
						
						if(i != listSize) {
							mMap.addPolyline(new PolylineOptions() //mMap is the Map Object
							.add(pt_loc.get(i).getCoordinates(), pt_loc.get(i + 1).getCoordinates())
							.width(5).color(color).geodesic(true));
						}
						
						mMap.addMarker(new MarkerOptions() //mMap is the Map Object
						.title(locationName)
						.position(dest)
						.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
					}
				 }
				CameraPosition cameraPosition = new CameraPosition.Builder()
			    .target(new LatLng(src.latitude, src.longitude))      // Sets the center of the map to Current Location
			    .zoom(12)                   // Sets the zoom
			    .bearing(0)                // Sets the orientation of the camera to north
			    .tilt(0)                   // Sets the tilt of the camera to 30 degrees
			    .build();                   // Creates a CameraPosition from the builder
				mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		mDialog.dismiss();
		return;
 }

@Override
public void onCancel(DialogInterface arg0) {
	cancel(true);
	
}
}
