package persistence;

import org.json.JSONObject;

// some codes below are from JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
