package net.magis.BeaconPH.UI;

import com.example.beacon.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InformQuerier extends Activity {
	Typeface face;
	EditText etContact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inform_querier);
		face = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("beaconPh");
		getActionBar().setIcon(android.R.color.transparent);
		
		Bundle extras = getIntent().getExtras();
		final String stFirstName = extras.getString("MESSAGE");
		
		final LinearLayout vContact = (LinearLayout)findViewById(R.id.linear_contactQuerier);
		final LinearLayout vMessage = (LinearLayout)findViewById(R.id.linear_message);
		
				
		etContact = (EditText) findViewById(R.id.text_EnterPhoneNumber);
		etContact.setOnKeyListener(new OnKeyListener() {
    	    public boolean onKey(View v, int keyCode, KeyEvent event) {
    	       if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) 
    	       { 	    	   
    	    	   vContact.setVisibility(View.GONE);
    	    	   vMessage.setVisibility(View.VISIBLE);
    	    	      	    	   
    	    	   hideSoftKeyboard();  	      	    	   
    	    	   return true;
    	       }
    	       return false;
    	    }
    	});
		
		final TextView tContactAsk = (TextView)findViewById(R.id.text_AskContact);
		tContactAsk.setTypeface(face);
		
		final TextView tInform = (TextView)findViewById(R.id.text_InformYou);
		final TextView tThankyou = (TextView)findViewById(R.id.text_ThankYou);
				
		tThankyou.setTypeface(face);
		
		tInform.setText("We will inform you once " + stFirstName + " is safe.");		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inform_querier, menu);
		return true;
	}
	
	public void hideSoftKeyboard() {
	    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(etContact.getWindowToken(), 0);
	}
	
	public void showSoftKeyboard(View view) {
	    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
	    view.requestFocus();
	    inputMethodManager.showSoftInput(view, 0);
	}

}
