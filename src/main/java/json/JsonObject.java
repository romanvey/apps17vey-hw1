package json;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    Map<String, Json> jsonPairs;
    public JsonObject(JsonPair... jsonPairs) {
        this.jsonPairs = new HashMap<>();
        for(JsonPair pair: jsonPairs) {
            this.jsonPairs.put(pair.key, pair.value);
        }
    }

    public JsonObject(ArrayList<JsonPair> jsonPairs) {
        this.jsonPairs = new HashMap<>();
        for(JsonPair pair: jsonPairs) {
            this.jsonPairs.put(pair.key, pair.value);
        }
    }

    @Override
    public String toJson() {
        return "{" + getJsonObjBody() + "}";
    }

    private String getJsonObjBody() {
        String jsonStr = "";
        if (jsonPairs.size() == 0){
            return jsonStr;
        }
        int i = 0;
        for(String key : jsonPairs.keySet()){
            String JsonKey = new JsonString(key).toJson();
            String JsonValue = jsonPairs.get(key).toJson();
            jsonStr += JsonKey + ": " + JsonValue;
            if (i == jsonPairs.size() - 1){
                continue;
            }
            jsonStr += ", ";
            i += 1;
        }
        return jsonStr;
    }

    public void add(JsonPair jsonPair) {
        jsonPairs.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return jsonPairs.get(name);
    }

    public JsonObject projection(String... names) {
        ArrayList<JsonPair> result = new ArrayList<>();
        for(String name: names) {
            Json value = this.find(name);
            if(value != null){
                JsonPair tmp = new JsonPair(name, value);
                result.add(tmp);
            }
        }
        return new JsonObject(result);
    }
}
