package logic;

public class Course {
	String name;
	int section;
	public Course(String name, int section) {
		this.name = name;
		this.section = section;
	}
	public String toString()
	{
		//Implementing a print function for testing
		return name+"-"+Integer.toString(section);
	}
}
