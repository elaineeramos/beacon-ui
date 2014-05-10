package com.example.beacon;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;

public class FoundPerson extends Activity {
Typeface face;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_found_person);
		
		Bundle extras = getIntent().getExtras();
		String stFirstName = extras.getString("MESSAGE");
		
		final TextView vReminder = (TextView)findViewById(R.id.text_StaySafeReminder);
		vReminder.setText("Please take care of " + stFirstName + "!");
		face = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
		vReminder.setTypeface(face);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.found_person, menu);
		return true;
	}

}
