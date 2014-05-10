package com.example.beacon;

import java.util.Calendar;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	protected static final String EXTRA_MESSAGE = "main";
	private Button bSearch;
	private Button bReport;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_main);
					
		String string_hour = "Hour";	
		string_hour = getCurrentHour();
		
		String string_day = "Day";	
		string_day = getCurrentDay();		
		
		string_day = string_day + string_hour;
		
		TextView textDateView = new TextView(this);
		textDateView = (TextView)findViewById(R.id.text_DayTime);
		textDateView.setText(string_day);
		
		bSearch = (Button) findViewById(R.id.button_Search);
		bReport = (Button) findViewById(R.id.button_Report);
		
		// Fade In animation
		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator());
		fadeIn.setDuration(900);
		
		Animation fadeIn1 = new AlphaAnimation(0, 1);
		fadeIn1.setInterpolator(new DecelerateInterpolator());
		fadeIn1.setDuration(1500);
		
		Animation fadeIn2 = new AlphaAnimation(0, 1);
		fadeIn2.setInterpolator(new DecelerateInterpolator());
		fadeIn2.setDuration(2100);
		
		// Animation for Date		
		Animation aSlide = AnimationUtils.loadAnimation(this, R.anim.linear);				
		AnimationSet animationSet = new AnimationSet(false);
		animationSet.addAnimation(aSlide);
		animationSet.addAnimation(fadeIn);
		textDateView.startAnimation(animationSet);
		
		// Animation for Search
		Animation aSlide1 = AnimationUtils.loadAnimation(this, R.anim.linear_delay_1);
		AnimationSet animationSet1 = new AnimationSet(false);
		animationSet1.addAnimation(aSlide1);
		animationSet1.addAnimation(fadeIn1);		
		bSearch.startAnimation(animationSet1);
		
		//Animation for Report
		Animation aSlide2 = AnimationUtils.loadAnimation(this, R.anim.linear_delay_2);
		AnimationSet animationSet2 = new AnimationSet(false);
		animationSet2.addAnimation(aSlide2);
		animationSet2.addAnimation(fadeIn2);		
		bReport.startAnimation(animationSet2);
		
        bSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Go to SearchPerson
            	Intent intentSearch = new Intent(MainActivity.this, SearchPerson.class);
                String message = "dummy";
                intentSearch.putExtra("EXTRA_MESSAGE", message);
                startActivity(intentSearch);
            }
        });
        
        bReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Go to ReportPerson activity            	
            	Intent intent = new Intent(MainActivity.this, ReportPerson.class);
                String message = "dummy";
				intent.putExtra("EXTRA_MESSAGE", message);
                startActivity(intent);
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
	
	@Override
	public void onBackPressed(){
         super.onBackPressed();
	}
}
