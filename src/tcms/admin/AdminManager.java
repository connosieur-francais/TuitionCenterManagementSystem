package tcms.admin;

import java.io.*;
import java.util.*;

import tcms.users.User;
import tcms.users.UserManager;

public class AdminManager {

	private int fieldLength = 4; // If CSV file fields change, change this number
	private UserManager userManager = new UserManager();
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
					String email = fields[2].trim();
					String address = fields[3].trim();
					Admin admin = new Admin(adminID, userID, email, address);
					admins.add(admin);

					// Debug print
					System.out.println("Loaded admin: " + admin);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void refreshAdminList() {
		// Reload users
		userManager.loadUsers("src/users.csv");
		List<User> users = userManager.getAllUsers();

		// Map current admin userIDs for faster lookup
		Set<Integer> existingAdminUserIDs = new HashSet<>();
		for (Admin admin : admins) {
			existingAdminUserIDs.add(admin.getUserID());
		}

		boolean updated = false;

		// Add new admins from users.csv if they don't already exist
		for (User user : users) {
			if (user.getRole().equalsIgnoreCase("admin") && !existingAdminUserIDs.contains(user.getID())) {
				int newAdminID = nextAvailableAdminID();
				Admin newAdmin = new Admin(newAdminID, user.getID());
				admins.add(newAdmin);
				System.out.println("Added new admin from refresh: " + user.getUsername());
				updated = true;
			}
		}
		// Save only if there was a change
		if (updated) {
			saveAdmins("src/admins.csv");
		}
	}

	public void saveAdmins(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write("admin_id,user_id,email,address\n");
			for (Admin admin : admins) {
				bw.write(admin.toCSV());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User findUserByAdminID(int adminID) {
		int userID;
		for (Admin admin : admins) {
			if (admin.getAdminID() == adminID) {
				userID = admin.getUserID();
				User user = userManager.findUserByUserID(userID);
				return user;
			}
		}
		return null;
	}

	public Admin findAdminByUserID(int userID) {
		for (Admin admin : admins) {
			if (admin.getUserID() == userID) {
				return admin;
			}
		}

		return null;
	}

	public void updateAdminsInCSV() {
		userManager.loadUsers("src//users.csv");
		List<User> users = userManager.getAllUsers();
		System.out.println("updateAdminsInCSV: Updating admins in admins.csv");
		System.out.println("Total users: " + users.size());

		boolean updated = false;

		// Add new admin entries for users with role 'admin' that don't already exist
		for (User user : users) {
			if (user.getRole().equalsIgnoreCase("admin")) {
				System.out.println("Checking admin candidate: " + user.getUsername());
				boolean exists = false;

				for (Admin admin : admins) {
					if (user.getID() == admin.getUserID()) {
						exists = true;
						break;
					}
				}

				if (!exists) {
					System.out.println("Account doesn't exist. Adding account: " + user.getUsername());
					int newAdminID = nextAvailableAdminID();
					Admin newAdmin = new Admin(newAdminID, user.getID());
					admins.add(newAdmin);
					System.out.println("Admin added: UserID = " + newAdmin.getUserID());
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
			User user = userManager.findUserByUserID(admin.getUserID());

			if (user == null || !user.getRole().equalsIgnoreCase("admin")) {
				System.out.println("Removed admin (no longer admin role): UserID = " + admin.getUserID());
				iterator.remove();
				updated = true;
			}
		}

		// Save only if changes were made
		if (updated) {
			saveAdmins("src//admins.csv");
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

	public List<Admin> getAllAdmins() {
		return admins;
	}
}
