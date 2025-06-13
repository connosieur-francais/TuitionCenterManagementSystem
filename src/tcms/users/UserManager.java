package tcms.users;
import java.io.*;
import java.util.*;

public class UserManager {

	private List<User> users = new ArrayList<>();

	public void loadUsers(String filename) {
		users.clear();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) { // Creates a list of users by reading
																					// from src/users.csv
			String line;
			boolean skipHeader = true;

			while ((line = br.readLine()) != null) {
				if (skipHeader) {
					skipHeader = false;
					continue;
				}
				String[] fields = line.split(",");
				if (fields.length >= 6) {
					User user = new User(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3],
							Integer.parseInt(fields[4]), fields[5]);
					users.add(user);
				}
			}
		} catch (IOException e) {
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
