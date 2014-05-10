package com.example.listviewsample;

import java.util.ArrayList;

import com.example.beacon.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
 
public class ListViewer extends Activity {
	private ListView listView = null;
	private ArrayList<GoogleMapsLocation> items = new ArrayList<GoogleMapsLocation>();
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
		      	intent.putExtra("isLocation", true);
		      	intent.putExtra("isArray", false);
		        startActivity(intent);
			}
        });
    }
 
    //After pressing search for user and query response is parsed, place into List
    private ArrayList<GoogleMapsLocation> generateData(){
        items.add(new GoogleMapsLocation(0, 1, "BAGBAGUIN FAMILY HOSPITAL", "String2", 14.62470165,121.0053405));
        items.add(new GoogleMapsLocation(0, 1, "SAN LORENZO HOSPITAL", "String3", 14.64316617,121.032353532136));
        items.add(new GoogleMapsLocation(0, 1, "AP CRUZ COMMUNITY HOSPITAL", "String4", 14.5936311011481,120.957680893411));
 
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
		      	intent.putParcelableArrayListExtra("Location_array", (ArrayList<? extends Parcelable>) items);
		      	intent.putExtra("isArray", true);
		        startActivity(intent);
               return true;

          default:
               return super.onOptionsItemSelected(item);
       }
    }
    
    
 
}