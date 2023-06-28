package ui;

import model.Case;
import model.Medication;
import model.PatientProfile;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;



 // Represents the panel in which the medical history of patient is displayed.

public class MedicalHistoryDisplayPanel extends JPanel {
    private static final String Patient_Name = "Patient Name: ";
    private static final String Gender = "Patient Gender: ";
    private static final String Age = "Patient Age: ";
    private static final String Medical_History = "Medical History: ";
    private static final String Allergy_Medi = "Allergy Medication: ";

    //private PatientProfile patientProfile;
    private JLabel name;
    private JLabel age;
    private JLabel gender;
    private JLabel medicalHistory;
    private JLabel allergyMedi;
    private JTextArea jta;


    // Constructs a medical history panel
    // EFFECTS: sets the background colour and draws the initial labels;
    //          updates this with the patient profile whose medical history is to be displayed
    public MedicalHistoryDisplayPanel(PatientProfile pp) {


        //setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(189, 190, 189));
        setPreferredSize(new Dimension(440, 500));
        name = new JLabel(Patient_Name + AddingPanel.pp.getName());
        name.setPreferredSize(new Dimension(400, 15));
        age = new JLabel(Age + AddingPanel.pp.getAge(20201115));
        age.setPreferredSize(new Dimension(400, 15));
        gender = new JLabel(Gender + AddingPanel.pp.getGender());
        gender.setPreferredSize(new Dimension(400, 15));
        medicalHistory = new JLabel(Medical_History); //+ "\n" + allHistory(AddingPanel.pp.getMedicalHistory()));
        medicalHistory.setPreferredSize(new Dimension(400, 15));
        allergyMedi = new JLabel(Allergy_Medi + allMediName(AddingPanel.pp));
        allergyMedi.setPreferredSize(new Dimension(400, 15));

        jta = new JTextArea(20, 30);
        jta.setTabSize(4);
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);
        jta.setBackground(Color.white);
        jta.setText(allHistory(AddingPanel.pp.getMedicalHistory()));
        new JScrollPane(jta);


        addingComponent();


    }

    //MODIFIES: this
    //EFFECTS: adding component to panel
    private void addingComponent() {
        add(name);
        add(age);
        add(gender);
        add(allergyMedi);
        add(medicalHistory);
        add(jta);
    }

    //MODIFIES:cases
    //EFFECTS: append Date, Doctor, Hospital, Illness, Details as string of every case
    public String allHistory(ArrayList<Case> cases) {

        StringBuffer sb = new StringBuffer();

        for (Case c : cases) {
            sb.append(c.getDate()).append(",   Doctor: ").append(c.getDoctor()).append(",  Hospital name: ")
                    .append(c.getHospital()).append("\n").append("Illness: ")
                    .append(c.getIllness()).append(",  Detail: ").append(c.getDetails()).append("\n");
        }

        return sb.toString();

    }

    //MODIFIES:pp
    //EFFECTS: append medication name as string of the list of medications
    private String allMediName(PatientProfile pp) {
        ArrayList<Medication> medications = pp.getAllergyMedications();

        StringBuilder sb = new StringBuilder();

        for (Medication m : medications) {
            sb.append(m.getMedicationName()).append("  ");
        }
        return sb.toString();

    }



}

