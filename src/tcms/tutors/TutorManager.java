package tcms.tutors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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

import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;

public class TutorManager {

	private final int fieldLength = 9;
	private final int subjectFieldLength = 3;
	private final int levelFieldLength = 2;
	private static UserManager userManager;
	private Map<Integer, Tutor> userIDTutorMap = new HashMap<>();
	private Map<Integer, Tutor> tutorIDTutorMap = new HashMap<>();
	private Map<Integer, Subject> subjectIDSubjectMap = new HashMap<>();
	private Map<Integer, String> levelIDLevelMap = new HashMap<>();
	private List<Tutor> tutors = new ArrayList<>();
	private List<Subject> subjects = new ArrayList<>();
	private List<Level> levels = new ArrayList<>();

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
					"tutor_id,user_id,contact,email,address,assigned_level,assigned_subjectID_1,assigned_subjectID_2,assigned_subjectID_3\n");
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

	public void updateTutorsInCSV(UserManager um) {
		userManager = um;
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
					int newTutorID = getFirstAvailableTutorID();
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
			// Sort tutors list by tutorID
			tutors.sort(Comparator.comparingInt(Tutor::getTutorID));
			saveTutors(Constants.TUTORS_CSV);
		}
	}

	public int getFirstAvailableTutorID() {
		Set<Integer> usedIDs = new HashSet<>();
		for (Tutor tutor : tutors) {
			usedIDs.add(tutor.getTutorID());
		}

		int id = 1;
		while (usedIDs.contains(id)) {
			id++;
		}
		return id;
	}

	public void addTutor(Tutor newTutor, User tutorUserAccount) {
	    Integer tutorID = Integer.valueOf(newTutor.getTutorID());
	    
	    // Add to main user system
	    System.out.println("Added user: " + tutorUserAccount.getUsername());
	    userManager.addUser(tutorUserAccount);
	    
	    // Add to tutor-specific lists and maps
	    System.out.println("Adding tutor with Email : " + newTutor.getEmail());
	    tutors.add(newTutor);
	    userIDTutorMap.put(tutorID, newTutor);
	    tutorIDTutorMap.put(tutorID, newTutor);
	}

	public void removeTutor(Tutor tutor) {
	    if (tutor == null) {
	        System.out.println("removeTutor: Null");
	        return;
	    }

	    // Gather info before removal
	    Integer userID = Integer.valueOf(tutor.getUserID());
	    Integer tutorID = Integer.valueOf(tutor.getTutorID());
	    User tutorUserAccount = findUserByTutorID(tutor.getTutorID());

	    // Remove from tutor-specific structures
	    tutors.remove(tutor);
	    userIDTutorMap.remove(userID);
	    tutorIDTutorMap.remove(tutorID);
	    userManager.removeUser(tutorUserAccount);
	}
	// SUBJECTS SECTION 
	public void loadSubjects(String filename) {

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			boolean skipHeader = true;

			while ((line = br.readLine()) != null) {
				if (skipHeader) {
					skipHeader = false;
					continue;
				}

				String[] fields = line.split(",");

				if (fields.length >= subjectFieldLength) {
					// Gather fields
					int subjectID = Integer.parseInt(fields[0].trim());
					String subjectName = fields[1];
					int levelID = Integer.parseInt(fields[2].trim());
					// Create subject object
					Subject subject = new Subject(subjectID, subjectName, levelID);
					// Add object to list and map
					subjects.add(subject);
					subjectIDSubjectMap.put(subjectID, subject);

					System.out.println("TutorManager -> Loaded Subject: " + subjectName);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Subject findSubjectBySubjectID(int subjectID) {
		if (subjectIDSubjectMap.get(subjectID) != null) {
			return subjectIDSubjectMap.get(subjectID);
		} else {
			System.out.println("TutorManager -> findSubjectBySubjectID: Failed to locate subject with ID");
			System.out.println("TutorManager -> findSubjectBySubjectID: Returning a subject with no name");
			return new Subject(0, "Not Assigned", 1);
		}
	}
	
	// LEVELS SECTION
	
	public void loadLevels(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			
			String line;
			boolean skipHeader = true;
			
			while ((line = br.readLine()) != null) {
				if (skipHeader) {
					skipHeader = false;
					continue;
				}
				
				String [] fields = line.split(",");
				
				if (fields.length >= levelFieldLength) {
					int levelID = Integer.parseInt(fields[0].trim());
					String levelName = fields[1].trim();
					
					Level lvl = new Level(levelID, levelName);
					levels.add(lvl);
					levelIDLevelMap.put(levelID, levelName);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String findLevelNameByLevelID(int levelID) {
		String levelName = levelIDLevelMap.get(levelID);
		if (levelName != null) {
			return levelName;
		} else {
			System.out.println("TutorManager -> findLevelNameByLevelID: Failed to locate level name");
			System.out.println("TutorManager -> findLevelNameByLevelID: Returning default level (Form 1)");
			return "Form 1";
		}
	}
	
	public Map<Integer, Subject> getSubjectIDSubjectMap() {
		return subjectIDSubjectMap;
	}

	public Map<Integer, Tutor> getUserIDTutorMap() {
		return userIDTutorMap;
	}

	public Map<Integer, Tutor> getTutorIDTutorMap() {
		return tutorIDTutorMap;
	}
	
	public Map<Integer, String> getLevelIDLevelMap() {
		return levelIDLevelMap;
	}
	
	public List<Level> getAllLevels() {
		return levels;
	}

	public List<Tutor> getAllTutors() {
		return tutors;
	}
	
	public List<Subject> getAllSubjects() {
		return subjects;
	}

}
