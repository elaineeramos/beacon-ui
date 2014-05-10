package com.example.beacon;

import java.util.Calendar;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

Typeface face;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		face = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
					
		String string_hour;	
		string_hour = getCurrentHour();
		
		String string_day;	
		string_day = getCurrentDay();
					
		StringBuilder string_datetime = new StringBuilder();

		string_datetime.append(string_day);
		string_datetime.append(string_hour);

		string_day = string_datetime.toString();
		
		TextView textDateView = new TextView(this);
		textDateView = (TextView)findViewById(R.id.text_DayTime);
		textDateView.setText(string_day);
		
		final Button bSearch = (Button) findViewById(R.id.button_Search);
		final Button bReport = (Button) findViewById(R.id.button_Report);
		final LinearLayout vHome = (LinearLayout) findViewById(R.id.linearlayout_home);
		final LinearLayout vInputName = (LinearLayout) findViewById(R.id.linear_inputName);
		final LinearLayout vReportName = (LinearLayout) findViewById(R.id.linear_reportName);
		final EditText etReportName = (EditText) findViewById(R.id.text_ReportFullName);
		
        bSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	vHome.setVisibility(View.GONE);
            	vInputName.setVisibility(View.VISIBLE);
            }
        });
        
        bReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	vHome.setVisibility(View.GONE);
            	vReportName.setVisibility(View.VISIBLE);
            	
            	TextView tQuestion;
            	
            	tQuestion = (TextView) findViewById(R.id.text_ReportAsk);
            	tQuestion.setTypeface(face);
            
                etReportName.setOnKeyListener(new OnKeyListener() {

                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                          // If the event is a key-down event on the "enter" button
                          if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                               (keyCode == KeyEvent.KEYCODE_ENTER))
                          {
                                // Perform action on Enter key press
                        	  etReportName.clearFocus();
                              System.out.print("EVER TESTING");
                              return true;
                          }
                          System.out.print("EVER TESTING BBB");
                          return false;
                    }
                });
            }
        });
        

	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public String getCurrentDay()
	{
		String string_local_day = "Day";
		int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		
		switch (day)
		{
			case 1:
				string_local_day = "Sunday, ";
				break;
			case 2:
				string_local_day = "Monday, ";
				break;
			case 3:
				string_local_day = "Tuesday, ";
				break;
			case 4:
				string_local_day = "Wednesday, ";
				break;
			case 5:
				string_local_day = "Thursday, ";
				break;
			case 6:
				string_local_day = "Friday, ";
				break;
			case 7:
				string_local_day = "Saturday, ";
				break;
			default:
				break;
		}
		
		return string_local_day;
	}
	
	public String getCurrentHour()
	{
		String string_local_hour = " Hour";
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		
		if (hour == 0)
		{
			string_local_hour = "12 am";
		}
		else if (hour == 12)
		{
			string_local_hour = "12 pm";
		}
		else if (hour > 12)
		{
			string_local_hour = Integer.toString(hour - 12) + " pm";
		}
		else
		{
			string_local_hour = Integer.toString(hour) + " am";
		}
		
		return string_local_hour;
	}
	
}
