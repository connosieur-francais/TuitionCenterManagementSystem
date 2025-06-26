package tcms.tutors;

public class Tutor {
	private int tutorID;
	private int userID;
	private String contact;
	private String email;
	private String address;
	private int assigned_level;
	private int assigned_subjectID_1;
	private int assigned_subjectID_2;
	private int assigned_subjectID_3;

	public Tutor(int tutorID, int userID, String contact, String email, String address, int assigned_level,
			int assigned_subjectID_1, int assigned_subjectID_2, int assigned_subjectID_3) {
		setTutorID(tutorID);
		setUserID(userID);
		setContact(contact);
		setEmail(email);
		setAssigned_level(assigned_level);
		setAssigned_subjectID_1(assigned_subjectID_1);
		setAssigned_subjectID_2(assigned_subjectID_2);
		setAssigned_subjectID_3(assigned_subjectID_3);
	}
	
	public Tutor(int tutorID, int userID) {
		setTutorID(tutorID);
		setUserID(userID);
		setContact("Not set");
		setEmail("Not set");
		setAssigned_level(1);
		setAssigned_subjectID_1(0);
		setAssigned_subjectID_2(0);
		setAssigned_subjectID_3(0);
	}

	public int getTutorID() {
		return tutorID;
	}

	public void setTutorID(int tutorID) {
		this.tutorID = tutorID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAssigned_level() {
		return assigned_level;
	}

	public void setAssigned_level(int assigned_level) {
		this.assigned_level = assigned_level;
	}

	public int getAssigned_subjectID_1() {
		return assigned_subjectID_1;
	}

	public void setAssigned_subjectID_1(int assigned_subjectID_1) {
		this.assigned_subjectID_1 = assigned_subjectID_1;
	}

	public int getAssigned_subjectID_2() {
		return assigned_subjectID_2;
	}

	public void setAssigned_subjectID_2(int assigned_subjectID_2) {
		this.assigned_subjectID_2 = assigned_subjectID_2;
	}

	public int getAssigned_subjectID_3() {
		return assigned_subjectID_3;
	}

	public void setAssigned_subjectID_3(int assigned_subjectID_3) {
		this.assigned_subjectID_3 = assigned_subjectID_3;
	}

	public String toCSV() {
		return String.join(",", String.valueOf(tutorID), String.valueOf(userID), contact, email, address,
				String.valueOf(assigned_level), String.valueOf(assigned_subjectID_1),
				String.valueOf(assigned_subjectID_2), String.valueOf(assigned_subjectID_3));
	}
}
