package com.example.beacon;

import com.example.listviewsample.ListViewer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class SearchPerson extends Activity {
	EditText etReportName;
	Typeface face;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_person);
		// Show the Up button in the action bar.		
		setupActionBar();
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("beaconPh");
		getActionBar().setIcon(android.R.color.transparent);
		
		face = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
		
		TextView tSearch = (TextView)findViewById(R.id.text_AskSearch);
		tSearch.setTypeface(face);
		
		etReportName = (EditText) findViewById(R.id.text_SearchFullName);
    	etReportName.setOnKeyListener(new OnKeyListener() {
    	    public boolean onKey(View v, int keyCode, KeyEvent event) {
    	       if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
    	    	   hideSoftKeyboard();
    	    	   
    	    	   Intent intent = new Intent(SearchPerson.this, ListViewer.class);
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
	
}
