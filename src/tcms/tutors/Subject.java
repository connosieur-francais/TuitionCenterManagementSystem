package tcms.tutors;

public class Subject {
	private int subjectID;
	private String subjectName;
	
	public Subject(int subjectID, String subjectName) {
		setSubjectID(subjectID);
		setSubjectName(subjectName);
	}
	
	public int getSubjectID() {
		return subjectID;
	}

	public String getSubjectName() {	
		return subjectName;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public String toCSV() {
		return String.join(",", String.valueOf(this.subjectID), this.subjectName);
	}
}
