package tcms.utils;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tcms.users.User;
import tcms.users.UserManager;

public class Validators {
	
	// ============= Input Validators ====================
	public static boolean isValidName(String name) {
		// Check if name is empty
		if (name == null || name.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Name cannot be empty", "Invalid Name", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Checks if name is over 4 characters long
		boolean isValid = name.length() >= 4;
		if (!isValid) {
			JOptionPane.showMessageDialog(null, "Name has to be over 4 characters long", "Invalid Name",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public static boolean isUsernameUnique(String username, UserManager userManager, JFrame parent) {
	    for (User user : userManager.getAllUsers()) {
	        if (user.getUsername().equalsIgnoreCase(username)) {
	            JOptionPane.showMessageDialog(parent,
	                    "Username already exists. Please choose a different one.",
	                    "Duplicate Username",
	                    JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    }
	    return true;
	}


	public static boolean isValidPassword(String password) {
		// Check if password is empty
		if (password == null || password.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Password cannot be empty", "Invalid Password",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Check length
		if (password.length() < 8) {
			JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long", "Invalid Password",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Check for digit
		if (!password.matches(".*\\d.*")) {
			JOptionPane.showMessageDialog(null, "Password must contain at least one digit", "Invalid Password",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Check for uppercase letter
		if (!password.matches(".*[A-Z].*")) {
			JOptionPane.showMessageDialog(null, "Password must contain at least one uppercase letter",
					"Invalid Password", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Check for lowercase letter
		if (!password.matches(".*[a-z].*")) {
			JOptionPane.showMessageDialog(null, "Password must contain at least one lowercase letter",
					"Invalid Password", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean isValidEmail(String email) {
		// Check for null or empty string after trimming whitespace
		if (email == null || email.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email cannot be empty.", "Invalid Email", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Find the position of '@' and the last '.'
		int positionOfAt = email.indexOf('@');
		int positionOfLastDot = email.lastIndexOf('.');
		// Conditions for a valid email:
		boolean hasValidAt = positionOfAt > 0;
		boolean hasDotAfterAt = positionOfLastDot > positionOfAt + 1;
		boolean dotNotAtEnd = positionOfLastDot < email.length() - 1;

		boolean isValid = hasValidAt && hasDotAfterAt && dotNotAtEnd;

		if (!isValid) {
			JOptionPane.showMessageDialog(null, "Email format is invalid. Please enter a valid email address.",
					"Invalid Email", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean isValidContact(String contact) {
		// Check if contact is empty
		if (contact == null || contact.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Contact number cannot be empty.", "Invalid Contact",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Remove any spaces in the contact
		contact = contact.trim();
		// Make sure it only contains digits
		if (!contact.matches("\\d+")) {
			JOptionPane.showMessageDialog(null, "Contact number must contain only digits.", "Invalid Contact",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Make sure contact is 10 digits long
		if (contact.length() != 10) {
			JOptionPane.showMessageDialog(null, "Contact number must be exactly 10 digits long.", "Invalid Contact",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean isValidAddress(String address) {
		// Check if address is empty
		if (address == null || address.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Address cannot be empty", "Invalid Address",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// Make sure address doesn't have commas
		if (address.contains(",")) {
			JOptionPane.showMessageDialog(null, "Address cannot contain commas", "Invalid Address",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (!(address.length() >= 8)) {
			JOptionPane.showMessageDialog(null, "Address has to be over 8 characters long", "Invalid Address",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public static boolean hasValidSubjectSelection(List<String> selectedSubjects, JFrame parent) {
	    if (selectedSubjects == null || selectedSubjects.size() < 1 || selectedSubjects.size() > 3) {
	        JOptionPane.showMessageDialog(parent,
	                "Please select at least 1 and at most 3 subjects.",
	                "Invalid Subject Selection",
	                JOptionPane.WARNING_MESSAGE);
	        return false;
	    }
	    return true;
	}
	
	public static boolean isValidIncome(double income) {
		if (income != 0) {
			return true;
		}
		JOptionPane.showMessageDialog(null,
				"The date selected has no income to be reported.",
				"Report Generation Failed", 
				JOptionPane.INFORMATION_MESSAGE);
		return false;
	}
	
	public static boolean isMonthSelected(String month) {
		if (month != null) {
			return true;
		}
		JOptionPane.showMessageDialog(null,
				"Select a month before generating the report!",
				"Report Generation Failed",
				JOptionPane.WARNING_MESSAGE);
		return false;
	}
}
