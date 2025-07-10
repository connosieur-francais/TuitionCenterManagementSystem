package tcms.students;

import tcms.utils.Constants;

import java.io.*;
import java.util.*;

public class SubjectChangeRequestManager {
    private List<SubjectChangeRequest> requests = new ArrayList<>();

    public void loadRequests(String filename) {
        requests.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean skipHeader = true;
            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                String[] parts = line.split(",", -1);
                if (parts.length >= 4) {
                    int id = Integer.parseInt(parts[0]);
                    int studentId = Integer.parseInt(parts[1]);
                    String subject = parts[2];
                    String reason = parts[3];
                    requests.add(new SubjectChangeRequest(id, studentId, subject, reason));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveRequests(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("request_id,student_id,subject,reason\n");
            for (SubjectChangeRequest req : requests) {
                bw.write(String.join(",", String.valueOf(req.getRequestId()), String.valueOf(req.getStudentId()),
                        req.getNewSubject(), req.getReason()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRequest(SubjectChangeRequest req) {
        requests.add(req);
    }

    public void deleteRequestById(int id) {
        requests.removeIf(r -> r.getRequestId() == id);
    }

    public int getNextRequestId() {
        return requests.stream().mapToInt(SubjectChangeRequest::getRequestId).max().orElse(0) + 1;
    }

    public List<SubjectChangeRequest> getRequestsByStudentId(int studentId) {
        List<SubjectChangeRequest> result = new ArrayList<>();
        for (SubjectChangeRequest req : requests) {
            if (req.getStudentId() == studentId) {
                result.add(req);
            }
        }
        return result;
    }
}
