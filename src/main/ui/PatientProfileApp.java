package ui;


import model.Case;
import model.Medication;
import model.PatientProfile;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// some codes below are from JsonSerializationDemo

// Patient profile application
public class PatientProfileApp {
    private static final String JSON_STORE = "./data/patientprofile.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private PatientProfile pro;
    private Case case1;
   /* private Case c1;
    private Case c2;
    private Case c3;*/
    private Medication m1;
    private Medication m2;

    private Scanner input;

    // EFFECTS: runs the patient profile application
    public PatientProfileApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        pro = new PatientProfile("Jean", 19990716, "f");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPatientProfile();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPatientProfile() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("qui")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nTake care!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("add")) {
            addCase();
        } else if (command.equals("vmh")) {
            showingMedicalHistory();
        } else if (command.equals("rmh")) {
            showingCases();
        } else if (command.equals("rag")) {
            doseAllergyToThisMedication();
        } else if (command.equals("sav")) {
            savePatientProfile();
        } else if (command.equals("loa")) {
            loadPatientProfile();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts

    private void init() {

        m1 = new Medication("penicillin");
        m2 = new Medication("aspirin");
        pro.addMedication(m1);
        pro.addMedication(m2);

        input = new Scanner(System.in);
    }



    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nPlease select from:");
        System.out.println("\tadd -> add present illness");
        System.out.println("\tvmh -> view medical history");
        System.out.println("\trmh -> retrieve a illness from medical history");
        System.out.println("\trag -> retrieve a medication from allergy medication");
        System.out.println("\tsav -> save patient profile to file");
        System.out.println("\tloa -> load patient profile to file");
        System.out.println("\tqui -> quit");

    }



    // MODIFIES: this
    // EFFECTS: adding a case to patient profile
    private void addCase() {

        System.out.print("Enter the date (in the form of YYYYMMDD):");
        String s = input.next();
        int date = Integer.parseInt(s);
        System.out.print("Enter doctor name:");
        String dr = input.next();
        System.out.print("Enter hospital name:");
        String clinic = input.next();
        System.out.print("Enter the illnes:");
        String illness = input.next();
        System.out.print("Enter details:");
        String details = input.next();

        case1 = new Case(date, dr, clinic, illness, details);
        pro.addCase(case1);

        printCases(pro.getMedicalHistory());


    }

    // MODIFIES: this
    // EFFECTS:  displaying medical history
    private void showingMedicalHistory() {
        printCases(pro.getMedicalHistory());

    }

    // MODIFIES: this
    // EFFECTS: conducts user input and displaying related medical history
    private void showingCases() {

        System.out.println("Enter the illness to check has patient had it before:");
        String s = input.next();
        List<Case> cases = pro.doseHappenBefore(s);
        if (cases.size() == 0) {
            System.out.println("patient has never had this illness before");
        } else {
            printCases(pro.doseHappenBefore(s));
        }


    }

    // EFFECTS: conducts user input and print "yes" if allergy medication list has the same
    //          medication as input, otherwise print "no".
    private void doseAllergyToThisMedication() {
        System.out.println("Enter the medication that you want to know if patient allergy to it:");
        String s = input.next();

        if (pro.isAllergyToMedication(s)) {
            System.out.println("yes, patient is allergic to it");
        } else {
            System.out.println("no, patient isn't allergic to it");
        }

    }

    // EFFECTS: saves the patient profile to file
    private void savePatientProfile() {
        try {
            jsonWriter.open();
            jsonWriter.write(pro);
            jsonWriter.close();
            System.out.println("Saved " + pro.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads patient profile from file
    private void loadPatientProfile() {
        try {
            pro = jsonReader.read();
            System.out.println("Loaded " + pro.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // EFFECTS: prints doctor's name, hospital, illness, details of each case to the screen
    private void printCases(ArrayList<Case> cases) {
        System.out.println("patient name:" + pro.getName());
        for (Case s : cases) {
            System.out.println(s.getDate() + ", Doctor:" + s.getDoctor() + ", " + s.getHospital());
            System.out.println("Illness:" + s.getIllness());
            System.out.println("Detail:" + s.getDetails());

        }

    }


}
