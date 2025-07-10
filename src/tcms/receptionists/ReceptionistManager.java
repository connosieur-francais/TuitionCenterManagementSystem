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
import tcms.utils.Constants;

public class ReceptionistManager {

	private int fieldLength = 5;
	private int paymentFieldLength = 6;
	private static UserManager userManager;
	private Map<Integer, Receptionist> userIDReceptionistMap = new HashMap<>();
	private Map<Integer, Receptionist> receptionistIDReceptionistMap = new HashMap<>();
	private List<Receptionist> receptionists = new ArrayList<>();

	private Map<Integer, Payment> paymentIDPaymentMap = new HashMap<>();
	private List<Payment> payments = new ArrayList<>();

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

			// Sort a copy of the list for saving only
			List<Receptionist> sortedReceptionists = new ArrayList<>(receptionists);
			sortedReceptionists.sort(Comparator.comparingInt(Receptionist::getReceptionistID));

			for (Receptionist receptionist : sortedReceptionists) {
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

		// Remove receptionists who are no longer in users.csv or no longer have the
		// role
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
			saveReceptionists(Constants.RECEPTIONISTS_CSV);
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

	public void addReceptionist(Receptionist newReceptionist, User receptionistUserAccount) {
		Integer recepID = Integer.valueOf(newReceptionist.getReceptionistID());
		// Add to main user system
		System.out.println("Added user: " + receptionistUserAccount.getUsername());
		userManager.addUser(receptionistUserAccount);

		// Add to receptionist-specific lists and maps
		System.out.println("Adding receptionist with Email : " + newReceptionist.getEmail());
		receptionists.add(newReceptionist);
		userIDReceptionistMap.put(recepID, newReceptionist);
		receptionistIDReceptionistMap.put(recepID, newReceptionist);
	}

	public void removeReceptionist(Receptionist receptionist) {
		if (receptionist == null) {
			System.out.println("removeReceptionist: Null");
			return;
		}
		// Gather info before removal
		Integer userID = Integer.valueOf(receptionist.getUserID());
		Integer receptionistID = Integer.valueOf(receptionist.getReceptionistID());
		User receptionistUserAccount = findUserByReceptionistID(receptionist.getReceptionistID());

		// Remove from receptionist-specific structures
		receptionists.remove(receptionist);
		userIDReceptionistMap.remove(userID);
		receptionistIDReceptionistMap.remove(receptionistID);
		userManager.removeUser(receptionistUserAccount);

	}

	public int getFirstAvailableReceptionistID() {
		List<Integer> ids = new ArrayList<>();

		for (Receptionist r : receptionists) {
			ids.add(r.getReceptionistID());
		}

		int expected = 1;
		for (int id : ids) {
			if (id != expected)
				return expected;
			expected++;
		}
		return expected;
	}

	// For Payments.csv
	public void loadPayments(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			boolean skipHeader = true;

			while ((line = br.readLine()) != null) {
				if (skipHeader) {
					skipHeader = false;
					continue;
				}
				String[] fields = line.split(",");

				if (fields.length >= paymentFieldLength) {
					int paymentID = Integer.parseInt(fields[0].trim());
					int studentID = Integer.parseInt(fields[1].trim());
					int subjectID = Integer.parseInt(fields[2].trim());
					double amount = Double.parseDouble(fields[3].trim());
					String date = fields[4].trim();
					int receiptID = Integer.parseInt(fields[5].trim());

					Payment payment_record = new Payment(paymentID, studentID, subjectID, amount, date, receiptID);

					payments.add(payment_record);
					paymentIDPaymentMap.put(paymentID, payment_record);
					// Debug print
					System.out.println("ReceptionistManager -> Loaded payment record" + payment_record);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void savePayments(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Payment findPaymentByPaymentID(int paymentID) {
		Payment payment_record = paymentIDPaymentMap.get(paymentID);
		if (payment_record == null) {
			System.out.println("ReceptionistManager -> findPaymentByPaymentID: Failed to find payment record with paymentID" + paymentID);
			return null;
		}
		System.out.println("ReceptionistManager -> findPaymentByPaymentID: Found payment record with paymentID " + paymentID);
		return payment_record;
	}

	public Map<Integer, Payment> getPaymentIDPaymentMap() {
		return paymentIDPaymentMap;
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

	public List<Payment> getAllPayments() {
		return payments;
	}

}
