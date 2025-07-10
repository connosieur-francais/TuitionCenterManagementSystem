package tcms.receptionists;

public class Payment {
	private int paymentID;

	private int studentID;
	private int subjectID;
	private double amount;
	private String date;
	private int receiptID;

	public Payment(int paymentID, int studentID, int subjectID, double amount, String date, int receiptID) {
		super();
		this.paymentID = paymentID;
		this.studentID = studentID;
		this.subjectID = subjectID;
		this.amount = amount;
		this.date = date;
		this.receiptID = receiptID;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public int getStudentID() {
		return studentID;
	}

	public int getSubjectID() {
		return subjectID;
	}

	public double getAmount() {
		return amount;
	}

	public String getDate() {
		return date;
	}

	public int getReceiptID() {
		return receiptID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setReceiptID(int receiptID) {
		this.receiptID = receiptID;
	}

	public String toCSV() {
		return String.join(",", 
				String.valueOf(this.paymentID), 
				String.valueOf(this.studentID),
				String.valueOf(this.subjectID), 
				String.valueOf(this.amount),
				date,
				String.valueOf(this.receiptID));
	}
}
