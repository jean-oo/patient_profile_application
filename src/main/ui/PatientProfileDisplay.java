package ui;

import model.PatientProfile;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;



 //Represents the main window in which the Patient Profile
public class PatientProfileDisplay extends JFrame {

    private MedicalHistoryDisplayPanel medicalHistoryDisplayPanel;
    private AddingPanel addingPanel;
    private PatientProfile patientProfile;
    private ButtonPanel buttonPanel;
    private SearchPanel searchPanel;

    // Constructs main window
    // EFFECTS: sets up window in which Patient Profile will be played
    public PatientProfileDisplay() {
        super("Patient Profile");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 700));
        setBackground(new Color(189, 190, 189));
        medicalHistoryDisplayPanel = new MedicalHistoryDisplayPanel(patientProfile);
        addingPanel = new AddingPanel();
        buttonPanel = new ButtonPanel();
        searchPanel = new SearchPanel();
        // ((JPanel) getContentPane()).setBorder(new EmptyBorder(90, 13, 13, 13));
        add(addingPanel, BorderLayout.WEST);
        add(medicalHistoryDisplayPanel, BorderLayout.EAST);
        add(buttonPanel,BorderLayout.SOUTH);
        add(searchPanel,BorderLayout.NORTH);
        this.pack();



        pack();
        centreOnScreen();
        this.setVisible(true);





        setResizable(false);
    }


    // Centres frame on desktop
    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }




// show the patient profile
    public static void main(String[] args) {
        new PatientProfileDisplay();
    }
}
