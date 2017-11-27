package logic;
//This is a testing class that should be deleted once it's appended with the current Course.java.
//It contains all the new properties added to the class.

public class Course {
	String name;
	int section;
	String days;
	
	public Course(String name, int section, String daysofweek) {
		this.name = name;
		this.section = section;
		this.days = daysofweek;
		//A string containing a single letter for each day of the week the class lands on.
		//MON = M, TUES = T, WED = W, THURS = R, FRI = F
	}
	public boolean onDay(String day)
	{
		//Checks if the class is scheduled for a provided day.
		return days.contains(day);
	}
	public String toString()
	{
		//Implementing a print function for testing
		return name+"-"+Integer.toString(section)+" ("+days+")";
	}
}
