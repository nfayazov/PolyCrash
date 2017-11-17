package logic;
import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Database {

	static int NUM_STUDENTS = 1000;

	public HashSet<Student> db;
	
	public Database() {
		makeTable();		
	}
	
	private void makeTable() {
		this.db = new HashSet<Student>();
		try {
			Scanner fsc = new Scanner(new File("src/logic/resources/firstNames.txt"));
			Scanner lsc = new Scanner(new File("src/logic/resources/lastNames.txt"));

			String first = "";
			String last = "";

			for (int i = 0; i < NUM_STUDENTS; i++) {
				first = fsc.nextLine();
				last = lsc.nextLine();

				Student student = new Student(first, last);
				db.add(student);
			}

		//for testing purposes
			
		/*Iterator<Student> it = db.iterator();
		while (it.hasNext()){
			Student st = it.next();
			System.out.println(st.first + " " + st.last + " " + st.password);
		}*/
		
		
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public HashSet<Student> getTable() {
		return db;
	}
}
