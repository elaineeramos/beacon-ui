package com.example.beacon;

import java.util.ArrayList;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ReportPerson extends Activity {
protected static final String stringFirstName = "MESSAGE";
Typeface face;
EditText etReportName;
String firstName = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_person);
		
		face = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
		
		final LinearLayout vReportName = (LinearLayout) findViewById(R.id.linear_reportName);
		final LinearLayout vReportStatus = (LinearLayout) findViewById(R.id.linear_reportStatus);
		final TextView tStatus = (TextView) findViewById(R.id.text_ReportAskStatus);
		tStatus.setTypeface(face);
				
		TextView tQuestion = (TextView) findViewById(R.id.text_ReportAsk);
    	tQuestion.setTypeface(face);
    
    	etReportName = (EditText) findViewById(R.id.text_ReportFullName);
    	etReportName.setOnKeyListener(new OnKeyListener() {
    	    public boolean onKey(View v, int keyCode, KeyEvent event) {
    	       if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
    	    	   Toast.makeText(ReportPerson.this, etReportName.getText(), Toast.LENGTH_SHORT).show();
    	    	   vReportName.setVisibility(View.GONE);
    	    	   vReportStatus.setVisibility(View.VISIBLE);
    	    	   hideSoftKeyboard();
    	    	   
    	    	   //Set entered first name to next question
    	    	   
    	    	   if((boolean)(etReportName.getText().toString()).contains(" "))
    	    	   {
    	    		   firstName = etReportName.getText().toString().substring(0, etReportName.getText().toString().indexOf(" ")); 
    	    	   }
    	    	   
    	    	   tStatus.setText("What is " + firstName + "'s status?");
    	    	   return true;
    	       }
    	       hideSoftKeyboard();
    	       return false;
    	    }
    	});
		
		
    	
    	
    	
		ArrayList<String> arStatus = new ArrayList<String>();
		arStatus.add("Choose Status");
		arStatus.add("Found Alive");
		arStatus.add("Missing");
		arStatus.add("Needs Rescue");
		arStatus.add("Found Dead");
		ArrayAdapter<String> adapterStatus;
		Spinner spinnerStatus= (Spinner) findViewById(R.id.spinner_status);
		adapterStatus= new ArrayAdapter<String>(this, R.layout.spinner_item, arStatus);
		spinnerStatus.setAdapter(adapterStatus);			
		spinnerStatus.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        // your code here
		    	switch (position)
		    	{
		    		case 1:
		    			Intent intent = new Intent(ReportPerson.this, FoundPerson.class);
						intent.putExtra(stringFirstName, firstName);
		                startActivity(intent);

		    			break;
		    		case 2:
		    			Toast.makeText(ReportPerson.this, "EVER TESTING 2", Toast.LENGTH_SHORT).show();
		    			break;
		    		case 3:
		    			Toast.makeText(ReportPerson.this, "EVER TESTING 3", Toast.LENGTH_SHORT).show();
		    			break;
		    		case 4:
		    			Toast.makeText(ReportPerson.this, "EVER TESTING 4", Toast.LENGTH_SHORT).show();
		    			break;
		    		default:
		    			break;
		    	}
		    	
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report_person, menu);
		return true;
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
