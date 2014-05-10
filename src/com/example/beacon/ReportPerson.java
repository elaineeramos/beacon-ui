package com.example.beacon;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ReportPerson extends Activity {
Typeface face;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_person);
		
		final LinearLayout vReportName = (LinearLayout) findViewById(R.id.linear_reportName);
		final LinearLayout vReportStatus = (LinearLayout) findViewById(R.id.linear_reportStatus);
		
		TextView tQuestion = (TextView) findViewById(R.id.text_ReportAsk);
    	tQuestion.setTypeface(face);
    
    	final EditText etReportName = (EditText) findViewById(R.id.text_ReportFullName);
    	etReportName.setOnKeyListener(new OnKeyListener() {
    	    public boolean onKey(View v, int keyCode, KeyEvent event) {
    	       if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
    	    	   Toast.makeText(ReportPerson.this, etReportName.getText(), Toast.LENGTH_SHORT).show();
    	    	   vReportName.setVisibility(View.GONE);
    	    	   vReportStatus.setVisibility(View.VISIBLE);
    	    	   return true;
    	       }
    	       return false;
    	    }
    	});
		
		
		ArrayList<String> array = new ArrayList<String>();
		array.add("item0");
		ArrayAdapter<String> yourAdapter;
		Spinner yourSpinner= (Spinner) findViewById(R.id.spinner_status);
		yourAdapter= new ArrayAdapter<String>(this, R.layout.spinner_item, array);
		yourSpinner.setAdapter(yourAdapter);	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report_person, menu);
		return true;
	}

}
