package tcms.admin;

public class Admin {
	private static int idNumber = 1;

	private int adminID;
	private int userID;
	private String email;
	private String address;

	public Admin(int adminID, int userID, String email, String address) {
		setAdminID(adminID);
		setUserID(userID);
		setEmail(email);
		setAddress(address);
	}
	
	public Admin(int userID) {
		setAdminID(advanceAdminID());
		setUserID(userID);
		setEmail("Not set");
		setAddress("Not set");
	}
	
	public int getAdminID() {
		return adminID;
	}

	public static int advanceAdminID() {
		int id = idNumber;
		idNumber++;
		return id;

	}

	public void setAdminID(int adminID) {
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
	
	public String toCSV() {
		return String.join(",", String.valueOf(this.adminID), String.valueOf(this.userID), this.email, this.address);
	}
}
