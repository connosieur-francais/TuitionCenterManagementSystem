package tcms.tutors;

public class Subject {
	private int subjectID;
	private String subjectName;
	private int levelID;

	public Subject(int subjectID, String subjectName, int levelID) {
		setSubjectID(subjectID);
		setSubjectName(subjectName);
		setLevelID(levelID);
	}

	public int getSubjectID() {
		return subjectID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public int getLevelID() {
		return levelID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}

	public String toCSV() {
		return String.join(",", String.valueOf(this.subjectID), this.subjectName, String.valueOf(this.levelID));
	}
}
