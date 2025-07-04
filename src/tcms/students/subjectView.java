package tcms.students;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class subjectView {
	
	    
	    public static String getSubjectByStudentId(int studentID) {
	        String filePath = "src/subjects.csv";  
	        String line;

	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	           
	            br.readLine();

	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length >= 2) {
	                    int id = Integer.parseInt(parts[0].trim());
	                    if (id == studentID) {
	                        return parts[1].trim();
	                    }
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading subjects.csv: " + e.getMessage());
	        } catch (NumberFormatException e) {
	            System.err.println("Invalid student ID format in CSV: " + e.getMessage());
	        }

	        return "Subject not found";
	    }
	}



