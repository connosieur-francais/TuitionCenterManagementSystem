package tcms.utils;

import java.awt.Color;
import java.time.format.DateTimeFormatter;

public class Constants {

	// Color Scheme
	public static final Color CANVAS_COLOR = new Color(30, 33, 36);
	public static final Color BLURPLE = new Color(88, 101, 242);
	public static final Color LIGHT_GREY = new Color(70, 70, 70);
	public static final Color BLURPLE_HOVER = new Color(79, 82, 196);
	public static final Color BLURPLE_CLICK = new Color(60, 69, 165);
	public static final Color RED = new Color(237, 66, 69);
	public static final Color RED_HOVER = new Color(200, 50, 52);
	public static final Color RED_CLICK = new Color(165, 30, 33);
	public static final Color TEXT_COLOR = Color.WHITE;
	public static final Color DARK_GRAY = new Color(43, 45, 49);
	public static final Color SLATE = new Color(66, 69, 73);
	public static final Color NOT_QUITE_BLACK = new Color(35, 39, 42);
	public static final Color DEEP_DARK = new Color(40, 43, 48);
	public static final Color DARK_BUT_NOT_BLACK = new Color(44, 47, 51);
	public static final Color RED_BUTTON = new Color(237, 66, 69); // Base red (Discord red)
	public static final Color RED_BUTTON_HOVER = new Color(200, 50, 52); // Hover red
	public static final Color RED_BUTTON_CLICK = new Color(165, 30, 33); // Click red
	public static final Color RED_BUTTON_BORDER = new Color(110, 20, 25); // Slightly darker border\
	public static final Color GREEN_BUTTON = new Color(70, 200, 112); // Base green (Discord mint)
	public static final Color GREEN_BUTTON_HOVER = new Color(60, 170, 95); // Slightly darker hover
	public static final Color GREEN_BUTTON_CLICK = new Color(50, 140, 80); // Even darker click
	public static final Color GREEN_BUTTON_BORDER = new Color(35, 100, 60); // Deep green for border

	// Image file paths
	public static final String LOGIN_PAGE_BACKGROUND_FILE = "src/tcms/resources/loginPageBackground.png";
	public static final String RECEPTIONIST_USER_ICON_FILE = "src/tcms/resources/receptionist_user_icon.png";
	public static final String TUTOR_USER_ICON_FILE = "src/tcms/resources/tutor_user_icon.png";
	public static final String STUDENT_USER_ICON_FILE = "src/tcms/resources/blank_profile.jpg";

	public static final String[] IMG_CAROUSEL = { "src/tcms/resources/carousel_schedule.jpg",
			"src/tcms/resources/carousel_briefing.png", "src/tcms/resources/carousel_enroll.jpg" };

	// Data File paths (relative to root folder or build path)
	public static final String USERS_CSV = "src/tcms/data/users.csv";
	public static final String RECEPTIONISTS_CSV = "src/tcms/data/receptionists.csv";
	public static final String TUTORS_CSV = "src/tcms/data/tutors.csv";
	public static final String STUDENTS_CSV = "src/tcms/data/students.csv";
	public static final String ADMINS_CSV = "src/tcms/data/admins.csv";
	public static final String PAYMENTS_CSV = "src/tcms/data/payments.csv";
	public static final String CLASSES_CSV = "src/tcms/data/classes.csv";
	public static final String ENROLLMENTS_CSV = "src/tcms/data/enrollments.csv";
	public static final String LEVELS_CSV = "src/tcms/data/levels.csv";
	public static final String SUBJECT_CHANGE_REQUESTS_CSV = "src/tcms/data/subject_change_requests.csv";
	public static final String SUBJECTS_CSV = "src/tcms/data/subjects.csv";
	public static final String RECEIPTS_CSV = "src/tcms/data/receipts.csv";

	// CSV File Headers
	public static final String USERS_CSV_HEADER = "user_id,username,password,role,login_attempts,status\n";
	public static final String RECEPTIONISTS_CSV_HEADER = "receptionist_id,user_id,contact,email,address\n";
	public static final String TUTORS_CSV_HEADER = "tutor_id,user_id,contact,email,address,assigned_level,assigned_subjectID_1,assigned_subjectID_2,assigned_subjectID_3\n";
	public static final String STUDENTS_CSV_HEADER = "student_id,user_id,contact,email,address,level,enrollment_date\n";
	public static final String ADMINS_CSV_HEADER = "admin_id,user_id,contact,email,address\n";
	public static final String PAYMENTS_CSV_HEADER = "payment_id,student_id,subject_id,amount_paid,payment_date,receipt_id\n";

	// CSV DATE FORMAT
	public static final DateTimeFormatter CSV_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
}