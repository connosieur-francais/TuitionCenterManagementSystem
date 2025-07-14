package tcms.students;

import tcms.tutors.ClassSchedule;
import tcms.tutors.ScheduleManager;
import tcms.utils.Constants;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UpcomingClassesPanel extends JPanel {
    private int studentID;
    private ScheduleManager scheduleManager;

    public UpcomingClassesPanel(int studentID, ScheduleManager scheduleManager) {
        this.studentID = studentID;
        this.scheduleManager = scheduleManager;

        setLayout(null);
        setBounds(504, 344, 331, 327);
        setBackground(new Color(37, 37, 37));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(83, 101, 242), 2),
                "Today's Classes",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial Black", Font.BOLD, 16),
                Color.WHITE
        ));

        displayTodayClasses();
    }

    private void displayTodayClasses() {
        removeAll();

        DayOfWeek today = LocalDate.now().getDayOfWeek();
        String dayString = capitalize(today.toString().toLowerCase());

        List<ClassSchedule> allClasses = scheduleManager.getScheduleForStudent(studentID);
        List<ClassSchedule> todayClasses = allClasses.stream()
                .filter(cs -> cs.getDay().equalsIgnoreCase(dayString))
                .collect(Collectors.toList());

        if (todayClasses.isEmpty()) {
            JLabel label = new JLabel("No classes today.");
            label.setFont(new Font("Arial", Font.ITALIC, 14));
            label.setForeground(Color.WHITE);
            label.setBounds(20, 30, 250, 20);
            add(label);
        } else {
            int y = 25;
            for (ClassSchedule cs : todayClasses) {
                JPanel classCard = new JPanel();
                classCard.setLayout(null);
                classCard.setBackground(new Color(51, 51, 51));
                classCard.setBounds(15, y, 290, 60);
                classCard.setBorder(BorderFactory.createLineBorder(new Color(83, 101, 242), 1));

                JLabel subjectLabel = new JLabel(cs.getSubjectName());
                subjectLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
                subjectLabel.setForeground(Color.WHITE);
                subjectLabel.setBounds(10, 5, 270, 20);
                classCard.add(subjectLabel);

                JLabel timeLocLabel = new JLabel("AT" + " " + cs.getTime() + " " + cs.getLocation());
                timeLocLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
                timeLocLabel.setForeground(new Color(200, 200, 200));
                timeLocLabel.setBounds(10, 28, 270, 20);
                classCard.add(timeLocLabel);

                add(classCard);
                y += 70;
            }
        }

        repaint();
        revalidate();
    }

    private String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

  
    public static void main(String[] args) {
        JFrame frame = new JFrame("Upcoming Classes Test");
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ScheduleManager sm = new ScheduleManager();
        sm.loadSchedule(Constants.CLASSES_CSV);

        UpcomingClassesPanel panel = new UpcomingClassesPanel(1, sm); 
        frame.add(panel);

        frame.setVisible(true);
    }
}
