package com.example.beacon;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;

public class InformQuerier extends Activity {
	Typeface face;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inform_querier);
		
		Bundle extras = getIntent().getExtras();
		final String stFirstName = extras.getString("MESSAGE");
		
		final TextView tInform = (TextView)findViewById(R.id.text_InformYou);
		final TextView tThankyou = (TextView)findViewById(R.id.text_ThankYou);
		face = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
		tInform.setText("We will inform you once " + stFirstName + " is safe.");
		tThankyou.setTypeface(face);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inform_querier, menu);
		return true;
	}

}
