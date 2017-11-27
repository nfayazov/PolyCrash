package logic;

import java.util.ArrayList;

//Simple time class to hold an hour and minutes for our courses. Uses military time.
public class Time {
	int hour;
	int minute;
	String period;
	
	public Time(int hour, int minute){
		this.hour = hour;
		this.minute = minute;
		if(hour <= 11)
		{
			this.period = "AM";
		}
		else
		{
			this.period = "PM";
		}
	}
}
