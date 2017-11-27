package logic;

public class Course {
	String name;
	int section;
	//String days;
	
	public Course(String name, int section) {
		this.name = name;
		this.section = section;
		//this.days = daysofweek;
		//A string containing a single letter for each day of the week the class lands on.
		//MON = M, TUES = T, WED = W, THURS = R, FRI = F
	}
	public String toString()
	{
		//Implementing a print function for testing
		return name+"-"+Integer.toString(section);
	}
}
