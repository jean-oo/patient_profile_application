package model;

import org.json.JSONObject;
import persistence.Writable;



// Represents a medication having medication name
public class Medication implements Writable {

    public String medi;


    // EFFECTS: medi on Medication is set to medi

    public Medication(String medi) {
        this.medi = medi;
    }


    public String getMedicationName() {
        return this.medi;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("medication name", medi);
        return json;
    }
}
