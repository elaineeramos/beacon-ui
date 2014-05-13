package net.magis.BeaconPH.UI.Extra;

import java.util.ArrayList;

import net.magis.BeaconPH.Data.GoogleMapsLocation;
import net.magis.BeaconPH.Data.GoogleMapsPerson;

import com.example.beacon.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
 
public class ListViewer extends Activity {
	private Boolean isLocation, isArray = false;
	private ListView listView = null;
	private ArrayList<GoogleMapsLocation> items = new ArrayList<GoogleMapsLocation>();
	private ArrayList<GoogleMapsLocation> locationList = new ArrayList<GoogleMapsLocation>();
	private ArrayList<GoogleMapsPerson> personList = new ArrayList<GoogleMapsPerson>();
	private MyPersonsAdapter pAdapter;
	private MyLocationsAdapter lAdapter;
	private Globals g = Globals.getInstance();
	
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.listviewer);
 
        listView = (ListView) findViewById(R.id.listView);
        Intent i = getIntent();
        locationList = i.getParcelableArrayListExtra(g.getLocations_Array_Key());
        Log.d("locationList", locationList + "");
        personList = i.getParcelableArrayListExtra(g.getPersons_Array_Key());

        Log.d("personList", personList + "");
        isLocation = i.getBooleanExtra(g.getIsLocation(), false);
        isArray = i.getBooleanExtra(g.getIsArray(), false);
        if(isLocation) {
            // 1. pass context and data to the custom adapter
            lAdapter = new MyLocationsAdapter(this, locationList);
            listView.setAdapter(lAdapter);
        	if(isArray) {
        		
        	} else {
        		
        	}
        } else {
            // 1. pass context and data to the custom adapter
        	pAdapter = new MyPersonsAdapter(this, personList);
            listView.setAdapter(pAdapter);
	    	if(isArray) {
	    		
	    	} else {
	    		
	    	}
        }

        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
		      	Intent intent = new Intent(ListViewer.this, MapView.class);
		        if(isLocation) {
		        	GoogleMapsLocation temp = (GoogleMapsLocation) listView.getItemAtPosition(position);
			      	intent.putExtra(g.getLocation_Object_Key(), temp);

		        } else {
		        	GoogleMapsPerson temp = (GoogleMapsPerson) listView.getItemAtPosition(position);
			      	intent.putExtra(g.getPerson_Object_Key(), temp);

		        }
		      	intent.putExtra(g.getIsLocation(), isLocation);
		      	intent.putExtra(g.getIsArray(), isArray);

		        startActivity(intent);
			}
        });
    }
 

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.main, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
       switch (item.getItemId())
       {
          case R.id.mapView:
		      	Intent intent = new Intent(ListViewer.this, MapView.class);
		      	intent.putParcelableArrayListExtra(g.getLocations_Array_Key(), (ArrayList<? extends Parcelable>) locationList);
		      	intent.putParcelableArrayListExtra(g.getPersons_Array_Key(), (ArrayList<? extends Parcelable>) personList);
		      	intent.putExtra(g.getIsLocation(), isLocation);
		      	intent.putExtra(g.getIsArray(), true);
		        startActivity(intent);
               return true;

          default:
               return super.onOptionsItemSelected(item);
       }
    }
    
    
 
}