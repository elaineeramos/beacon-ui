package com.example.listviewsample;

import java.util.ArrayList;

import com.example.beacon.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
 
public class ListViewer extends Activity {
	private ListView listView = null;
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.listviewer);
        
 
        // 1. pass context and data to the custom adapter
        MyAdapter adapter = new MyAdapter(this, generateData());
 
        // 2. Get ListView from activity_main.xml
        listView = (ListView) findViewById(R.id.listView);
 
        // 3. setListAdapter
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Location temp = (Location) listView.getItemAtPosition(position);
		      	Intent intent = new Intent(ListViewer.this, MapView.class);
		      	intent.putExtra("Location_parcel", temp);
		        startActivity(intent);
				
			}
        });
    }
 
    //After pressing search for user and query response is parsed, place into List
    private ArrayList<GoogleMapsLocation> generateData(){
        ArrayList<GoogleMapsLocation> items = new ArrayList<GoogleMapsLocation>();
        items.add(new GoogleMapsLocation(0, 1, "String1", "String2", 14.516018161679616,121.07677210654408));
        items.add(new GoogleMapsLocation(0, 1, "String2", "String3", 14.516018161679616,121.07677210654408));
        items.add(new GoogleMapsLocation(0, 1, "String3", "String4", 14.516018161679616,121.07677210654408));
 
        return items;
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
		        startActivity(intent);
               return true;

          default:
               return super.onOptionsItemSelected(item);
       }
    }
    
    
 
}