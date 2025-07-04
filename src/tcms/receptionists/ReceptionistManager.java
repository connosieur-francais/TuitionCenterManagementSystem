package tcms.receptionists;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import tcms.users.User;
import tcms.users.UserManager;

public class ReceptionistManager {

	private int fieldLength = 5;
	private static UserManager userManager;
	private Map<Integer, Receptionist> userIDReceptionistMap = new HashMap<>();
	private Map<Integer, Receptionist> receptionistIDReceptionistMap = new HashMap<>();
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
					userIDReceptionistMap.put(userID, receptionist);
					receptionistIDReceptionistMap.put(receptionistID, receptionist);

					// Debug Print
					System.out.println("Loaded receptionist " + receptionist);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveReceptionists(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write("receptionist_id,user_id,contact,email,address\n");
			for (Receptionist receptionist : receptionists) {
				bw.write(receptionist.toCSV());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public User findUserByReceptionistID(int receptionistID) {
		if (receptionistIDReceptionistMap.containsKey(receptionistID)) {
			Receptionist receptionist = receptionistIDReceptionistMap.get(receptionistID);
			int userID = receptionist.getUserID();
			User user = userManager.findUserByUserID(userID);
			System.out.println("findUserByReceptionistID: Successfully located user = " + user.getUsername());
			return user;
		} else {
			System.out.println("findUserByReceptionistID: Failed to locate user");
			return null;
		}
	}
	
	public Receptionist findReceptionistByUserID(int userID) {
		if (userIDReceptionistMap.containsKey(userID)) {
			return userIDReceptionistMap.get(userID);
		} else {
			System.out.println("findReceptionistByUserID: Failed to locate receptionist");
			return null;
		}
	}
	
	public void updateReceptionistInCSV(UserManager um) {
		userManager = um;
		List<User> users = userManager.getAllUsers();
		System.out.println("updateReceptionistInCSV: Updating receptionists in receptionists.csv");
		System.out.println("Total users: " + users.size());

		// Map current receptionist userIDs for fast lookup
		Set<Integer> existingReceptionistUserIDs = new HashSet<>();
		for (Receptionist receptionist : receptionists) {
			existingReceptionistUserIDs.add(receptionist.getUserID());
		}

		boolean updated = false;

		// Add new receptionists from users.csv
		for (User user : users) {
			if (user.getRole().equalsIgnoreCase("receptionist")) {
				if (!existingReceptionistUserIDs.contains(user.getID())) {
					System.out.println("Adding new receptionist: " + user.getUsername());
					int newReceptionistID = nextAvailableReceptionistID(); // Find lowest available ID
					Receptionist newReceptionist = new Receptionist(newReceptionistID, user.getID());
					receptionists.add(newReceptionist);
					userIDReceptionistMap.put(user.getID(), newReceptionist);
					receptionistIDReceptionistMap.put(newReceptionistID, newReceptionist);
					updated = true;
				} else {
					System.out.println("Receptionist already exists: " + user.getUsername());
				}
			}
		}

		// Remove receptionists who are no longer in users.csv or no longer have the role
		Iterator<Receptionist> iterator = receptionists.iterator();
		while (iterator.hasNext()) {
			Receptionist receptionist = iterator.next();
			User user = userManager.findUserByUserID(receptionist.getUserID());

			if (user == null || !user.getRole().equalsIgnoreCase("receptionist")) {
				String username = (user == null) ? "Unknown" : user.getUsername();
				System.out.println("Removing receptionist (no longer has role): " + username);
				iterator.remove();
				updated = true;
			}
		}

		// Save only if changes were made
		if (updated) {
			receptionists.sort(Comparator.comparingInt(Receptionist::getReceptionistID));
			saveReceptionists("src/receptionists.csv");
		}
	}

	public int nextAvailableReceptionistID() {
		Set<Integer> usedIDs = new HashSet<>();
		for (Receptionist receptionist : receptionists) {
			usedIDs.add(receptionist.getReceptionistID());
		}

		int id = 1;
		while (usedIDs.contains(id)) {
			id++;
		}
		return id;
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
	
	public Map<Integer, Receptionist> getUserIDReceptionistMap() {
		return userIDReceptionistMap;
	}

	public Map<Integer, Receptionist> getReceptionistIDReceptionistMap() {
		return receptionistIDReceptionistMap;
	}
	
	public List<Receptionist> getAllReceptionists() {
		return receptionists;
	}

}
