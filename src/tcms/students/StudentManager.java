package tcms.students;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import tcms.users.User;
import tcms.users.UserManager;

public class StudentManager {
	
	private int fieldLength = 7;
	private static UserManager userManager;
	private Map<Integer, Student> userIDStudentMap = new HashMap<>();
	private Map<Integer, Student> studentIDStudentMap = new HashMap<>();
	private List<Student> students = new ArrayList<>();

	public void loadStudents(String filename) {
		students.clear();

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
					int studentID = Integer.parseInt(fields[0].trim());
					int userID = Integer.parseInt(fields[1].trim());
					String contact = fields[2].trim();
					String email = fields[3].trim();
					String address = fields[4].trim();
					int level = Integer.parseInt(fields[5].trim());
					String enrollmentDate = fields[6].trim();

					Student student = new Student(studentID, userID, email, contact, address, level, enrollmentDate);
					students.add(student);
					userIDStudentMap.put(userID, student);
					studentIDStudentMap.put(studentID, student);

					System.out.println("Loaded student: " + student);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveStudents(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write("student_id,user_id,contact,email,address,level,enrollment_date\n");
			for (Student student : students) {
				bw.write(String.join(",",
					String.valueOf(student.getStudentID()),
					String.valueOf(student.getUserID()),
					student.getContact(),
					student.getEmail(),
					student.getAddress(),
					String.valueOf(student.getLevel()),
					student.getEnrollment_date()
				));
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateStudentsInCSV(UserManager um) {
		userManager = um;
		List<User> users = userManager.getAllUsers();
		System.out.println("updateStudentsInCSV: Updating students in students.csv");

		Set<Integer> existingStudentUserIDs = new HashSet<>();
		for (Student student : students) {
			existingStudentUserIDs.add(student.getUserID());
		}

		boolean updated = false;

		for (User user : users) {
			if (user.getRole().equalsIgnoreCase("student") && !existingStudentUserIDs.contains(user.getID())) {
				int newStudentID = nextAvailableStudentID();
				Student newStudent = new Student(newStudentID, user.getID());
				students.add(newStudent);
				userIDStudentMap.put(user.getID(), newStudent);
				studentIDStudentMap.put(newStudentID, newStudent);
				System.out.println("Added new student: " + user.getUsername());
				updated = true;
			} else {
				System.out.println("Student already exists: " + user.getUsername());
			}
		}

		Iterator<Student> iterator = students.iterator();
		while (iterator.hasNext()) {
			Student student = iterator.next();
			User user = userManager.findUserByUserID(student.getUserID());
			if (user == null || !user.getRole().equalsIgnoreCase("student")) {
				System.out.println("Removed student (invalid user or role): " + (user != null ? user.getUsername() : "Unknown"));
				iterator.remove();
				updated = true;
			}
		}

		if (updated) {
			students.sort(Comparator.comparingInt(Student::getStudentID));
			saveStudents("src/students.csv");
		}
	}

	public int nextAvailableStudentID() {
		Set<Integer> usedIDs = new HashSet<>();
		for (Student student : students) {
			usedIDs.add(student.getStudentID());
		}

		int id = 1;
		while (usedIDs.contains(id)) {
			id++;
		}
		return id;
	}

	public Student findStudentByUserID(int userID) {
		return userIDStudentMap.get(userID);
	}

	public Student findStudentByStudentID(int studentID) {
		return studentIDStudentMap.get(studentID);
	}

	public Map<Integer, Student> getUserIDStudentMap() {
		return userIDStudentMap;
	}

	public Map<Integer, Student> getStudentIDStudentMap() {
		return studentIDStudentMap;
	}

	public List<Student> getAllStudents() {
		return students;
	}

	public boolean isValidEmail(String email) {
		if (email == null || email.trim().isEmpty()) return false;
		int at = email.indexOf('@');
		int dot = email.lastIndexOf('.');
		return at > 0 && dot > at + 1 && dot < email.length() - 1;
	}

	public static boolean isValidContact(String contact) {
		String contactRegex = "^01[0-46-9]-\\d{3}-\\d{4}$";
		return Pattern.matches(contactRegex, contact);
	}
	

	
	}

	

