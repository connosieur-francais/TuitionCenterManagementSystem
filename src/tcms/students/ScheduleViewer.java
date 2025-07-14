package tcms.students;

import tcms.tutors.*;
import tcms.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleViewer extends JFrame {
    private int studentID;
    private ScheduleManager scheduleManager;

    public ScheduleViewer(int studentID, ScheduleManager scheduleManager) {
        this.studentID = studentID;
        this.scheduleManager = scheduleManager;

        setTitle("My Weekly Class Schedule");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(34, 34, 34));
        getContentPane().setLayout(null);

        buildUI();
        setVisible(true);
    }

    private void buildUI() {
        // Header label
        JLabel header = new JLabel("My Weekly Class Schedule");
        header.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 28));
        header.setForeground(Color.WHITE);
        header.setBounds(50, 30, 600, 40);
        getContentPane().add(header);

        
        JPanel scheduleContainer = new JPanel();
        scheduleContainer.setLayout(new BoxLayout(scheduleContainer, BoxLayout.Y_AXIS));
        scheduleContainer.setBackground(new Color(41, 41, 41));

        List<ClassSchedule> schedules = scheduleManager.getScheduleForStudent(studentID);

        if (schedules.isEmpty()) {
            JLabel noData = new JLabel("No classes scheduled.");
            noData.setFont(new Font("Arial", Font.ITALIC, 18));
            noData.setForeground(Color.LIGHT_GRAY);
            noData.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            scheduleContainer.add(noData);
        } else {
            
            Map<String, List<ClassSchedule>> grouped = schedules.stream()
                .collect(Collectors.groupingBy(ClassSchedule::getDay, TreeMap::new, Collectors.toList()));

            
            List<String> orderedDays = Arrays.asList(
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
            );

            for (String day : orderedDays) {
                List<ClassSchedule> dayClasses = grouped.get(day);
                if (dayClasses != null) {
                   
                    JLabel dayHeader = new JLabel(day);
                    dayHeader.setFont(new Font("Arial Black", Font.BOLD, 22));
                    dayHeader.setForeground(new Color(255, 255, 255));
                    dayHeader.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));
                    scheduleContainer.add(dayHeader);

                    for (ClassSchedule cs : dayClasses) {
                        JPanel classCard = new JPanel();
                        classCard.setLayout(new BoxLayout(classCard, BoxLayout.Y_AXIS));
                        classCard.setBackground(new Color(54, 57, 63));
                        classCard.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
                        classCard.setMaximumSize(new Dimension(800, 100));

                        JLabel subject = new JLabel(cs.getSubjectName());
                        subject.setFont(new Font("Arial Black", Font.BOLD, 18));
                        subject.setForeground(Color.WHITE);

                        JLabel details = new JLabel(cs.getTime() + " | " + cs.getLocation() + " | Class ID: " + cs.getClassID());
                        details.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                        details.setForeground(Color.LIGHT_GRAY);
                        details.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

                        classCard.add(subject);
                        classCard.add(details);

                        scheduleContainer.add(classCard);
                        scheduleContainer.add(Box.createRigidArea(new Dimension(0, 10)));
                    }
                }
            }
        }

       
        JScrollPane scrollPane = new JScrollPane(scheduleContainer);
        scrollPane.setBounds(50, 100, 820, 420);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        getContentPane().add(scrollPane);
    }

   
    public static void main(String[] args) {
        ScheduleManager sm = new ScheduleManager();
        sm.loadSchedule(Constants.CLASSES_CSV);
        new ScheduleViewer(2, sm); 
    }
}
