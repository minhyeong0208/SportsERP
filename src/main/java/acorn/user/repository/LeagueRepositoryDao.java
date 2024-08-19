package acorn.user.repository;

import acorn.user.entity.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeagueRepositoryDao {

    @Autowired
    private LeagueRepository leagueRepository;

    public List<League> getLeagueAll(){
        List<League> list = leagueRepository.findAll();
        return list;
    }
}
