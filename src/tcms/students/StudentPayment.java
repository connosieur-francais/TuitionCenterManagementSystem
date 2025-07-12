package tcms.students;

public class StudentPayment {
    private int paymentId;
    private int studentId;
    private String description;
    private double amount;
    private String status; // "Pending" or "Paid"

    public StudentPayment(int paymentId, int studentId, String description, double amount, String status) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.description = description;
        this.amount = amount;
        this.status = status;
    }

    public int getPaymentId() { return paymentId; }
    public int getStudentId() { return studentId; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String toCSV() {
        return paymentId + "," + studentId + "," + description + "," + amount + "," + status;
    }
}
