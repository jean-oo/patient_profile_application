package ui;

import model.Case;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

// A panel contains search button, and search result
public class SearchPanel extends JPanel implements ActionListener {
    private JLabel filterL;
    private JTextField filterT;
    private MedicalHistoryDisplayPanel medicalHistoryDisplayPanel;
    private JLabel question1;
    private JTextArea history;


    // Constructs a search panel
    // effects: sets the background colour and draws the initial labels;
    //           Search button are to be displayed, able to enter information
    public SearchPanel() {
        setPreferredSize(new Dimension(800, 150));
        setBackground(new Color(233, 233, 233));

        question1 = new JLabel("Dose the patient have this illness before?");

        filterL = new JLabel(" ");
        filterT = new JTextField(5);

        history = new JTextArea(15, 60);
        history.setTabSize(4);
        history.setLineWrap(true);
        history.setWrapStyleWord(true);
        history.setBackground(Color.white);


        medicalHistoryDisplayPanel = new MedicalHistoryDisplayPanel(AddingPanel.pp);
        JButton btn = new JButton("Search");
        btn.setActionCommand("search");
        btn.addActionListener(this);
        add(question1);
        add(filterT);
        add(btn);
        add(filterL);
        add(history);


    }


    // EFFECTS: sets active button to the button panel
    //          called by the framework when the button is clicked

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("search")) {
            sound("./data/MouseClick.wav");
            String result = new String();
            String s = filterT.getText();
            ArrayList<Case> cases = AddingPanel.pp.doseHappenBefore(s);
            if (cases.size() == 0) {
                result = "patient has never had this illness before";
            } else {
                result = medicalHistoryDisplayPanel.allHistory(cases);
            }
            history.setText(result);

        }
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
}