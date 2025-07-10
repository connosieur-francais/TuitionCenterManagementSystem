// Updated AssignTutorSubjectFrame.java with Discord-style UI
package tcms.admin.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import tcms.tutors.Subject;
import tcms.tutors.Tutor;
import tcms.tutors.TutorManager;
import tcms.users.UserManager;
import tcms.utils.Constants;

public class AssignTutorSubjectFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private TutorManager tutorManager;
    private UserManager userManager;
    private Tutor tutor;

    private JLabel assignSubjectLabel;
    private JList<String> subjectList;
    private JList<String> assignedList;
    private JList<String> toBeAssignedList;

    private DefaultListModel<String> availableSubjectsModel = new DefaultListModel<>();
    private DefaultListModel<String> assignedSubjectsModel = new DefaultListModel<>();
    private DefaultListModel<String> toBeAssignedSubjectsModel = new DefaultListModel<>();

    private JRadioButton form1Btn, form2Btn, form3Btn, form4Btn, form5Btn;
    private JButton addBtn, saveBtn, cancelBtn;

    private ManageTutorsPanel manageTutorsPanel;

    public AssignTutorSubjectFrame(TutorManager tm, UserManager um, Tutor t, ManageTutorsPanel mtp) {
        tutorManager = tm;
        userManager = um;
        tutor = t;
        manageTutorsPanel = mtp;

        setTitle("Assign Subjects to Tutor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 500);

        contentPane = new JPanel();
        contentPane.setBackground(Constants.DARK_BUT_NOT_BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        assignSubjectLabel = new JLabel("Assigning Subjects for " + userManager.findUserByUserID(tutor.getUserID()).getUsername());
        assignSubjectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        assignSubjectLabel.setFont(new Font("Arial", Font.BOLD, 24));
        assignSubjectLabel.setForeground(Constants.TEXT_COLOR);
        assignSubjectLabel.setPreferredSize(new Dimension(43, 40));
        contentPane.add(assignSubjectLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(null);
        centerPanel.setBackground(Constants.NOT_QUITE_BLACK);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        ButtonGroup formGroup = new ButtonGroup();

        form1Btn = createFormButton("Form 1", 50);
        form2Btn = createFormButton("Form 2", 160);
        form3Btn = createFormButton("Form 3", 270);
        form4Btn = createFormButton("Form 4", 380);
        form5Btn = createFormButton("Form 5", 490);

        formGroup.add(form1Btn);
        formGroup.add(form2Btn);
        formGroup.add(form3Btn);
        formGroup.add(form4Btn);
        formGroup.add(form5Btn);

        centerPanel.add(form1Btn);
        centerPanel.add(form2Btn);
        centerPanel.add(form3Btn);
        centerPanel.add(form4Btn);
        centerPanel.add(form5Btn);

        subjectList = new JList<>(availableSubjectsModel);
        subjectList.setBackground(Constants.SLATE);
        subjectList.setForeground(Constants.TEXT_COLOR);
        subjectList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scroll1 = new JScrollPane(subjectList);
        scroll1.setBounds(50, 76, 267, 222);
        centerPanel.add(scroll1);

        JLabel subjectListLabel = createListLabel("Available Subjects", 50, 53);
        centerPanel.add(subjectListLabel);

        assignedList = new JList<>(assignedSubjectsModel);
        assignedList.setBackground(Constants.SLATE);
        assignedList.setForeground(Constants.TEXT_COLOR);
        assignedList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scroll2 = new JScrollPane(assignedList);
        scroll2.setBounds(340, 76, 325, 83);
        centerPanel.add(scroll2);

        JLabel assignedSubjectsLbl = createListLabel("Already Assigned", 340, 53);
        centerPanel.add(assignedSubjectsLbl);

        toBeAssignedList = new JList<>(toBeAssignedSubjectsModel);
        toBeAssignedList.setBackground(Constants.SLATE);
        toBeAssignedList.setForeground(Constants.TEXT_COLOR);
        toBeAssignedList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scroll3 = new JScrollPane(toBeAssignedList);
        scroll3.setBounds(340, 192, 325, 106);
        centerPanel.add(scroll3);

        JLabel toBeAssignedLbl = createListLabel("To Be Assigned", 340, 169);
        centerPanel.add(toBeAssignedLbl);

        addBtn = new JButton("Add Selected");
        styleButton(addBtn, Constants.GREEN_BUTTON, Constants.GREEN_BUTTON_HOVER, Constants.GREEN_BUTTON_CLICK);
        addBtn.setBounds(180, 310, 140, 25);
        addBtn.setActionCommand("AddSelected");
        addBtn.addActionListener(this);
        centerPanel.add(addBtn);

        cancelBtn = new JButton("Cancel");
        styleButton(cancelBtn, Constants.RED_BUTTON, Constants.RED_BUTTON_HOVER, Constants.RED_BUTTON_CLICK);
        cancelBtn.setBounds(499, 382, 100, 25);
        cancelBtn.setActionCommand("Cancel");
        cancelBtn.addActionListener(this);
        centerPanel.add(cancelBtn);

        saveBtn = new JButton("Save");
        styleButton(saveBtn, Constants.GREEN_BUTTON, Constants.GREEN_BUTTON_HOVER, Constants.GREEN_BUTTON_CLICK);
        saveBtn.setBounds(611, 382, 100, 25);
        saveBtn.setActionCommand("Save");
        saveBtn.addActionListener(this);
        centerPanel.add(saveBtn);

        preloadAssignedSubjects();

        setVisible(true);
    }

    private void styleButton(JButton button, Color base, Color hover, Color click) {
        button.setBackground(base);
        button.setForeground(Constants.TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(base.darker()));
        button.setFont(new Font("Arial", Font.BOLD, 13));
    }

    private JRadioButton createFormButton(String label, int x) {
        JRadioButton btn = new JRadioButton(label);
        btn.setBounds(x, 17, 100, 21);
        btn.setActionCommand(label);
        btn.setForeground(Constants.TEXT_COLOR);
        btn.setBackground(Constants.NOT_QUITE_BLACK);
        btn.setFont(new Font("Arial", Font.PLAIN, 13));
        btn.addActionListener(this);
        return btn;
    }

    private JLabel createListLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 150, 13);
        lbl.setForeground(Constants.TEXT_COLOR);
        lbl.setFont(new Font("Arial", Font.BOLD, 12));
        return lbl;
    }

    private void loadSubjectsByForm(String form) {
        availableSubjectsModel.clear();
        for (Subject subject : tutorManager.getAllSubjects()) {
            String levelName = tutorManager.findLevelNameByLevelID(subject.getLevelID());
            if (levelName.equalsIgnoreCase(form)) {
                availableSubjectsModel.addElement(subject.getSubjectName());
            }
        }
    }

    private void preloadAssignedSubjects() {
        assignedSubjectsModel.clear();
        for (int subjectId : List.of(tutor.getAssigned_subjectID_1(), tutor.getAssigned_subjectID_2(), tutor.getAssigned_subjectID_3())) {
            Subject subj = tutorManager.getSubjectIDSubjectMap().get(subjectId);
            if (subj != null && subjectId != 0) {
                assignedSubjectsModel.addElement(subj.getSubjectName());
            }
        }
    }

    private void handleSave() {
        List<String> newSubjectNames = Collections.list(toBeAssignedSubjectsModel.elements());
        List<Integer> newSubjectIDs = new ArrayList<>();
        for (String name : newSubjectNames) {
            for (Subject s : tutorManager.getAllSubjects()) {
                if (s.getSubjectName().equalsIgnoreCase(name)) {
                    newSubjectIDs.add(s.getSubjectID());
                    break;
                }
            }
        }

        tutor.setAssigned_subjectID_1(newSubjectIDs.size() > 0 ? newSubjectIDs.get(0) : 0);
        tutor.setAssigned_subjectID_2(newSubjectIDs.size() > 1 ? newSubjectIDs.get(1) : 0);
        tutor.setAssigned_subjectID_3(newSubjectIDs.size() > 2 ? newSubjectIDs.get(2) : 0);

        tutorManager.saveTutors(Constants.TUTORS_CSV);
        JOptionPane.showMessageDialog(this, "Subjects successfully updated!");

        if (manageTutorsPanel != null) {
            manageTutorsPanel.refreshTutorTable();
        }

        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.startsWith("Form ")) {
            loadSubjectsByForm(cmd);
        } else if ("AddSelected".equals(cmd)) {
            List<String> selected = subjectList.getSelectedValuesList();
            for (String subj : selected) {
                if (!toBeAssignedSubjectsModel.contains(subj) && !assignedSubjectsModel.contains(subj)) {
                    toBeAssignedSubjectsModel.addElement(subj);
                }
            }
        } else if ("Save".equals(cmd)) {
            handleSave();
        } else if ("Cancel".equals(cmd)) {
            dispose();
        }
    }
}
