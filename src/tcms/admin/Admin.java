package tcms.admin;

public class Admin {
	
	private int adminID;
	private int userID;
	private String contact;
	private String email;
	private String address;

	public Admin(int adminID, int userID, String contact, String email, String address) {
		setAdminID(adminID);
		setUserID(userID);
		setContact(contact);
		setEmail(email);
		setAddress(address);
	}
	
	public Admin(int adminID, int userID) {
		setAdminID(adminID);
		setUserID(userID);
		setContact("Not set");
		setEmail("Not set");
		setAddress("Not set");
	}
	
	public int getAdminID() {
		return adminID;
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
	
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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
		return String.join(",", String.valueOf(this.adminID), String.valueOf(this.userID), this.contact, this.email, this.address);
	}
}
