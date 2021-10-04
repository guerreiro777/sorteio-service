package br.com.bb.sorteio.sorteioservice.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtil {

    public static JsonObject stringToJson(final String strJson) {
       return new Gson().fromJson(strJson, JsonObject.class);
    }

    public static String objectToJson(Object object) {
        return new Gson().toJson(object);
    }
}
