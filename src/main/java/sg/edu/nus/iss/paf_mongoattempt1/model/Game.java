package sg.edu.nus.iss.paf_mongoattempt1.model;

public record Game(int gameId, 
                String name, 
                int year, 
                int ranking, 
                int usersRated,
                String url, 
                String thumbnail, 
                Long timestamp) {
    

}
