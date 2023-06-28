package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

// Represents a patient profile having an patient name, gender, birthday date, allergy medication list,
//            and medical history.
public class PatientProfile implements Writable {
    private String name;                                 // the patient name
    private String gender;                               // the patient gender
    private Integer birthday;                            // the patient birthday
    private ArrayList<Medication> allergyMedications;    // the current patient allergy medications list
    private ArrayList<Case> medicalHistory;              // the current patient medical history


    // REQUIRES: birthday is in the form of YYYYMMDD and less than today's date and greater then 0.
    // EFFECTS: name, birthday, gender on PatientProfile is set to nm, birthday, gender respectively;
    //          build list for allergy medications and medical history.

    public PatientProfile(String nm, int birthday, String gender) {
        name = nm;
        this.birthday = birthday; //eg:19990808
        this.gender = gender;
        allergyMedications = new ArrayList<>();
        medicalHistory = new ArrayList<>();


    }


    //REQUIRES: todayDate is in the form of YYYYMMDD, greater than birthday.
    //MODIFIES: this
    //EFFECTS: Today's date subtract birthday date to get age and return age
    public int getAge(int todayDate) {
        Integer age = ((todayDate - birthday) / 10000);

        return age;

    }


    public String getName() {
        return this.name;

    }


    //MODIFIES:this
    //EFFECTS: add an allergy medication to list
    public void addMedication(Medication medi) {
        allergyMedications.add(medi);
    }


    public ArrayList<Medication> getAllergyMedications() {
        return allergyMedications;
    }


    //EFFECTS:return true if the medication on the allergy medication list, otherwise return false.
    public boolean isAllergyToMedication(String medi) {
        for (Medication c : allergyMedications) {
            if (c.getMedicationName().equals(medi)) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES:this
    //EFFECTS:remove the given medication from allergy medication list
    public void removeMedication(Medication medi) {

        allergyMedications.remove(medi);

    }

    //EFFECT: match the given illness in medical history and return the same illness cases happen before
    public ArrayList<Case> doseHappenBefore(String ill) {
        ArrayList<Case> happenCases = new ArrayList<>();
        for (Case c : medicalHistory) {
            if (c.getIllness().equals(ill)) {
                happenCases.add(c);
            }
        }
        return happenCases;

    }

    //MODIFIES: this, c
    //EFFECTS: if c is null, medical history doesn't change, otherwise, if medical history doesn't contain c,
    //         add c to patient's medical history, and add patient profile to c
    public void addCase(Case c) {

        if (c == null) {
            this.medicalHistory = medicalHistory;
        } else {
            if (!medicalHistory.contains(c)) {
                medicalHistory.add(c);
                c.addPatientProfile(this);
            }
        }

    }

    //MODIFIES: this
    //EFFECTS: add present illness to patient profile
    public ArrayList<Case> getMedicalHistory() {
        return medicalHistory;
    }


    public int getBirthday() {
        return this.birthday;
    }


    public String getGender() {
        return this.gender;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("patient name", name);
        json.put("patient gender", gender);
        json.put("patient birthday", birthday);
        json.put("medications", medisToJson());
        json.put("cases", casesToJson());
        return json;
    }

    private JSONArray medisToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Medication m : allergyMedications) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;

    }

    // EFFECTS: returns cases in this medical history as a JSON array
    private JSONArray casesToJson() {

        JSONArray jsonArray = new JSONArray();
        for (Case c : medicalHistory) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PatientProfile that = (PatientProfile) o;
        return name.equals(that.name)
                && gender.equals(that.gender)
                && birthday.equals(that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, birthday);
    }
}
