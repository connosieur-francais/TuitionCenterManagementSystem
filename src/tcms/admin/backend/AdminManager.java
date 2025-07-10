package tcms.admin.backend;

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

import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;

public class AdminManager {

	private int fieldLength = 5;
	private static UserManager userManager;
	private Map<Integer, Admin> userIDAdminMap = new HashMap<>();
	private Map<Integer, Admin> adminIDAdminMap = new HashMap<>();
	private List<Admin> admins = new ArrayList<>();

	public void loadAdmins(String filename) {
		admins.clear();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
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

					System.out.println("Loaded admin: " + admin);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveAdmins(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write(Constants.ADMINS_CSV_HEADER);
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

	public void updateAdminsInCSV(UserManager um) {
		userManager = um;
		List<User> users = userManager.getAllUsers();
		System.out.println("updateAdminsInCSV: Updating admins in admins.csv");

		Set<Integer> existingAdminUserIDs = new HashSet<>();
		for (Admin admin : admins) {
			existingAdminUserIDs.add(admin.getUserID());
		}

		boolean updated = false;

		for (User user : users) {
			if (user.getRole().equalsIgnoreCase("admin")) {
				if (!existingAdminUserIDs.contains(user.getID())) {
					System.out.println("Account doesn't exist. Adding account: " + user.getUsername());
					int newAdminID = nextAvailableAdminID();
					Admin newAdmin = new Admin(newAdminID, user.getID());
					admins.add(newAdmin);
					adminIDAdminMap.put(newAdminID, newAdmin);
					userIDAdminMap.put(user.getID(), newAdmin);
					updated = true;
				} else {
					System.out.println("Admin account exists: " + user.getUsername());
				}
			}
		}

		Iterator<Admin> iterator = admins.iterator();
		while (iterator.hasNext()) {
			Admin admin = iterator.next();
			User user = userManager.findUserByUserID(admin.getUserID());

			if (user == null || !user.getRole().equalsIgnoreCase("admin")) {
				String username = (user == null) ? "Unknown/Deleted User" : user.getUsername();

				if (user != null && user.getUsername().equalsIgnoreCase("admin")) {
					System.out.println("Skipping removal of core admin user: " + user.getUsername());
					continue;
				}

				System.out.println("Removing admin (no longer has admin role): " + username);
				iterator.remove();
				userIDAdminMap.remove(admin.getUserID());
				adminIDAdminMap.remove(admin.getAdminID());
				updated = true;
			}
		}

		if (updated) {
			admins.sort(Comparator.comparingInt(Admin::getAdminID));
			saveAdmins(Constants.ADMINS_CSV);
		}
	}

	public int nextAvailableAdminID() {
		Set<Integer> usedIDs = new HashSet<>();
		for (Admin admin : admins) {
			usedIDs.add(admin.getAdminID());
		}
		int id = 1;
		while (usedIDs.contains(id)) {
			id++;
		}
		return id;
	}

	public boolean isValidEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			return false;
		}
		int positionOfAt = email.indexOf('@');
		int positionOfLastDot = email.lastIndexOf('.');
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
