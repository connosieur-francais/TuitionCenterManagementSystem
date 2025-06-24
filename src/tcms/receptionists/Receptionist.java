package tcms.receptionists;

public class Receptionist {
	private int receptionistID;
	private int userID;
	private String contact;
	private String email;
	private String address;

	// Constructor
	public Receptionist(int receptionistID, int userID, String contact, String email, String address) {
		setReceptionistID(receptionistID);
		setUserID(userID);
		setContact(contact);
		setEmail(email);
		setAddress(address);
	}
	
	public Receptionist(int receptionistID, int userID) {
		setReceptionistID(receptionistID);
		setUserID(userID);
		setContact("Not set");
		setEmail("Not set");
		setAddress("Not set");
	}

	// Methods
	public int getReceptionistID() {
		return receptionistID;
	}

	public void setReceptionistID(int receptionistID) {
		this.receptionistID = receptionistID;
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
		return String.join(",", String.valueOf(this.receptionistID), String.valueOf(this.userID), this.contact,
				this.email, this.address);
	}
}
