package logic;

public class Student {
	String first, last, major, password;
	int year;

	public Student(String first, String last) {
		this.first = first;
		this.last = last;
		this.password = createPassword(first, last);
		
	}
	
	public String createPassword(String first, String last) {
		String end = Integer.toString((int)last.charAt(last.length()-1));
		return (first.charAt(0) + last).toLowerCase() + end;
	}

	public String getFirstAndLastName() {
		return this.first + " " + this.last;
	}
	
	//make this private or protected
	public String getPassword() {
		return this.password;
	}

	public void viewCurrentSchedule() {

	}
}
