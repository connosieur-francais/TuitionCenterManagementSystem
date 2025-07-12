package tcms.students;

import java.io.*;
import java.util.*;

public class StudentPaymentManager {
    private List<StudentPayment> payments = new ArrayList<>();

    public void loadPayments(String filePath) {
        payments.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", -1);
                if (p.length >= 5) {
                    StudentPayment payment = new StudentPayment(
                        Integer.parseInt(p[0]),
                        Integer.parseInt(p[1]),
                        p[2],
                        Double.parseDouble(p[3]),
                        p[4]
                    );
                    payments.add(payment);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading payments: " + e.getMessage());
        }
    }

    public void savePayments(String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (StudentPayment payment : payments) {
                pw.println(payment.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Error saving payments: " + e.getMessage());
        }
    }

    public List<StudentPayment> getPaymentsByStudentId(int studentId) {
        List<StudentPayment> result = new ArrayList<>();
        for (StudentPayment p : payments) {
            if (p.getStudentId() == studentId) result.add(p);
        }
        return result;
    }

    public void markAsPaid(int paymentId) {
        for (StudentPayment p : payments) {
            if (p.getPaymentId() == paymentId) {
                p.setStatus("Paid");
                break;
            }
        }
    }
}
