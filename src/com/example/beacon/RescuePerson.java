package com.example.beacon;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RescuePerson extends Activity {
Typeface face;
EditText etLocation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rescue_person);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("beaconPh");
		getActionBar().setIcon(android.R.color.transparent);
		
		Bundle extras = getIntent().getExtras();
		final String stFirstName = extras.getString("MESSAGE");
		
		final TextView vQuestion = (TextView)findViewById(R.id.text_RescueAsk);
		final LinearLayout vRescue = (LinearLayout)findViewById(R.id.linear_rescueName);
		
		vQuestion.setText("Where is " + stFirstName + "'s last known location?");
		face = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
		vQuestion.setTypeface(face);
		
		etLocation = (EditText) findViewById(R.id.text_RescueAddress);
		etLocation.setOnKeyListener(new OnKeyListener() {
    	    public boolean onKey(View v, int keyCode, KeyEvent event) {
    	       if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) { 	    	   
    	    	   vRescue.setVisibility(View.GONE);    	    	      	    	   
    	    	   hideSoftKeyboard();   
    	    	   
    	    	   Intent intentInform = new Intent(RescuePerson.this, InformQuerier.class);
    	    	   intentInform.putExtra("MESSAGE", stFirstName);
	               startActivity(intentInform);   
	               
    	    	   return true;
    	       }
    	       hideSoftKeyboard();
    	       return false;
    	    }
    	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rescue_person, menu);
		return true;
	}

	public void hideSoftKeyboard() {
	    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(etLocation.getWindowToken(), 0);
	}
	
	public void showSoftKeyboard(View view) {
	    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
	    view.requestFocus();
	    inputMethodManager.showSoftInput(view, 0);
	}
	
}
