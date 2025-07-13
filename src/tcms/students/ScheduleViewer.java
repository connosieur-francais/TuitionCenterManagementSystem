package tcms.students;

import tcms.tutors.*;
import tcms.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScheduleViewer extends JFrame {
    private int studentID;
    private ScheduleManager scheduleManager;

    public ScheduleViewer(int studentID, ScheduleManager scheduleManager) {
    	getContentPane().setBackground(new Color(34, 34, 34));
        this.studentID = studentID;
        this.scheduleManager = scheduleManager;

        setTitle("Student Class Schedule");
        setSize(972, 574); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null); 

        buildUI();
        setVisible(true);
    }

    private void buildUI() {
        JLabel header = new JLabel("Class Schedule for Student ID: " + studentID);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        header.setForeground(new Color(255, 255, 255)); 
        header.setBounds(30, 20, 500, 30);
        getContentPane().add(header);

        //schedule info
        JPanel schedulePanel = new JPanel();
        schedulePanel.setLayout(null); 
        schedulePanel.setBounds(76, 88, 742, 366); 
        schedulePanel.setBackground(new Color(41, 41, 41)); 
        schedulePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        List<ClassSchedule> schedules = scheduleManager.getScheduleForStudent(studentID);
        int y = 20;

        if (schedules.isEmpty()) {
            JLabel label = new JLabel("No classes scheduled.");
            label.setFont(new Font("Arial", Font.ITALIC, 16));
            label.setForeground(new Color(34, 34, 34));
            label.setBounds(20, y, 500, 25);
            schedulePanel.add(label);
        } else {
            for (ClassSchedule cs : schedules) {
                JLabel label = new JLabel("• " + cs.getDay() + " - " + cs.toString());
                label.setFont(new Font("Arial", Font.PLAIN, 16));
                label.setForeground(new Color(255, 255, 255)); 
                label.setBounds(20, y, 680, 25);
                schedulePanel.add(label);
                y += 35;
            }
        }

        getContentPane().add(schedulePanel); 
    }

    public static void main(String[] args) {
        ScheduleManager sm = new ScheduleManager();
        sm.loadSchedule(Constants.CLASSES_CSV); 
        new ScheduleViewer(2, sm); 
    }
}
