package tcms.students;

public class Student {
	private int studentID;
	private int userID;
	private String email;
	private String contact_number;
	private String address;
	private int level;
	private String enrollment_date;
	
	public Student(int userID, String email, String contact_number, String address, int level, String enrollment_date) {
		this.userID = userID;
		this.email = email;
		this.contact_number = contact_number;
		this.address = address;
		this.level = level;
		this.enrollment_date = enrollment_date;
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
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
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
	public void setEnrollment_date(String enrollment_date) {
		this.enrollment_date = enrollment_date;
	}
}
