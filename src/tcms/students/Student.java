package tcms.students;

public class Student {
	private int studentID;
	private int userID;
	private String email;
	private String contact;
	private String address;
	private int level;
	private String enrollment_date;
	
	public Student(int studentID, int userID, String email, String contact, String address, int level, String enrollment_date) {
		setStudentID(studentID);
		setUserID(userID);
		setEmail(email);
		setContact(contact);
		setAddress(address);
		setLevel(level);
		setEnrollmentDate(enrollment_date);
	}
	
	public Student(int studentID, int userID) {
		setStudentID(studentID);
		setUserID(userID);
		setEmail("Not set");
		setContact("Not set");
		setAddress("Not set");
		setLevel(1);
		setEnrollmentDate("Unknown");
	}
	
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getEnrollment_date() {
		return enrollment_date;
	}
	public void setEnrollmentDate(String enrollment_date) {
		this.enrollment_date = enrollment_date;
	}
	
	public String toCSV() {
		return String.join(",", String.valueOf(userID),String.valueOf(studentID) );
	}
}
