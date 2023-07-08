package sg.edu.nus.iss.paf_mongoattempt1.service;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_mongoattempt1.repo.GamesRepo;

@Service
public class GameService {
    
    @Autowired
    private GamesRepo repo;

    public List<Document> getGames(int limit, int offset) {
        List<Document> games = repo.getGames(offset, limit);
        return games;
    }

    public List<Document> getGamesRanked(int limit, int offset) {
        List<Document> games = repo.getGamesRanked(offset, limit);
        return games;
    }

     public Optional<Document> getGameById(int gameId) {
        Optional<Document> game = repo.getGameById(gameId);
        return game;
    }

    public Long getCount() {
        return repo.getCount();
    }
}
