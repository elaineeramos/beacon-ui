package net.magis.BeaconPH.UI.Extra;

import java.util.ArrayList;
import java.util.List;

import net.magis.BeaconPH.Data.GoogleMapsLocation;
import net.magis.BeaconPH.Data.GoogleMapsPerson;

import android.R.layout;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beacon.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.Marker;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;

public class MapView extends FragmentActivity implements OnMarkerClickListener, PanelSlideListener {
	private GoogleMap mMap;
	private UiSettings mUiSettings;
	private ArrayList<GoogleMapsLocation> locationList = new ArrayList<GoogleMapsLocation>();
	private GoogleMapsLocation locationObject = null;
	private ArrayList<GoogleMapsPerson> personList = new ArrayList<GoogleMapsPerson>();
	private GoogleMapsPerson personObject = null;
	private SlidingUpPanelLayout slider;
	private RelativeLayout expand;
	private int height;
	private TextView name, status, capacity;
	private Boolean isLocation = false;
	private Boolean isArray = false;
	private Globals g = Globals.getInstance();
	
	@SuppressWarnings("unchecked")
	@Override
	   public void onCreate(Bundle savedInstanceState)
	   {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.mapview);
	      setUpMapIfNeeded();
	      slider = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
	      expand = (RelativeLayout)findViewById(R.id.infoTab);

	      name = (TextView) findViewById(R.id.name);
	      status = (TextView) findViewById(R.id.status);
	      capacity = (TextView) findViewById(R.id.capacity);
	      
	      slider.setDragView(expand);
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			height = size.y;
        	slider.setPanelHeight(50);
        	slider.setAnchorPoint(0.5f);
        	slider.setPanelSlideListener(this);
		      mMap.setOnMarkerClickListener(this);
	      Intent i = getIntent();
	      //personObject and personList get extra
	      isLocation = i.getBooleanExtra("isLocation", false);
	      isArray = i.getBooleanExtra("isArray", false);
	      if(isLocation) {
	    	  locationObject = (GoogleMapsLocation) i.getParcelableExtra(g.getLocation_Object_Key());
		      locationList = i.getParcelableArrayListExtra(g.getLocations_Array_Key());
		      if(isArray) {
		    	  //For clicking map view option (all in list)
			      DrawPolylineFromLatLng drawer = new DrawPolylineFromLatLng(this, mMap, true, true, R.drawable.ic_launcher);
			      drawer.execute(locationList);
		      } else {
		    	  //For single clicks (via List View)
			      ArrayList<GoogleMapsLocation> items = new ArrayList<GoogleMapsLocation>();
			      items.add(locationObject);
			      items.add(locationObject);
			      items.add(locationObject);
			      
			      DrawPolylineFromLatLng drawer = new DrawPolylineFromLatLng(this, mMap, true, true, R.drawable.ic_launcher);
			      drawer.execute(items);
		      }
	      } else {
	    	  personObject = (GoogleMapsPerson) i.getParcelableExtra(g.getPerson_Object_Key());
	    	  personList = i.getParcelableArrayListExtra(g.getPersons_Array_Key());
		      if(isArray) {
		    	  //For clicking map view option (all in list)
			      DrawPolylineFromLatLngPerson drawer = new DrawPolylineFromLatLngPerson(this, mMap, true, true, R.drawable.ic_launcher);
			      drawer.execute(personList);
		      } else {
		    	  //For single clicks (via List View)
			      ArrayList<GoogleMapsPerson> items = new ArrayList<GoogleMapsPerson>();
			      items.add(personObject);
			      items.add(personObject);
			      items.add(personObject);
			      
			      DrawPolylineFromLatLngPerson drawer = new DrawPolylineFromLatLngPerson(this, mMap, true, true, R.drawable.ic_launcher);
			      drawer.execute(items);
		      }
	      }

	   }
	   /* Google Map Related */
	   @SuppressLint("NewApi")
		private void setUpMapIfNeeded()
	   {
	       // Do a null check to confirm that we have not already instantiated the map.
	       if (mMap == null) {
	           // Try to obtain the map from the SupportMapFragment.
	       	System.out.println("call fragment manager");
	       	mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	           // Check if we were successful in obtaining the map.
	           if (mMap != null) {
	           	System.out.println("map is not null");
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
	@Override
	public boolean onMarkerClick(Marker arg0) {
		int index = Integer.parseInt(arg0.getId().substring(1));
		if(isLocation) {
			if(isArray) {
				//Get index of marker
				name.setText(locationList.get(index).getName());
				capacity.setText(locationList.get(index).getType() + "");
				status.setText(locationList.get(index).getAddress());
			} else {
				name.setText(locationObject.getName());
				capacity.setText(locationObject.getType() + "");
				status.setText(locationObject.getAddress());
			}
		} else {
			if(isArray) {
//				name.setText(personObject.getGivenName());
//				capacity.setText(personObject.getLastLocation());
//				status.setText(personObject.getStatusDetails());
			} else {
				name.setText(personObject.getGivenName());
				capacity.setText(personObject.getLastLocation());
				status.setText(personObject.getStatusDetails());				
			}
		}

		if(slider.isAnchored()) {
			//slider.collapsePane();
		} else {
			slider.expandPane(0.5f);
		}
		return false;
	}

        @Override
        public void onPanelSlide(View panel, float slideOffset) {

        }

        @Override
        public void onPanelExpanded(View panel) {
    		slider.setAnchorPoint(0.5f);
        	System.out.println("Expand1");
        }

        @Override
        public void onPanelCollapsed(View panel) {
    		slider.setAnchorPoint(0.5f);
        	System.out.println("onPanelCollapsed1");

        }

		@Override
		public void onPanelAnchored(View panel) {
			slider.setAnchorPoint(0.5f);
			System.out.println("onPanelAnchored1");
			// TODO Auto-generated method stub
			
		}
}
