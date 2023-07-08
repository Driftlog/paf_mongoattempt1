package sg.edu.nus.iss.paf_mongoattempt1.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.paf_mongoattempt1.model.Game;
import sg.edu.nus.iss.paf_mongoattempt1.service.GameService;

@RestController
@RequestMapping
public class GamesController {

    @Autowired
    private GameService svc;

    @GetMapping(produces = "application/json",
                path="/games")
    public JsonObject BrowseGames(@RequestParam(name="offset", defaultValue = "0" ) int offset, 
                            @RequestParam(name="limit", defaultValue = "25") int limit ) {
                                List<Document> games = svc.getGames(limit, offset);
                                JsonObject jsonObject = Json.createObjectBuilder()
                                    .add("games", games.toString())
                                    .add("offset", offset)
                                    .add("limit", limit)
                                    .add("total", games.size())
                                    .add("timestamp", new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()))
                                    .build();

                                System.out.println(jsonObject.getInt("limit"));
                                return jsonObject;
    }

    @GetMapping(path = "/games/rank",
                produces="application/json")
    public JsonObject BrowseGameByRank(@RequestParam(name="offset", defaultValue = "0" ) int offset, 
                            @RequestParam(name="limit", defaultValue = "25") int limit ) {
                            List<Document> games = svc.getGamesRanked(limit, offset);
                                JsonObject jsonObject = Json.createObjectBuilder()
                                    .add("games", games.toString())
                                    .add("offset", offset)
                                    .add("limit", limit)
                                    .add("total", games.size())
                                    .add("timestamp", new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()))
                                    .build();
                                return jsonObject;
    }

    @GetMapping(path = "/game/{gameId}")
    public JsonObject GetGameById(@PathVariable int gameId) {
        Optional<Document> game = svc.getGameById(gameId);
        
        if (game.isPresent()) {
            JsonObject jsonObject = Json.createObjectBuilder()
                                    .add("game", game.toString())
                                    .build();

            return jsonObject;
        }

        JsonObject jsonObject = Json.createObjectBuilder()
                                .addNull("game")
                                .build();

        return jsonObject;
    }

}
