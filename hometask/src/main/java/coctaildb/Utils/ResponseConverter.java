package coctaildb.Utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResponseConverter {

    public static JsonPath converteToJSONPath(Response resp) {
        String response = resp.asString();
        return new JsonPath(response);
    }
}
