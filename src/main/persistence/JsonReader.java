package persistence;


import model.Case;
import model.Medication;
import model.PatientProfile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// some codes below are from JsonSerializationDemo

// Represents a reader that reads patient profile from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads patient profile from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PatientProfile read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePatientProfile(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses patient profile from JSON object and returns it
    private PatientProfile parsePatientProfile(JSONObject jsonObject) {
        String nm = jsonObject.getString("patient name");
        int birthday = jsonObject.getInt("patient birthday");
        String gender = jsonObject.getString("patient gender");
        PatientProfile pp = new PatientProfile(nm, birthday, gender);
        addCases(pp, jsonObject);
        addMedications(pp,jsonObject);
        return pp;
    }

    // MODIFIES: pp
    // EFFECTS: parses cases from JSON object and adds them to workroom
    private void addCases(PatientProfile pp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cases");
        for (Object json : jsonArray) {
            JSONObject nextCase = (JSONObject) json;
            addCase(pp, nextCase);
        }
    }

    // MODIFIES: pp
    // EFFECTS: parses case from JSON object and adds it to workroom
    private void addCase(PatientProfile pp, JSONObject jsonObject) {
        String illness = jsonObject.getString("illness");
        String details = jsonObject.getString("symptom details");
        String dr = jsonObject.getString("doctor name");
        String hospital = jsonObject.getString("hospital");
        int date = jsonObject.getInt("diagnose date");
        Case c = new Case(date, dr, hospital, illness, details);
        pp.addCase(c);
    }

    // MODIFIES: pp
    // EFFECTS: parses medications from JSON object and adds them to workroom
    private void addMedications(PatientProfile pp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("medications");
        for (Object json : jsonArray) {
            JSONObject nextCase = (JSONObject) json;
            addMedication(pp, nextCase);
        }
    }

    // MODIFIES: pp
    // EFFECTS: parses the medication from JSON object and adds it to workroom
    private void addMedication(PatientProfile pp, JSONObject jsonObject) {
        String medicationName = jsonObject.getString("medication name");
        Medication m = new Medication(medicationName);
        pp.addMedication(m);
    }

}