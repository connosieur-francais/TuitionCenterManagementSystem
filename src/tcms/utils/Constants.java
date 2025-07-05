package tcms.utils;

import java.awt.Color;

public class Constants {
    // Color Scheme
    public static final Color CANVAS_COLOR = new Color(30, 33, 36);
    public static final Color BLURPLE = new Color(88, 101, 242);
    public static final Color BLURPLE_HOVER = new Color(79, 82, 196);
    public static final Color BLURPLE_CLICK = new Color(60, 69, 165);
    public static final Color RED = new Color(237, 66, 69);
    public static final Color RED_HOVER = new Color(200, 50, 52);
    public static final Color RED_CLICK = new Color(165, 30, 33);
    public static final Color TEXT_COLOR = Color.WHITE;
    public static final Color DARK_GRAY = new Color(43, 45, 49);
    public static final Color NOT_QUITE_BLACK = new Color(35, 39, 42);
    public static final Color DEEP_DARK = new Color(40, 43, 48);
    public static final Color DARK_BUT_NOT_BLACK = new Color(44, 47, 51);
    
    public static final String LOGIN_PAGE_BACKGROUND_FILE = "src/tcms/resources/loginPageBackground.png";
    public static final String RECEPTIONIST_USER_ICON_FILE = "src/tcms/resources/receptionist_user_icon.png";
    public static final String STUDENT_USER_ICON_FILE = "src/tcms/resources/blank_profile.jpg";
    
    public static final String[] IMG_CAROUSEL = {
    	    "src/tcms/resources/carousel_schedule.jpg",
    	    "src/tcms/resources/carousel_briefing.png",
    	    "src/tcms/resources/carousel_enroll.jpg"
    	};
    
    // File paths (relative to root folder or build path)
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
}  
