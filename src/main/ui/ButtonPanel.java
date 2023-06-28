package ui;

import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// A panel contains View, Save, Load button
public class ButtonPanel extends JPanel implements ActionListener {
    private JLabel saveL;
    private JLabel loadL;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
   // private SearchPanel searchPanel;

    // Constructs a button panel
    // effects: sets the background colour and draws the initial labels;
    //           View, Save, Load button are to be displayed
    public ButtonPanel() {
        setBackground(new Color(104, 104, 104));
        setPreferredSize(new Dimension(800, 50));

        //searchPanel = new SearchPanel();

        jsonWriter = new JsonWriter("./data/GUIPatientProfile.json");
        jsonReader = new JsonReader("./data/GUIPatientProfile.json");

        JButton view = new JButton("View");
        view.setActionCommand("view");
        view.addActionListener(this);
        JButton save = new JButton("Save");
        save.setActionCommand("save");
        save.addActionListener(this);
        JButton load = new JButton("Load");
        load.setActionCommand("load");
        load.addActionListener(this);
        saveL = new JLabel(" ");
        loadL = new JLabel(" ");
        add(save);
        add(saveL);
        add(load);
        add(loadL);
        add(view);
    }

    // EFFECTS: sets active button to the button panel
    //          called by the framework when the button is clicked

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("view")) {
            //medicalHistory = new JLabel(Medical_History + allHistory(new AddingPanel().pp.getMedicalHistory()));
            sound("./data/MouseClick.wav");
            new PatientProfileDisplay();
            // allergyMedi = new JLabel(Allergy_Medi + allMediName(AddingPanel.pp));
        }

        if (e.getActionCommand().equals("save")) {
            sound("./data/MouseClick.wav");
            save();
        }

        if (e.getActionCommand().equals("load")) {
            sound("./data/MouseClick.wav");
            loadFile();
        }

    }


    // EFFECTS: loads patient profile from file
    private void loadFile() {
        try {
            AddingPanel.pp = jsonReader.read();
            loadL.setText("Loaded! ");
        } catch (IOException e) {
            loadL.setText("Unable to read from file: " + "./data/GUIPatientProfile.json");
        }

    }


    // EFFECTS: saves the patient profile to file
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(AddingPanel.pp);
            jsonWriter.close();
            saveL.setText("Saved! ");
        } catch (FileNotFoundException e) {
            saveL.setText("Unable to write to file: " + "./data/GUIPatientProfile.json");
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
