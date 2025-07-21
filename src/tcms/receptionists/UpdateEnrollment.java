package tcms.receptionists;

import java.awt.EventQueue;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class UpdateEnrollment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIC;
	private JComboBox<String> cmbSubject1;
	private JComboBox<String> cmbSubject2;
	private JComboBox<String> cmbSubject3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UpdateEnrollment frame = new UpdateEnrollment();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateEnrollment() {
		setTitle("Update Student Enrollment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIC = new JLabel("IC/Passport:");
		lblIC.setBounds(30, 70, 100, 20);
		contentPane.add(lblIC);

		txtIC = new JTextField();
		txtIC.setBounds(130, 68, 250, 25);
		contentPane.add(txtIC);
		txtIC.setColumns(10);

		JLabel lblSubject1 = new JLabel("Subject 1:");
		lblSubject1.setBounds(30, 102, 100, 20);
		contentPane.add(lblSubject1);

		cmbSubject1 = new JComboBox<>(new String[] {"Math", "Science", "English", "Bahasa", "History"});
		cmbSubject1.setBounds(130, 100, 250, 25);
		contentPane.add(cmbSubject1);

		JLabel lblSubject2 = new JLabel("Subject 2:");
		lblSubject2.setBounds(30, 140, 100, 20);
		contentPane.add(lblSubject2);

		cmbSubject2 = new JComboBox<>(new String[] {"Math", "Science", "English", "Bahasa", "History"});
		cmbSubject2.setBounds(130, 140, 250, 25);
		contentPane.add(cmbSubject2);

		JLabel lblSubject3 = new JLabel("Subject 3:");
		lblSubject3.setBounds(30, 180, 100, 20);
		contentPane.add(lblSubject3);

		cmbSubject3 = new JComboBox<>(new String[] {"Math", "Science", "English", "Bahasa", "History"});
		cmbSubject3.setBounds(130, 180, 250, 25);
		contentPane.add(cmbSubject3);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(75, 250, 100, 30);
		contentPane.add(btnUpdate);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(249, 250, 100, 30);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Update Enrollment");
		lblNewLabel.setBounds(30, 23, 84, 13);
		contentPane.add(lblNewLabel);

		// 🔁 Update Button Function
		btnUpdate.addActionListener(e -> {
			String icToUpdate = txtIC.getText().trim();
			String sub1 = cmbSubject1.getSelectedItem().toString();
			String sub2 = cmbSubject2.getSelectedItem().toString();
			String sub3 = cmbSubject3.getSelectedItem().toString();

			if (icToUpdate.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter the IC/Passport number.", "Missing Data", JOptionPane.WARNING_MESSAGE);
				return;
			}

			File inputFile = new File("students.txt");
			File tempFile = new File("temp.txt");
			boolean updated = false;

			try (
				BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
			) {
				String line;

				while ((line = reader.readLine()) != null) {
					String[] data = line.split(",");

					if (data.length >= 10 && data[1].equalsIgnoreCase(icToUpdate)) {
						data[6] = sub1;
						data[7] = sub2;
						data[8] = sub3;
						line = String.join(",", data);
						updated = true;
					}

					writer.write(line);
					writer.newLine();
				}

				reader.close();
				writer.close();

				if (updated) {
					inputFile.delete();
					tempFile.renameTo(inputFile);
					JOptionPane.showMessageDialog(this, "Subjects updated successfully.");
				} else {
					tempFile.delete();
					JOptionPane.showMessageDialog(this, "Student with IC/Passport not found.");
				}

			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		btnBack.addActionListener(e -> {
			new ReceptionistDashboard().setVisible(true);
			dispose();
		});
	}
}
