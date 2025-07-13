package tcms.receptionists;

import java.time.LocalDate;

import tcms.utils.Constants;

public class Payment {
	private int paymentID;
	private int studentID;
	private int subjectID;
	private double amount;
	private LocalDate date;
	private int receiptID;

	public Payment(int paymentID, int studentID, int subjectID, double amount, LocalDate date, int receiptID) {
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

	public LocalDate getDate() {
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

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setReceiptID(int receiptID) {
		this.receiptID = receiptID;
	}

	public String toCSV() {
		return String.join(",", 
			String.valueOf(paymentID), 
			String.valueOf(studentID),
			String.valueOf(subjectID), 
			String.valueOf(amount),
			date.format(Constants.CSV_DATE_FORMAT),  
			String.valueOf(receiptID));
	}
	@Override
	public String toString() {
	    return "PaymentID: " + paymentID +
	           " | StudentID: " + studentID +
	           " | SubjectID: " + subjectID +
	           " | Amount: RM" + amount +
	           " | Date: " + date.format(Constants.CSV_DATE_FORMAT) +  
	           " | " + (receiptID == 0 ? "Status: Unpaid" : "Status: Paid");
	}

	

}
