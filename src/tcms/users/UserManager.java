package tcms.users;

import java.io.*;
import java.util.*;

import tcms.utils.Constants;

public class UserManager {
	
	private int fieldLength = 6; // If CSV file changes, change this number
	private Map<Integer, User> userMap = new HashMap<>();
	private Map<String, User> usernameMap = new HashMap<>();
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
				
				if (fields.length >= fieldLength) {
					int userID = Integer.parseInt(fields[0].trim());
					String username = fields[1].trim();
					String password = fields[2].trim();
					String role = fields[3].trim();
					int loginAttempts = Integer.parseInt(fields[4].trim());
					String status = fields[5].trim();

					User user = new User(userID, username, password, role, loginAttempts, status);
					users.add(user);
					userMap.put(userID, user); // Add to map | userID : User object
					usernameMap.put(username, user); // Add to map | username : User object

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
			bw.write("user_id,username,password,role,login_attempts,status\n");
			for (User user : users) {
				bw.write(user.toCSV());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void refreshUsersList(String filename) {
	    // Reload the users from file to ensure the list is up to date
	    List<User> latestUsers = new ArrayList<>();

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
	                latestUsers.add(user);
	                
	            }
	        }

	        // Replace current users list with refreshed list
	        users.clear();
	        users.addAll(latestUsers);

	        System.out.println("User list refreshed from: " + filename);
	        System.out.println("Total users: " + users.size());

	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (NumberFormatException e) {
	        System.out.println("Failed to parse number in user CSV. Check the file for formatting issues.");
	        e.printStackTrace();
	    }
	}
	
	public boolean renameUser(int userID, String newUsername) {
	    User user = userMap.get(userID);
	    if (user == null) {
	        System.out.println("renameUser: User ID not found: " + userID);
	        return false;
	    }
	    String oldUsernameKey = user.getUsername();
	    String newUsernameKey = newUsername;
	    // Optional: prevent duplicate usernames
	    if (usernameMap.containsKey(newUsernameKey)) {
	        System.out.println("renameUser: Username already exists: " + newUsername);
	        return false;
	    }
	    // Update maps and object
	    usernameMap.remove(oldUsernameKey);
	    user.setUsername(newUsername);
	    usernameMap.put(newUsernameKey, user);
	    // Save changes
	    saveUsers(Constants.USERS_CSV);
	    System.out.println("renameUser: Username updated from '" + oldUsernameKey + "' to '" + newUsernameKey + "'");
	    return true;
	}

	public User findUserByUsername(String username) {
		if (usernameMap.get(username) != null) {
			return usernameMap.get(username);
		} else {
			System.out.println("findUserByUsername: Failed to locate user");
			return null;
		}
	}
	

	public User findUserByUserID(int id) {
		if (userMap.get(id) != null) {
			return userMap.get(id); // fast lookup			
		} else {
			System.out.println("findUserByUserID: Failed to locate user");
			return null;
		}
	}
	
	public void addUser(User newUser) {
		// Adds user to existing user list and HashMaps
		Integer userID = Integer.valueOf(newUser.getID());
		String username = newUser.getUsername();
		
		users.add(newUser);
		usernameMap.put(username, newUser);
		userMap.put(userID, newUser);
	}
	
	public void removeUser(User user) {
		Integer userID = Integer.valueOf(user.getID());
		String username = user.getUsername();
		
		users.remove(user);
		userMap.remove(userID);
		usernameMap.remove(username);
	}

	public void decrementLoginAttempts(User user) {
		user.decreaseLoginAttempts();
	}

	public void resetLoginAttempts(User user) {
		user.setLoginAttempts(3);
	}
	
	public Map<String, User> getUsernameMap() {
		return usernameMap;
	}
	
	public Map<Integer, User> getUserMap() {
		return userMap;
	}

	public List<User> getAllUsers() {
		return users;
	}
}
