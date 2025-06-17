package tcms.admin;

import java.io.*;
import java.util.*;

import tcms.users.User;
import tcms.users.UserManager;

public class AdminManager {

	UserManager userManager = new UserManager();

	private List<Admin> admins = new ArrayList<>();

	public void loadAdmins(String filename) {
		admins.clear();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) { // Creates a list of users by reading
																					// from src/admins.csv
			String line;
			boolean skipHeader = true;

			while ((line = br.readLine()) != null) {
				if (skipHeader) {
					skipHeader = false;
					continue;
				}
				String[] fields = line.split(",");
				if (fields.length >= 6) {
					Admin admin = new Admin(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
							fields[3]);
					admins.add(admin);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
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
		for (Admin admin : admins) {
			if (admin.getAdminID() != adminID) {
				return null;
			} else {
				int userID = admin.getUserID();
				User user = userManager.findUserByUserID(userID);
				return user;
			}
		}
		return null;
	}

	public void updateAdminsInCSV() {
		List<User> users = userManager.getAllUsers(); // Initialize users list

		for (User user : users) {
			// Check if the user's role is Admin
			if (user.getRole().equalsIgnoreCase("admin")) {
				boolean exists = false;

				// Check if an admin with the same user ID already exists
				for (Admin admin : admins) {
					if (user.getID() == admin.getUserID()) {
						exists = true;
						break;
					}
				}
				// If not found, create a new Admin and add to CSV
				if (!exists) {
					Admin newAdmin = new Admin(user.getID());
					newAdmin.toCSV();
					admins.add(newAdmin); // add to current list of admins
				}
			}
		}
	}

	public List<Admin> getAllAdmins() {
		return admins;
	}
}
