package tcms.admin;

import java.io.*;
import java.util.*;

import tcms.users.User;
import tcms.users.UserManager;

public class AdminManager {
	UserManager userManager = new UserManager();
	private List<Admin> admins = new ArrayList<>();

	public void loadAdmins() {
		admins.clear();

		try (BufferedReader br = new BufferedReader(new FileReader("src//admin.csv"))) { // Creates a list of users by
																							// reading
			// from src/admin.csv
			String line;
			boolean skipHeader = true;

			while ((line = br.readLine()) != null) {
				if (skipHeader) {
					skipHeader = false;
					continue;
				}
				String[] fields = line.split(",");
				if (fields.length >= 4) {
					int adminID = Integer.parseInt(fields[0].trim());
					int userID = Integer.parseInt(fields[1].trim());
					String email = fields[2].trim();
					String address = fields[3].trim();
					Admin admin = new Admin(adminID, userID, email, address);
					admins.add(admin);

					// Debug print
					System.out.println("Loaded admin: ID=" + fields[0] + ", UserID=" + fields[1] + ", Email="
							+ fields[2] + ", Address=" + fields[3]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveAdmins(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write("admin_id,user_id,email,address\n");
			updateAdminsInCSV();
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
		System.out.println("updateAdminsInCSV: Updating admins in admin.csv");
		System.out.println("Total users: " + users.size());

		boolean updated = false;

		for (User user : users) { // LINE 87
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
					System.out.println("Adding new admin: " + user.getUsername());
					int newAdminID = nextAvailableAdminID();
					Admin newAdmin = new Admin(newAdminID, user.getID());
					admins.add(newAdmin);
					System.out.println("Admin added: UserID = " + newAdmin.getUserID());
					updated = true;
				} else {
					System.out.println("Admin already exists: " + user.getUsername());
				}
			}
		}

		if (updated) {
			saveAdmins("src/admin.csv"); // write to file only once after all changes
		}
	}

	public int nextAvailableAdminID() {
		int nextID = admins.stream().mapToInt(Admin::getAdminID).max().orElse(0) + 1;
		return nextID;
	}

	public List<Admin> getAllAdmins() {
		return admins;
	}
}
