package tcms.users;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Admin {
	private static int idNumber = 1;
	private String adminID;
	private int userID;
	private String email;
	private String address;
	
	public Admin() {
		
	}
	
	public String getAdminID() {
		return adminID;
	}
	
	public static String advanceAdminID() {
		String ID;
		String prefix = "A";
		int number = idNumber;
		
		idNumber++;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(prefix).append(String.valueOf(number));
		ID = stringBuilder.toString();
		return ID;
		
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
