package tcms.receptionists;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import tcms.users.UserManager;

public class ReceptionistManager {
	UserManager userManager = new UserManager();
	private List<Receptionist> receptionists = new ArrayList<>();
	
	public void loadReceptionists(String filename) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			
			// from src/receptionists.csv
			String line;
			boolean skipHeader = true;
			
			while ((line = br.readLine()) != null ) {
				if (skipHeader) {
					skipHeader = false;
					continue;
				}
				String[] fields = line.split(",");
				
				if (fields.length >=)
			}
		}
	}
}
