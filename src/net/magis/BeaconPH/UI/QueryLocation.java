package net.magis.BeaconPH.UI;

import java.util.ArrayList;

import net.magis.BeaconPH.Data.GoogleMapsLocation;
import net.magis.BeaconPH.UI.Extra.Globals;
import net.magis.BeaconPH.UI.Extra.ListViewer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beacon.R;

public class QueryLocation extends Activity implements OnItemSelectedListener{
	EditText etReportName;
	Typeface face;
	private Spinner spinner1;
	Globals g = Globals.getInstance();
	private ArrayList<GoogleMapsLocation> items = new ArrayList<GoogleMapsLocation>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_location);
		// Show the Up button in the action bar.		
//		setupActionBar();
//		
//		getActionBar().setDisplayHomeAsUpEnabled(true);
//		getActionBar().setTitle("beaconPh");
//		getActionBar().setIcon(android.R.color.transparent);
		
		face = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
		
		TextView tSearch = (TextView)findViewById(R.id.text_AskSearch);
		tSearch.setTypeface(face);
		spinner1 = (Spinner) findViewById(R.id.LocationSpinner);
		// Spinner item selection Listener  
		addListenerOnSpinnerItemSelection();

		etReportName = (EditText) findViewById(R.id.text_SearchLocation);
    	etReportName.setOnKeyListener(new OnKeyListener() {
    	    public boolean onKey(View v, int keyCode, KeyEvent event) {
    	       if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
    	    	   hideSoftKeyboard();
    	    	   
    	    	   //Send Query to API Here and Parse returned value into ArrayList for ListView
    	    	   //Pass as parcelable
    	    	   Intent intent = new Intent(QueryLocation.this, ListViewer.class);
    	    	   intent.putParcelableArrayListExtra(g.getLocations_Array_Key(), (ArrayList<? extends Parcelable>) generateData());
    		 	   intent.putExtra(g.getIsLocation(), true);
    		 	   intent.putExtra(g.getIsArray(), false);
                   startActivity(intent);
    	    	   
    	    	   return true;
    	       }
    	       hideSoftKeyboard();
    	       return false;
    	    }
    	});
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_person, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void hideSoftKeyboard() {
	    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(etReportName.getWindowToken(), 0);
	}
	
	public void showSoftKeyboard(View view) {
	    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
	    view.requestFocus();
	    inputMethodManager.showSoftInput(view, 0);
	}
	
    // Add spinner data
    
    public void addListenerOnSpinnerItemSelection(){
     	spinner1.setOnItemSelectedListener(this);
    }

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
            long id) {
		if(pos != 0) {
	        Toast.makeText(parent.getContext(), 
	                "On Item Select : \n" + parent.getItemAtPosition(pos).toString(),
	                Toast.LENGTH_LONG).show();
	 	   //Send Query to API Here and Parse returned value into ArrayList for ListView
	 	   //Pass as parcelable
	 	   Intent intent = new Intent(QueryLocation.this, ListViewer.class);
    	   intent.putParcelableArrayListExtra(g.getLocations_Array_Key(), (ArrayList<? extends Parcelable>) generateData());
	 	   intent.putExtra(g.getIsLocation(), true);
	 	   intent.putExtra(g.getIsArray(), false);
	 	   
	        startActivity(intent);
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
    //Test
    public ArrayList<GoogleMapsLocation> generateData(){
    	
        items.add(new GoogleMapsLocation(0, 1, "BAGBAGUIN FAMILY HOSPITAL", "String2", 14.62470165,121.0053405));
        items.add(new GoogleMapsLocation(0, 1, "SAN LORENZO HOSPITAL", "String3", 14.64316617,121.032353532136));
        items.add(new GoogleMapsLocation(0, 1, "AP CRUZ COMMUNITY HOSPITAL", "String4", 14.5936311011481,120.957680893411));
 
        return items;
    }
}
