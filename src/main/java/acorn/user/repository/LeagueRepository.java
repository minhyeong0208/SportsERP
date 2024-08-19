package acorn.user.repository;

import acorn.user.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;

import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Integer> {

    /*
    @Meta(comment = "")
    if you use hibernate.use_sql_comments to show which JPQL Query caused which SQL Query
    you can optionally use Spring Data JPAs Meta annotation to set a custom comment for your JPQL Query
    */
    @Meta(comment = "findAll method")
    List<League> findAll();

}
