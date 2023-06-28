package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Represents a case having an illness, diagnose date, symptom details, diagnose doctor, diagnose hospital
public class Case implements Writable {
    private String illness;
    private Integer date;
    private String details;
    private String doctor;
    private String hospital;
    private Set<PatientProfile> patients;

    // REQUIRES: date is in the form of YYYYMMDD and  greater then 0.
    // EFFECTS: illness, doctor, hospital, illness, details on Case is set to date, dr, hospital, details respectively;

    public Case(Integer date, String dr, String hospital, String illness, String details) {
        this.illness = illness;
        this.date = date;
        this.details = details;
        doctor = dr;
        this.hospital = hospital;
        patients = new HashSet<>();
    }


    public String getIllness() {
        return this.illness;
    }


    public String getDetails() {
        return this.details;
    }


    public int getDate() {
        return this.date;
    }


    public String getDoctor() {
        return this.doctor;
    }


    public String getHospital() {
        return this.hospital;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("doctor name", doctor);
        json.put("illness", illness);
        json.put("diagnose date", date);
        json.put("symptom details", details);
        json.put("hospital", hospital);
        return json;
    }

    //MODIFIES: this, pp
    //EFFECTS: if pp is null, list of patients doesn't change, otherwise, if the list of patients doesn't contain pp,
    //         add pp to the list, and add the case to pp
    public void addPatientProfile(PatientProfile pp) {
        if (pp == null) {
            this.patients = patients;
        } else {
            if (!patients.contains(pp)) {
                patients.add(pp);
                pp.addCase(this);

            }
        }
    }

    public Set<PatientProfile> getPatients() {
        return patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Case newCase = (Case) o;
        return illness.equals(newCase.illness)
                && date.equals(newCase.date)
                && details.equals(newCase.details)
                && doctor.equals(newCase.doctor)
                && hospital.equals(newCase.hospital);

    }

    @Override
    public int hashCode() {
        return Objects.hash(illness, date, details, doctor, hospital);
    }



}
