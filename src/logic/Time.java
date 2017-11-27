package logic;

//Simple time class to hold an hour and minutes for our courses. Uses military time.
public class Time {
	int hour;
	int minute;
	String period;
	
	public Time(int hour, int minute){
		this.hour = hour;
		this.minute = minute;
		getPeriod();
	}
	
	public String getPeriod()
	{
		if(this.hour <= 11)
		{
			this.period = "AM";
		}
		else
		{
			this.period = "PM";
		}
		return this.period;
	}
	
	public int getPeriodHour()
	{
		if(this.hour == 0)
		{
			return 12;
		}
		else if(this.hour <= 12)
		{
			return this.hour;
		}
		else
		{
			return this.hour-12;
		}
	}
}
