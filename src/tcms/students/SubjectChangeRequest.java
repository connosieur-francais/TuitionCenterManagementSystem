package tcms.students;

public class SubjectChangeRequest {
    private int requestId;
    private int studentId;
    private String newSubject;
    private String reason;

    public SubjectChangeRequest(int requestId, int studentId, String newSubject, String reason) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.newSubject = newSubject;
        this.reason = reason;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getNewSubject() {
        return newSubject;
    }

    public String getReason() {
        return reason;
    }

    public void setNewSubject(String newSubject) {
        this.newSubject = newSubject;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
