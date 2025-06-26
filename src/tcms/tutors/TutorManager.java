package tcms.tutors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tcms.users.User;
import tcms.users.UserManager;

public class TutorManager {

	private int fieldLength = 9;
	private UserManager userManager = new UserManager();
	private Map<Integer, Tutor> userIDTutorMap = new HashMap<>();
	private Map<Integer, Tutor> tutorIDTutorMap = new HashMap<>();
	private List<Tutor> tutors = new ArrayList<>();

	public void loadTutors(String filename) {
		tutors.clear();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			// from tutors.csv
			String line;
			boolean skipHeader = true;

			while ((line = br.readLine()) != null) {
				if (skipHeader) {
					skipHeader = false;
					continue;
				}

				String[] fields = line.split(",");
				if (fields.length >= fieldLength) {
					int tutorID = Integer.parseInt(fields[0].trim());
					int userID = Integer.parseInt(fields[1].trim());
					String contact = fields[2].trim();
					String email = fields[3].trim();
					String address = fields[4].trim();
					int assignedLevel = Integer.parseInt(fields[5].trim());
					int assignedSubject1 = Integer.parseInt(fields[6].trim());
					int assignedSubject2 = Integer.parseInt(fields[7].trim());
					int assignedSubject3 = Integer.parseInt(fields[8].trim());
					Tutor tutor = new Tutor(tutorID, userID, contact, email, address, assignedLevel, assignedSubject1,
							assignedSubject2, assignedSubject3);
					tutors.add(tutor);
					userIDTutorMap.put(userID, tutor);
					tutorIDTutorMap.put(tutorID, tutor);

					// Debug print
					System.out.println("Loaded tutor: " + tutor);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveTutors(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write(
					"tutor_id,user_id,contact,email,address,assigned_level,assigned_subjectID_1,assigned_subjectID_2,assigned_subjectID_3");
			for (Tutor tutor : tutors) {
				bw.write(tutor.toCSV());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User findUserByTutorID(int tutorID) {
		if (tutorIDTutorMap.get(tutorID) != null) {
			Tutor tutor = tutorIDTutorMap.get(tutorID);
			int userID = tutor.getUserID();
			User user = userManager.findUserByUserID(userID);
			System.out.println("findUserByTutorID: Successfully located user =" + user.getUsername());
			return user;
		} else {
			System.out.println("findUserByTutorID: Failed to locate user");
			return null;
		}
	}

	public Tutor findTutorByUserID(int userID) {
		if (userIDTutorMap.get(userID) != null) {
			Tutor tutor = userIDTutorMap.get(userID);
			System.out.println("findTutorByUserID: Found tutor with ID =" + tutor.getTutorID());
			return tutor;
		} else {
			System.out.println("findTutorByUserID: Failed to locate tutor");
			return null;
		}
	}

	public void updateTutorsInCSV() {
		userManager.loadUsers("src/users.csv");
		List<User> users = userManager.getAllUsers();
		System.out.println("updateTutorsInCSV: Updating tutors in tutors.csv");
		System.out.println("Total users: " + users.size());

		// Map current tutor userIDs for faster lookup
		Set<Integer> existingTutorUserIDs = new HashSet<>();
		for (Tutor tutor : tutors) {
			existingTutorUserIDs.add(tutor.getUserID());
		}

		boolean updated = false;

		// Add new tutors from users.csv
		for (User user : users) {
			if (user.getRole().equalsIgnoreCase("tutor")) {
				if (!existingTutorUserIDs.contains(user.getID())) {
					System.out.println("Account doesn't exist. Adding account: " + user.getUsername());
					int newTutorID = nextAvailableTutorID();
					Tutor newTutor = new Tutor(newTutorID, user.getID());
					tutors.add(newTutor);
					userIDTutorMap.put(user.getID(), newTutor);
					tutorIDTutorMap.put(newTutorID, newTutor);
					updated = true;
				} else {
					System.out.println("Tutor account exists: " + user.getUsername());
				}
			}
		}

		// Remove tutors who are no longer tutors in users.csv
		Iterator<Tutor> iterator = tutors.iterator();
		while (iterator.hasNext()) {
			Tutor tutor = iterator.next();
			User user = userManager.findUserByUserID(tutor.getUserID()); // Ensure this method exists

			if (user == null || !user.getRole().equalsIgnoreCase("tutor")) {
				String username = (user == null) ? "Unknown/Deleted User" : user.getUsername();
				System.out.println("Removing tutor (no longer has tutor role): " + username);
				iterator.remove();
				updated = true;
			}
		}

		// Save only if changes were made
		if (updated) {
			saveTutors("src/tutors.csv");
		}
	}

	public int nextAvailableTutorID() {
		int nextID = tutors.stream().mapToInt(Tutor::getTutorID).max().orElse(0) + 1;
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

	public Map<Integer, Tutor> getUserIDTutorMap() {
		return userIDTutorMap;
	}

	public Map<Integer, Tutor> getTutorIDTutorMap() {
		return tutorIDTutorMap;
	}

	public List<Tutor> getAllTutors() {
		return tutors;
	}

}
