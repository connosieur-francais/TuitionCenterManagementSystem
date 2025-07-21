package tcms.receptionists;

import java.awt.EventQueue;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class DeleteStudent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIC;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				DeleteStudent frame = new DeleteStudent();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public DeleteStudent() {
		setTitle("Delete Student");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("IC/Passport:");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(10, 41, 74, 13);
		contentPane.add(lblNewLabel);

		txtIC = new JTextField();
		txtIC.setBounds(94, 38, 170, 19);
		contentPane.add(txtIC);
		txtIC.setColumns(10);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(32, 102, 85, 30);
		contentPane.add(btnDelete);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(197, 102, 85, 30);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_1 = new JLabel("Delete Student");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 10, 74, 13);
		contentPane.add(lblNewLabel_1);

		btnDelete.addActionListener(e -> {
			String icToDelete = txtIC.getText().trim();

			if (icToDelete.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter the IC/Passport number.", "Input Required", JOptionPane.WARNING_MESSAGE);
				return;
			}

			File inputFile = new File("students.txt");
			File tempFile = new File("temp.txt");
			boolean found = false;

			try (
				BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
			) {
				String line;

				while ((line = reader.readLine()) != null) {
					String[] data = line.split(",");

					if (data.length >= 2 && data[1].equalsIgnoreCase(icToDelete)) {
						found = true;
						continue; 
					}

					writer.write(line);
					writer.newLine();
				}

				reader.close();
				writer.close();

				if (found) {
					inputFile.delete();
					tempFile.renameTo(inputFile);
					JOptionPane.showMessageDialog(this, "Student deleted successfully.");
				} else {
					tempFile.delete(); // nothing changed
					JOptionPane.showMessageDialog(this, "Student not found.");
				}

			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "File error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		btnBack.addActionListener(e -> {
			new ReceptionistDashboard().setVisible(true);
			dispose();
		});
	}
}
