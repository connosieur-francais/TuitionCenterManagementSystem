package tcms.receptionists;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.io.*;

public class RegisterStudent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtIC;
	private JTextField txtEmail;
	private JTextField txtContact;
	private JTextArea txtAddress;
	private JComboBox<String> cmbLevel, cmbSubject1, cmbSubject2, cmbSubject3, cmbMonth;

	public RegisterStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(10, 70, 84, 13);
		contentPane.add(lblNewLabel);

		JLabel lblIcpassport = new JLabel("IC/Passport:");
		lblIcpassport.setBounds(10, 114, 84, 13);
		contentPane.add(lblIcpassport);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 158, 84, 13);
		contentPane.add(lblEmail);

		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setBounds(10, 198, 110, 13);
		contentPane.add(lblContactNumber);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 240, 84, 13);
		contentPane.add(lblAddress);

		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setBounds(362, 70, 84, 13);
		contentPane.add(lblLevel);

		JLabel lblSubject = new JLabel("Subject 1:");
		lblSubject.setBounds(362, 114, 84, 13);
		contentPane.add(lblSubject);

		JLabel lblSubject_1 = new JLabel("Subject 2:");
		lblSubject_1.setBounds(362, 158, 84, 13);
		contentPane.add(lblSubject_1);

		JLabel lblSubject_2 = new JLabel("Subject 3:");
		lblSubject_2.setBounds(362, 198, 84, 13);
		contentPane.add(lblSubject_2);

		JLabel lblSubject_3 = new JLabel("Month Enrolled:");
		lblSubject_3.setBounds(362, 240, 100, 13);
		contentPane.add(lblSubject_3);

		txtName = new JTextField();
		txtName.setBounds(104, 67, 248, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtIC = new JTextField();
		txtIC.setColumns(10);
		txtIC.setBounds(104, 111, 248, 19);
		contentPane.add(txtIC);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(104, 155, 248, 19);
		contentPane.add(txtEmail);

		txtContact = new JTextField();
		txtContact.setColumns(10);
		txtContact.setBounds(104, 195, 248, 19);
		contentPane.add(txtContact);

		txtAddress = new JTextArea();
		txtAddress.setBounds(104, 234, 248, 22);
		contentPane.add(txtAddress);

		cmbLevel = new JComboBox<>();
		cmbLevel.setModel(new DefaultComboBoxModel<>(new String[] {"Form 1", "Form 2", "Form 3", "Form 4", "Form 5"}));
		cmbLevel.setBounds(456, 66, 248, 21);
		contentPane.add(cmbLevel);

		cmbSubject1 = new JComboBox<>();
		cmbSubject1.setModel(new DefaultComboBoxModel<>(new String[] {"Math", "Science", "English", "Bahasa", "History"}));
		cmbSubject1.setBounds(456, 106, 248, 21);
		contentPane.add(cmbSubject1);

		cmbSubject2 = new JComboBox<>();
		cmbSubject2.setModel(new DefaultComboBoxModel<>(new String[] {"Math", "Science", "English", "Bahasa", "History"}));
		cmbSubject2.setBounds(456, 154, 248, 21);
		contentPane.add(cmbSubject2);

		cmbSubject3 = new JComboBox<>();
		cmbSubject3.setModel(new DefaultComboBoxModel<>(new String[] {"Math", "Science", "English", "Bahasa", "History"}));
		cmbSubject3.setBounds(456, 194, 248, 21);
		contentPane.add(cmbSubject3);

		cmbMonth = new JComboBox<>();
		cmbMonth.setModel(new DefaultComboBoxModel<>(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		cmbMonth.setBounds(456, 236, 248, 21);
		contentPane.add(cmbMonth);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(233, 305, 90, 30);
		contentPane.add(btnRegister);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(456, 305, 90, 30);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_1 = new JLabel("Register Student");
		lblNewLabel_1.setBounds(10, 21, 84, 13);
		contentPane.add(lblNewLabel_1);

		// ➕ Register Button Action
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText().trim();
				String ic = txtIC.getText().trim();
				String email = txtEmail.getText().trim();
				String contact = txtContact.getText().trim();
				String address = txtAddress.getText().trim();
				String level = cmbLevel.getSelectedItem().toString();
				String subject1 = cmbSubject1.getSelectedItem().toString();
				String subject2 = cmbSubject2.getSelectedItem().toString();
				String subject3 = cmbSubject3.getSelectedItem().toString();
				String month = cmbMonth.getSelectedItem().toString();

				if (name.isEmpty() || ic.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please complete all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}

				try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true))) {
					String data = name + "," + ic + "," + email + "," + contact + "," + address + "," +
							level + "," + subject1 + "," + subject2 + "," + subject3 + "," + month;
					writer.write(data);
					writer.newLine();
					JOptionPane.showMessageDialog(null, "Student registered successfully.");

					txtName.setText("");
					txtIC.setText("");
					txtEmail.setText("");
					txtContact.setText("");
					txtAddress.setText("");
					cmbLevel.setSelectedIndex(0);
					cmbSubject1.setSelectedIndex(0);
					cmbSubject2.setSelectedIndex(0);
					cmbSubject3.setSelectedIndex(0);
					cmbMonth.setSelectedIndex(0);

				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Error saving student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReceptionistDashboard().setVisible(true);
				dispose();
			}
		});
		
		setVisible(true);
	}
}