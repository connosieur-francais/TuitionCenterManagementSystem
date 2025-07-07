package tcms.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import tcms.admin.AdminManager;
import tcms.receptionists.ReceptionistManager;
import tcms.students.StudentManager;
import tcms.tutors.TutorManager;
import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;

public class SystemInitializer {

	private static final UserManager userManager = new UserManager();
	private static final AdminManager adminManager = new AdminManager();
	private static final ReceptionistManager receptionistManager = new ReceptionistManager();
	private static final TutorManager tutorManager = new TutorManager();
	private static final StudentManager studentManager = new StudentManager();

	public static class ManagerBundle {
		private final UserManager um;
		private final AdminManager am;
		private final ReceptionistManager rm;
		private final TutorManager tm;
		private final StudentManager sm;

		public ManagerBundle(UserManager userManager, AdminManager adminManager,
				ReceptionistManager receptionistManager, TutorManager tutorManager, StudentManager studentManager) {
			this.um = userManager;
			this.am = adminManager;
			this.rm = receptionistManager;
			this.tm = tutorManager;
			this.sm = studentManager;
		}

		public UserManager getUserManager() {
			return um;
		}

		public AdminManager getAdminManager() {
			return am;
		}

		public ReceptionistManager getReceptionistManager() {
			return rm;
		}

		public TutorManager getTutorManager() {
			return tm;
		}

		public StudentManager getStudentManager() {
			return sm;
		}
	}

	private static final String[] FILES = { 
			Constants.USERS_CSV, 
			Constants.ADMINS_CSV, 
			Constants.TUTORS_CSV,
			Constants.RECEPTIONISTS_CSV, 
			Constants.STUDENTS_CSV, 
			Constants.CLASSES_CSV, 
			Constants.ENROLLMENTS_CSV,
			Constants.LEVELS_CSV, 
			Constants.PAYMENTS_CSV, 
			Constants.SUBJECT_CHANGE_REQUESTS_CSV,
			Constants.SUBJECTS_CSV,
			Constants.RECEIPTS_CSV};

	public static ManagerBundle initialize() {
		for (String filePath : FILES) {
			File file = new File(filePath);
			if (!file.exists()) {
				try {
					Files.createDirectories(file.getParentFile().toPath());
					file.createNewFile();
					System.out.println("System Initializer -> Created file: " + filePath);
				} catch (IOException e) {
					System.err.println("System Initializer -> Failed to create file: " + filePath);
				}
			}
		}

		userManager.loadUsers(Constants.USERS_CSV);
		adminManager.loadAdmins(Constants.ADMINS_CSV);
		tutorManager.loadTutors(Constants.TUTORS_CSV);
		tutorManager.loadSubjects(Constants.SUBJECTS_CSV);
		tutorManager.loadLevels(Constants.LEVELS_CSV);
		receptionistManager.loadReceptionists(Constants.RECEPTIONISTS_CSV);
		studentManager.loadStudents(Constants.STUDENTS_CSV);

		createDefaultAdminIfMissing();

		adminManager.updateAdminsInCSV(userManager);
		tutorManager.updateTutorsInCSV(userManager);
		receptionistManager.updateReceptionistInCSV(userManager);
		studentManager.updateStudentsInCSV(userManager);

		return new ManagerBundle(userManager, adminManager, receptionistManager, tutorManager, studentManager);
	}

	private static void createDefaultAdminIfMissing() {

		var admin_list = adminManager.getAllAdmins();

		if (admin_list.isEmpty() || admin_list.size() == 0) {
			int first_id = userManager.getFirstAvailableUserID();
			User newAdminUser = new User(first_id, "admin", "123", "admin");
			userManager.addUser(newAdminUser);
			userManager.saveUsers(Constants.USERS_CSV);
			System.out.println("System Intializer -> No admins detected!");
			System.out.printf("System Initializer -> Created default admin account.%nUsername: %s%nPassword: %s%n",
					newAdminUser.getUsername(), newAdminUser.getPassword());
		} else {
			System.out.println("System Initializer -> Admin(s) already exist. ");
		}
	}
}
