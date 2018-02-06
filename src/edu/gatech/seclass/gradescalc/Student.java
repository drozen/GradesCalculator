package edu.gatech.seclass.gradescalc;


//Develop class Student with the following attributes:

// not sure if should add static?
public class Student {
	
	public Student(String name, String gtid, GradesDB db) {
		setName(name);
		setGtid(gtid);		
	}

	// Constructor for no values given

	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	
/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}


	//	1. name
	private String name;
	
/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
				return this.toString().hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Student other = (Student) obj;

		return this.toString().equals(other.toString());
	}

	//	2. GT ID
	private String gtid;

//	3. email
	private String email;
	
//	4. attendance in percentage
	private int attendance;

	
	public String getName() {
		return name;
	}

	public  void setName(String name) {
		this.name = name;
	}

	public  String getGtid() {
		return gtid;
	}

	public  void setGtid(String gtid) {
		this.gtid = gtid;
	}

	public  String getEmail() {
		return email;
	}

	public  void setEmail(String email) {
		this.email = email;
	}

	public  int getAttendance() {
		return attendance;
	}

	public  void setAttendance(int attendance2) {
		this.attendance = attendance2;
	}
	
	// METHODS TO ADD:
	
	private String team;
	
	public String getTeam(){
		return team;
	}
	
	public int getAverageAssignmentsGrade() {
		GradesDB db = new GradesDB();
		return (int) db.getAverageAssignmentsGrade(this);
	}
	
	public int getAverageProjectsGrade() {
		GradesDB db = new GradesDB();
		return (int) db.getAverageProjectsGrade(this);
	}
	
	public int getOverallGrade() {
		GradesDB db = new GradesDB();
		return (int) db.getOverallGrade(this);
	}

		
} // end class Student
