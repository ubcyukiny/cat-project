package persistance;

import org.json.JSONObject;

// interface for JsonReader and JsonWriter
// cited from JsonSerializationDEMO
public interface Writable {
    //EFFECTS: return this as JSON Object
    JSONObject toJson();
}
