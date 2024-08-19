package acorn.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class League {

    @Id
    @Column(name="league_id")
    private int id;

    @Column(name="league_date")
    private String date;

    @Column(name="league_home")
    private String home;

    @Column(name="league_away")
    private String away;

    @Column(name="league_location")
    private String location;

}
