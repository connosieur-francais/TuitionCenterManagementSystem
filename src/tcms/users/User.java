package tcms.users;

import java.util.Map;

public class User {
	private int userID;
	private String username;
	private String password;
	private String role; // Admin, Receptionist, Tutor, Student
	private int loginAttempts;
	private String accountStatus; // active, locked
	
	private UserManager um = new UserManager(); // For next user id checking

	public User(int id, String username, String password, String role, int loginAttempts, String accountStatus) {
		setID(id);
		setUsername(username);
		setPassword(password);
		setRole(role);
		setLoginAttempts(loginAttempts);
		setAccountStatus(accountStatus);
	}

	public User(String username, String password, String role) { // Create new user
		setID(nextAvailableID());
		setUsername(username);
		setPassword(password);
		setRole(role);
		setLoginAttempts(0);
		setAccountStatus("active");
	}
	
	private int nextAvailableID() {
		um.loadUsers("src//users.csv");
		Map<Integer, User> userMap = um.getUserMap();
		int nextID = userMap.size() + 1;
		return nextID;
	}
	
	public void setID(int id) {
		this.userID = id;
	}

	public int getID() {
		return this.userID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void decreaseLoginAttempts() {
		this.loginAttempts--;
	}

	public void setAccountStatus(String status) {
		this.accountStatus = status;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getRole() {
		return this.role;
	}

	public String getAccountStatus() {
		return this.accountStatus;
	}

	public int getLoginAttempts() {
		return this.loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public String toCSV() {
		return String.join(",", String.valueOf(this.userID), this.username, this.password, this.role,
				String.valueOf(this.loginAttempts), this.accountStatus);
	}
}
