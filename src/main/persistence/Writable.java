package persistence;

import org.json.JSONObject;

public interface Writable {
    // the interface for this class is from workroom demo of CPSC 210

    // EFFECTS: returns this as JsonObject
    JSONObject toJson();
}
