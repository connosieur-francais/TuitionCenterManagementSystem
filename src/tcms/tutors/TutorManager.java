package tcms.tutors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

	public void refreshTutorsList() {
		// Reload users
		userManager.loadUsers("src/users.csv");
		List<User> users = userManager.getAllUsers();

		// Map current tutor userIDs for faster lookup
		Set<Integer> existingTutorUserIDs = new HashSet<>();
		for (Tutor tutor : tutors) {
			existingTutorUserIDs.add(tutor.getUserID());
		}
		
		boolean updated = false;

		for (User user : users) {
			if (user.getRole().equalsIgnoreCase("tutors") && !existingTutorUserIDs.contains(user.getID())) {
				int newTutorID = nextAvailableTutorID(); // to do 1;
				Tutor newTutor = new Tutor(newTutorID, user.getID());
				tutors.add(newTutor);
				System.out.println("Added new tutor from refresh: " + user.getUsername());
				updated = true;
			}
		}
		
		if (updated) {
			saveTutors("src//tutors.csv");
		}

	}
	
	public void saveTutors(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write("tutor_id,user_id,contact,email,address,assigned_level,assigned_subjectID_1,assigned_subjectID_2,assigned_subjectID_3");
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
			System.out.println("findUserByTutorID: Successfully located user" + user.getUsername());
			return user;
		} else {
			System.out.println("findUserByTutorID: Failed to locate user");
			return null;
		}
	}
	
	public int nextAvailableTutorID() {
		int nextID = tutors.stream().mapToInt(Tutor::getTutorID).max().orElse(0) + 1;
		return nextID;
	}
}
