package ui;

import model.Case;
import model.Medication;
import model.PatientProfile;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// A panel for entering cases information
public class AddingPanel extends JPanel implements ActionListener {

    static PatientProfile pp = new PatientProfile("Jane", 19990716, "Female");

    static {
        AddingPanel.pp.addMedication(new Medication("aspirin"));
    }

    private JTextField illnessT;
    private JTextField dateT;
    private JTextField detailsT;
    private JTextField doctorT;
    private JTextField hospitalT;
    private static final int TEXT_FIELD_WIDTH = 16;
    private JLabel newCaseL = new JLabel("Adding a new case");
    private JLabel illnessL = new JLabel("Enter illness");
    private JLabel dateL = new JLabel("Enter diagnose date");
    private JLabel detailsL = new JLabel("Enter symptom details");
    private JLabel doctorL = new JLabel("Enter diagnose doctor");
    private JLabel hospitalL = new JLabel("Enter diagnose hospital");
    private GridBagConstraints constraints;
    private GridBagLayout gb;
    static Case newCase;


    // Constructs an adding panel
    // effects: sets the background colour and draws the initial labels;
    //           able to enter information and to be displayed
    public AddingPanel() {


        illnessT = new JTextField(TEXT_FIELD_WIDTH);
        dateT = new JTextField(TEXT_FIELD_WIDTH);
        detailsT = new JTextField(TEXT_FIELD_WIDTH);
        doctorT = new JTextField(TEXT_FIELD_WIDTH);
        hospitalT = new JTextField(TEXT_FIELD_WIDTH);

        JButton addingButton = new JButton("Add");
        addingButton.setActionCommand("myButton");
        addingButton.addActionListener(this);

        gb = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(gb);

        setBackground(new Color(189, 190, 189));

        placeDate();
        placeDoctor();
        placeHospital();
        placeIllness();
        placeDetails();

        add(addingButton);


    }

    //EFFECTS: placing the label and text field of detail
    private void placeDetails() {
        //place the details label
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 10, 5, 0);
        gb.setConstraints(detailsL, constraints);
        add(detailsL);

        //place the details text fields
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(0, 0, 5, 10);
        gb.setConstraints(detailsT, constraints);
        add(detailsT);
    }

    //EFFECTS: placing the label and text field of illness
    private void placeIllness() {
        //place the illness label
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 10, 5, 0);
        gb.setConstraints(illnessL, constraints);
        add(illnessL);

        //place the illness text fields
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(0, 0, 5, 10);
        gb.setConstraints(illnessT, constraints);
        add(illnessT);
    }

    //EFFECTS: placing the label and text field of hospital
    private void placeHospital() {
        //place the hospital label
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 10, 5, 0);
        gb.setConstraints(hospitalL, constraints);
        add(hospitalL);

        //place the hospital text fields
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(0, 0, 5, 10);
        gb.setConstraints(hospitalT, constraints);
        add(hospitalT);
    }

    //EFFECTS: placing the label and text field of doctor
    private void placeDoctor() {
        //place the doctor label
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 10, 5, 0);
        gb.setConstraints(doctorT, constraints);
        add(doctorL);

        //place the doctor text fields
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(0, 0, 5, 10);
        gb.setConstraints(doctorT, constraints);
        add(doctorT);
    }

    //EFFECTS: placing the label and text field of date
    private void placeDate() {
        //place the date label
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(dateL, constraints);
        add(dateL);

        //place the date text fields
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(dateT, constraints);
        add(dateT);
    }


    //EFFECTS: play the sound of sourceName
    private void sound(String sourceName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sourceName).getAbsoluteFile());
            Clip c = AudioSystem.getClip();
            c.open(audioInputStream);
            c.start();
        } catch (Exception ex) {
            System.out.println("No sound");

        }
    }

    // MODIFIES: this
    // EFFECTS: sets active button to the button panel
    //          called by the framework when the button is clicked

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            sound("./data/MouseClick.wav");

            newCase = new Case(Integer.parseInt(dateT.getText()), doctorT.getText(), hospitalT.getText(),
                    illnessT.getText(), detailsT.getText());

            pp.addCase(newCase);


            new PatientProfileDisplay();


        }

    }
}
