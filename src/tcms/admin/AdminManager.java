package tcms.admin;

import java.io.*;
import java.util.*;

import tcms.users.User;
import tcms.users.UserManager;

public class AdminManager {

	private int fieldLength = 5; // If CSV file fields change, change this number
	private UserManager userManager = new UserManager();
	private Map<Integer, Admin> userIDAdminMap = new HashMap<>();
	private Map<Integer, Admin> adminIDAdminMap = new HashMap<>();
	private List<Admin> admins = new ArrayList<>();

	public void loadAdmins(String filename) {
		admins.clear();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) { // Creates a list of users by
																					// reading
			// from src/admins.csv
			String line;
			boolean skipHeader = true;

			while ((line = br.readLine()) != null) {
				if (skipHeader) {
					skipHeader = false;
					continue;
				}
				String[] fields = line.split(",");
				if (fields.length >= fieldLength) {
					int adminID = Integer.parseInt(fields[0].trim());
					int userID = Integer.parseInt(fields[1].trim());
					String contact = fields[2].trim();
					String email = fields[3].trim();
					String address = fields[4].trim();
					Admin admin = new Admin(adminID, userID, contact, email, address);
					admins.add(admin);
					userIDAdminMap.put(userID, admin);
					adminIDAdminMap.put(adminID, admin);

					// Debug print
					System.out.println("Loaded admin: " + admin);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveAdmins(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write("admin_id,user_id,contact,email,address\n");
			for (Admin admin : admins) {
				bw.write(admin.toCSV());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User findUserByAdminID(int adminID) {

		if (adminIDAdminMap.get(adminID) != null) {
			Admin admin = adminIDAdminMap.get(adminID);
			int userID = admin.getUserID();
			User user = userManager.findUserByUserID(userID);
			System.out.println("findUserByAdminID: Successfully located user = " + user.getUsername());
			return user;
		} else {
			System.out.println("findUserByAdminID: Failed to locate user");
			return null;
		}
	}

	public Admin findAdminByUserID(int userID) {
		if (userIDAdminMap.get(userID) != null) {
			return userIDAdminMap.get(userID);
		} else {
			System.out.println("findAdminByUserID: Failed to locate admin");
			return null;
		}
	}

	public void updateAdminsInCSV() {
		userManager.loadUsers("src/users.csv");
		List<User> users = userManager.getAllUsers();
		System.out.println("updateAdminsInCSV: Updating admins in admins.csv");
		System.out.println("Total users: " + users.size());

		// Map current admin userIDs for faster lookup
		Set<Integer> existingAdminUserIDs = new HashSet<>();
		for (Admin admin : admins) {
			existingAdminUserIDs.add(admin.getUserID());
		}

		boolean updated = false;

		// Add new admins from users.csv
		for (User user : users) {
			if (user.getRole().equalsIgnoreCase("admin")) {
				if (!existingAdminUserIDs.contains(user.getID())) {
					System.out.println("Account doesn't exist. Adding account: " + user.getUsername());
					int newAdminID = nextAvailableAdminID();
					Admin newAdmin = new Admin(newAdminID, user.getID());
					admins.add(newAdmin);
					updated = true;
				} else {
					System.out.println("Admin account exists: " + user.getUsername());
				}
			}
		}

		// Remove admins who are no longer admins in users.csv
		Iterator<Admin> iterator = admins.iterator();
		while (iterator.hasNext()) {
			Admin admin = iterator.next();
			User user = userManager.findUserByUserID(admin.getUserID()); // Use consistent lookup

			if (user == null || !user.getRole().equalsIgnoreCase("admin")) {
				String username = (user == null) ? "Unknown/Deleted User" : user.getUsername();
				System.out.println("Removing admin (no longer has admin role): " + username);
				iterator.remove();
				updated = true;
			}
		}

		// Save only if changes were made
		if (updated) {
			saveAdmins("src/admins.csv");
		}
	}

	public int nextAvailableAdminID() {
		int nextID = admins.stream().mapToInt(Admin::getAdminID).max().orElse(0) + 1;
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

	public Map<Integer, Admin> getUserIDAdminMap() {
		return userIDAdminMap;
	}

	public Map<Integer, Admin> getAdminIDAdminMap() {
		return adminIDAdminMap;
	}

	public List<Admin> getAllAdmins() {
		return admins;
	}
}
