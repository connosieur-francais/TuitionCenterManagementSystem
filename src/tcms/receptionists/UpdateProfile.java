package tcms.receptionists;

import java.awt.EventQueue;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UpdateProfile extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIC;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtContact;
	private JTextField txtPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UpdateProfile frame = new UpdateProfile();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public UpdateProfile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("IC/Passport:");
		lblNewLabel.setBounds(10, 60, 86, 13);
		contentPane.add(lblNewLabel);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 83, 86, 13);
		contentPane.add(lblName);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 106, 86, 13);
		contentPane.add(lblEmail);

		JLabel lblContactnumber = new JLabel("Contact Number:");
		lblContactnumber.setBounds(10, 129, 100, 13);
		contentPane.add(lblContactnumber);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 152, 86, 13);
		contentPane.add(lblPassword);

		JLabel lblNewLabel_1 = new JLabel("Update Profile");
		lblNewLabel_1.setBounds(10, 10, 150, 25);
		contentPane.add(lblNewLabel_1);

		txtIC = new JTextField();
		txtIC.setBounds(106, 57, 200, 19);
		contentPane.add(txtIC);
		txtIC.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(106, 80, 200, 19);
		contentPane.add(txtName);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(106, 103, 200, 19);
		contentPane.add(txtEmail);

		txtContact = new JTextField();
		txtContact.setColumns(10);
		txtContact.setBounds(106, 126, 200, 19);
		contentPane.add(txtContact);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(106, 149, 200, 19);
		contentPane.add(txtPassword);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(64, 221, 85, 30);
		contentPane.add(btnUpdate);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(246, 221, 85, 30);
		contentPane.add(btnBack);

		btnUpdate.addActionListener(e -> {
			String ic = txtIC.getText().trim();
			String name = txtName.getText().trim();
			String email = txtEmail.getText().trim();
			String contact = txtContact.getText().trim();
			String password = txtPassword.getText().trim();

			if (ic.isEmpty() || name.isEmpty() || email.isEmpty() || contact.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Missing Info", JOptionPane.WARNING_MESSAGE);
				return;
			}

			File inputFile = new File("receptionists.txt");
			File tempFile = new File("temp.txt");
			boolean found = false;

			try (
				BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
			) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] data = line.split(",");
					if (data.length >= 2 && data[1].equalsIgnoreCase(ic)) {
						String updatedLine = String.join(",", name, ic, email, contact, password);
						writer.write(updatedLine);
						found = true;
					} else {
						writer.write(line);
					}
					writer.newLine();
				}

				reader.close();
				writer.close();

				if (found) {
					inputFile.delete();
					tempFile.renameTo(inputFile);
					JOptionPane.showMessageDialog(this, "Profile updated successfully.");
				} else {
					tempFile.delete();
					JOptionPane.showMessageDialog(this, "Receptionist not found.");
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
