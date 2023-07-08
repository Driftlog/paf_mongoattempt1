package sg.edu.nus.iss.paf_mongoattempt1.repo;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_mongoattempt1.model.Game;

@Repository
public class GamesRepo {
    
    @Autowired
    private MongoTemplate template;

    //db.games.find().limit(25).skip(5)
    public List<Document> getGames(int offset, int limit){
        Criteria criteria1 = new Criteria();
        Query query = Query.query(criteria1).limit(limit).skip(offset);
        query.fields()
            .include("name");
        List<Document> games = template.find(query, Document.class, "games");
        return games;
    }

    public List<Document> getGamesRanked(int offset, int limit) {
        Criteria criteria2 = new Criteria().where("ranking").exists(true);
        Query query = Query.query(criteria2).with(Sort.by(Direction.DESC, "ranking")).limit(limit).skip(offset);
        query.fields()
            .include("name");
        List<Document> games = template.find(query, Document.class, "games");
        System.out.println(games);
        return games;

    }

    public Optional<Document> getGameById(int gameId) {
        Criteria criteria3 = new Criteria().where("gid").is(gameId);
        Query query = Query.query(criteria3);
        List<Document> game = template.find(query, Document.class, "games");
        if (game.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(game.get(0));

    }

}
