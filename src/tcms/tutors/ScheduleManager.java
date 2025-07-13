package tcms.tutors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleManager {
    private List<ClassSchedule> allSchedules = new ArrayList<>();

    
    public void loadSchedule(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                String[] fields = line.split(",");

                if (fields.length >= 7) {
                    int studentID = Integer.parseInt(fields[0].trim());
                    int tutorID = Integer.parseInt(fields[1].trim());
                    int classID = Integer.parseInt(fields[2].trim());
                    String subjectName = fields[3].trim();
                    String day = fields[4].trim();
                    String time = fields[5].trim();
                    String location = fields[6].trim();

                    ClassSchedule cs = new ClassSchedule(studentID, tutorID, classID, subjectName, day, time, location);
                    allSchedules.add(cs);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    public List<ClassSchedule> getScheduleForStudent(int studentID) {
        List<ClassSchedule> result = new ArrayList<>();
        for (ClassSchedule cs : allSchedules) {
            if (cs.getStudentID() == studentID) {
                result.add(cs);
            }
        }
        return result;
    }

    
    public List<ClassSchedule> getAllSchedules() {
        return allSchedules;
    }
}
