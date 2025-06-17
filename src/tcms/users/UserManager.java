package tcms.users;

import java.io.*;
import java.util.*;

public class UserManager {

	private List<User> users = new ArrayList<>();

	public void loadUsers(String filename) {
		users.clear();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			boolean skipHeader = true;

			while ((line = br.readLine()) != null) {
				if (skipHeader) {
					skipHeader = false;
					continue;
				}
				String[] fields = line.split(",");
				
				if (fields.length >= 6) {
					int userID = Integer.parseInt(fields[0].trim());
					String username = fields[1].trim();
					String password = fields[2].trim();
					String role = fields[3].trim();
					int loginAttempts = Integer.parseInt(fields[4].trim());
					String status = fields[5].trim();

					User user = new User(userID, username, password, role, loginAttempts, status);
					users.add(user);

					System.out.println("Loaded user: " + user);
				} else {
					System.out.println("Invalid line (skipped): " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Failed to parse number in user CSV. Check the file for formatting issues.");
			e.printStackTrace();
		}
	}

	public void saveUsers(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write("User_id,username,password,role,login_attempts,status\n");
			for (User user : users) {
				bw.write(user.toCSV());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User findUserByUsername(String username) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				return user;
			}
		}
		return null;
	}

	public User findUserByUserID(int id) {
		for (User user : users) {
			if (user.getID() == id) {
				return user;
			}
		}
		return null;
	}

	public void decrementLoginAttempts(User user) {
		user.decreaseLoginAttempts();
	}

	public void resetLoginAttempts(User user) {
		user.setLoginAttempts(3);
	}

	public List<User> getAllUsers() {
		return users;
	}
}
