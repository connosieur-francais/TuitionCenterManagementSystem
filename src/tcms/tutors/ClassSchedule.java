package tcms.tutors;

public class ClassSchedule {
    private int studentID;
    private int tutorID;
    private int classID;
    private String subjectName;
    private String day;
    private String time;
    private String location;

    public ClassSchedule(int studentID, int tutorID, int classID, String subjectName, String day, String time, String location) {
        this.studentID = studentID;
        this.tutorID = tutorID;
        this.classID = classID;
        this.subjectName = subjectName;
        this.day = day;
        this.time = time;
        this.location = location;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getTutorID() {
        return tutorID;
    }

    public int getClassID() {
        return classID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setTutorID(int tutorID) {
        this.tutorID = tutorID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return (subjectName == null || subjectName.isEmpty() ? "Unnamed Subject" : subjectName)
               + " @ " + time + " | Class ID: " + classID
               + " | Location: " + (location == null || location.isEmpty() ? "TBA" : location);
    }
}
