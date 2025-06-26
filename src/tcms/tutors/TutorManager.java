package tcms.tutors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
					Tutor tutor = new Tutor(tutorID, userID, contact, email, address,
							assignedLevel, assignedSubject1, assignedSubject2, assignedSubject3);
					
					// Debug print
					System.out.println("Loaded tutor: " + tutor);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
