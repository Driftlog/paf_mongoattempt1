package sg.edu.nus.iss.paf_mongoattempt1.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class GameUtil {
    
    public static JsonObject toJsonObject(Document document) {
            return Json.createObjectBuilder()
                    .add("game_id", document.getInteger("gid"))
                    .add("name", document.getString("name"))
                    .build();
    }

    public static JsonObject toJsonList(List<Document> documents, int offset, int limit, long count) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        documents.stream()
            .map(doc -> toJsonObject(doc))
            .forEach(doc -> jsonArrayBuilder.add(doc));
            JsonObject jsonObject = Json.createObjectBuilder()
                                    .add("games", jsonArrayBuilder.build())
                                    .add("offset", offset)
                                    .add("limit", limit)
                                    .add("total", count)
                                    .add("timestamp", new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()))
                                    .build();
            return jsonObject;
    }

    public static JsonObject toGame(Document document) {
            return Json.createObjectBuilder()
                    .add("game_id", document.getInteger("gid"))
                    .add("name", document.getString("name"))
                    .add("year", document.getInteger("year"))
                    .add("users_rated", document.getInteger("users_rated"))
                    .add("url", document.getString("url"))
                    .add("thumbnail", document.getString("image"))
                    .add("timestamp", new Date().getTime())
                    .build();

    }

   
}
