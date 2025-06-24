package tcms.receptionists;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import tcms.users.User;
import tcms.users.UserManager;

public class ReceptionistManager {

	private int fieldLength = 5;
	private UserManager userManager = new UserManager();
	private List<Receptionist> receptionists = new ArrayList<>();

	public void loadReceptionists(String filename) {

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			// from src/receptionists.csv
			String line;
			boolean skipHeader = true;

			while ((line = br.readLine()) != null) {
				if (skipHeader) {
					skipHeader = false;
					continue;
				}
				String[] fields = line.split(",");

				if (fields.length >= fieldLength) {
					int receptionistID = Integer.parseInt(fields[0].trim());
					int userID = Integer.parseInt(fields[1].trim());
					String contact = fields[2].trim();
					String email = fields[3].trim();
					String address = fields[4].trim();
					Receptionist receptionist = new Receptionist(receptionistID, userID, contact, email, address);
					receptionists.add(receptionist); // Add receptionist to receptionist list

					// Debug Print
					System.out.println("Loaded receptionist " + receptionist);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void refreshReceptionistList() {
		// Reload Users
		userManager.loadUsers("src//users.csv");
		List<User> users = userManager.getAllUsers();

		// Map current receptionist userIDs for faster lookup
		Set<Integer> existingReceptionistUserIDs = new HashSet<>();
		for (Receptionist receptionist : receptionists) {
			existingReceptionistUserIDs.add(receptionist.getReceptionistID());
		}

		boolean updated = false;

		// Add new receptionists from users.csv if they don't already exist
		for (User user : users) {
			if (user.getRole().equalsIgnoreCase("receptionist")
					&& !existingReceptionistUserIDs.contains(user.getID())) {
				int newReceptionistID = nextAvailableReceptionistID();
				Receptionist newReceptionist = new Receptionist(newReceptionistID, user.getID());
				receptionists.add(newReceptionist);
				System.out.println("Added new receptionist from refresh: " + user.getUsername());
				updated = true;
			}
		}
		
		// save only if there was a change
		if (updated) {
			saveReceptionists("src//receptionists.csv");
		}
	}

	public void saveReceptionists(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write("receptionist_id,user_id,contact,email,address");
			for (Receptionist receptionist : receptionists) {
				bw.write(receptionist.toCSV());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int nextAvailableReceptionistID() {
		int nextID = receptionists.stream().mapToInt(Receptionist::getReceptionistID).max().orElse(0) + 1;
		return nextID;
	}

	public boolean isValidEmail(String email) {
		// Check for null or empty string after trimming whitespace
		if (email == null || email.trim().isEmpty()) {
			return false;
		}

		// Find the position of '@' and the last '.'
		int positionOfAt = email.indexOf('@');
		int positionOfLastDot = email.lastIndexOf('.');

		// Conditions for a valid email:
		// 1. '@' must exist and not be the first character
		// 2. '.' must come after '@'
		// 3. '.' must not be the last character
		boolean hasValidAt = positionOfAt > 0;
		boolean hasDotAfterAt = positionOfLastDot > positionOfAt + 1;
		boolean dotNotAtEnd = positionOfLastDot < email.length() - 1;

		return hasValidAt && hasDotAfterAt && dotNotAtEnd;
	}

	public static boolean isValidContact(String contact) {
		String contactRegex = "^01[0-46-9]-\\d{3}-\\d{4}$";
		return Pattern.matches(contactRegex, contact);
	}

}
