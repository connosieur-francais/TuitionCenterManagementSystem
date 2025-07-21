/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tcms.receptionists;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ReceptionistDashboard extends javax.swing.JFrame {

	public ReceptionistDashboard() {
		initComponents();
	}

	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jButton6 = new javax.swing.JButton();
		btnRegisterStudent = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();
		btnPay = new javax.swing.JButton();
		jButton10 = new javax.swing.JButton();
		btnDelete = new javax.swing.JButton();
		btnProfile = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		txtIC = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		txtEmail = new javax.swing.JTextField();
		txtContact = new javax.swing.JTextField();
		txtLevel = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		txtAddress = new javax.swing.JTextField();
		txtName = new javax.swing.JTextField();
		txtSubject = new javax.swing.JTextField();
		txtMonth = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel2.setText("Welcome, Kow");

		jLabel3.setText("Recepronist");

		jButton6.setText("Logout");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		btnRegisterStudent.setText("Register Student");
		btnRegisterStudent.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRegisterStudentActionPerformed(evt);
			}
		});

		btnUpdate.setText("Update Enrollment");
		btnUpdate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnUpdateActionPerformed(evt);
			}
		});

		btnPay.setText("Accept Payment");
		btnPay.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPayActionPerformed(evt);
			}
		});

		jButton10.setText("Generate Receipt");
		jButton10.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton10ActionPerformed(evt);
			}
		});

		btnDelete.setText("Delete Student");
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDeleteActionPerformed(evt);
			}
		});

		btnProfile.setText("Update Profie");
		btnProfile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnProfileActionPerformed(evt);
			}
		});

		jLabel1.setText("Name :");

		jLabel4.setText("IC/Passport :");

		jLabel5.setText("Email :");

		jLabel6.setText("Contact :");

		jLabel7.setText("Address :");

		txtEmail.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtEmailActionPerformed(evt);
			}
		});

		jLabel8.setText("Level :");

		jLabel9.setText("Subject :");

		jLabel10.setText("Enrolment Month :");

		txtAddress.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtAddressActionPerformed(evt);
			}
		});

		txtName.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtNameActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jButton6))
						.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(jPanel1Layout.createSequentialGroup().addGap(153, 153, 153)
												.addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(btnUpdate).addComponent(btnDelete,
																javax.swing.GroupLayout.PREFERRED_SIZE, 120,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(79, 79, 79)
												.addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(btnProfile, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnPay, javax.swing.GroupLayout.DEFAULT_SIZE, 130,
																Short.MAX_VALUE))
												.addGap(76, 76, 76)
												.addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(jPanel1Layout.createSequentialGroup()
																.addComponent(btnRegisterStudent,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 130,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE))
														.addGroup(jPanel1Layout.createSequentialGroup()
																.addComponent(jButton10,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 130,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(0, 0, Short.MAX_VALUE))))
										.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addGap(29, 29, 29))
												.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
												.addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(txtAddress)
														.addComponent(txtSubject,
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(txtLevel).addComponent(txtMonth).addComponent(
																txtContact, javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.PREFERRED_SIZE, 740,
																javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(jPanel1Layout.createSequentialGroup()
																.addGroup(jPanel1Layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING,
																		false)
																		.addComponent(jLabel4,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				98, Short.MAX_VALUE)
																		.addComponent(jLabel1,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
																.addGap(0, 0, Short.MAX_VALUE))
														.addComponent(
																jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(jPanel1Layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING,
																		false)
																.addComponent(txtEmail,
																		javax.swing.GroupLayout.DEFAULT_SIZE, 740,
																		Short.MAX_VALUE)
																.addComponent(txtName).addComponent(txtIC)))))
								.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(10, 10, 10)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(jButton6))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel3)
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel1).addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel4).addComponent(txtIC, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel5).addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel6).addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(6, 6, 6)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel7).addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel8).addComponent(txtLevel, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(12, 12, 12)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel9).addComponent(txtSubject, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel10).addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRegisterStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(79, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>

	private void btnRegisterStudentActionPerformed(java.awt.event.ActionEvent evt) {
		String name = txtName.getText().trim();
		String ic = txtIC.getText().trim();
		String email = txtEmail.getText().trim();
		String contact = txtContact.getText().trim();
		String address = txtAddress.getText().trim();
		String level = txtLevel.getText().trim();
		String subject = txtSubject.getText().trim();
		String month = txtMonth.getText().trim();

		if (name.isEmpty() || ic.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty()
				|| level.isEmpty() || subject.isEmpty() || month.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill in all fields.");
			return;
		}

		String studentData = String.join(",", name, ic, email, contact, address, level, subject, month);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("students.csv", true))) {
			bw.write(studentData);
			bw.newLine();
			JOptionPane.showMessageDialog(this, "Student registered successfully!");
			clearFields();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error saving student data.");
		}
	}

	private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
		String ic = txtIC.getText().trim();
		String newSubjects = txtSubject.getText().trim();

		if (ic.isEmpty() || newSubjects.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter IC and new subjects.");
			return;
		}

		JOptionPane.showMessageDialog(this, "Subject enrollment updated (Mock). IC: " + ic);
	}

	private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {
		String contact = txtContact.getText().trim();
		String email = txtEmail.getText().trim();
		String address = txtAddress.getText().trim();

		if (contact.isEmpty() || email.isEmpty() || address.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill in all profile fields.");
			return;
		}

		JOptionPane.showMessageDialog(this, "Profile updated (Mock).");
	}

	private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {
		String ic = txtIC.getText().trim();
		String amount = txtMonth.getText().trim(); // Assume used for payment amount

		if (ic.isEmpty() || amount.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter IC and amount.");
			return;
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("payments.csv", true))) {
			bw.write(ic + "," + amount);
			bw.newLine();
			JOptionPane.showMessageDialog(this, "Payment recorded successfully!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error saving payment.");
		}
	}

	private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
		String ic = txtIC.getText().trim();
		if (ic.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter IC to delete.");
			return;
		}

		JOptionPane.showMessageDialog(this, "Student with IC " + ic + " deleted (Mock).");
	}

	private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
		String name = txtName.getText().trim();
		String ic = txtIC.getText().trim();
		String email = txtEmail.getText().trim();
		String contact = txtContact.getText().trim();
		String address = txtAddress.getText().trim();
		String level = txtLevel.getText().trim();
		String subject = txtSubject.getText().trim();
		String month = txtMonth.getText().trim();

		if (name.isEmpty() || ic.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty()
				|| level.isEmpty() || subject.isEmpty() || month.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill in all fields to generate the receipt.");
			return;
		}

		String receipt = "---------- Receipt ----------\n" + "Name: " + name + "\n" + "IC/Passport: " + ic + "\n"
				+ "Email: " + email + "\n" + "Contact: " + contact + "\n" + "Address: " + address + "\n" + "Level: "
				+ level + "\n" + "Subject: " + subject + "\n" + "Enrolment Month: " + month + "\n"
				+ "------------------------------";

		JOptionPane.showMessageDialog(this, receipt);
	}

	private void txtAddressActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out and exit?", "Logout",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	private void clearFields() {
		txtName.setText("");
		txtIC.setText("");
		txtEmail.setText("");
		txtContact.setText("");
		txtAddress.setText("");
		txtLevel.setText("");
		txtSubject.setText("");
		txtMonth.setText("");
	}

	// Main method to run the GUI
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ReceptionistDashboard().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnDelete;
	private javax.swing.JButton btnPay;
	private javax.swing.JButton btnProfile;
	private javax.swing.JButton btnRegisterStudent;
	private javax.swing.JButton btnUpdate;
	private javax.swing.JButton jButton10;
	private javax.swing.JButton jButton6;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JTextField txtAddress;
	private javax.swing.JTextField txtContact;
	private javax.swing.JTextField txtEmail;
	private javax.swing.JTextField txtIC;
	private javax.swing.JTextField txtLevel;
	private javax.swing.JTextField txtMonth;
	private javax.swing.JTextField txtName;
	private javax.swing.JTextField txtSubject;
	// End of variables declaration
}