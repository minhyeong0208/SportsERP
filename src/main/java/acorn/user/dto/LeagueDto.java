package acorn.user.dto;

import acorn.user.entity.League;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeagueDto {

    private int id;
    private String date;
    private String home;
    private String away;
    private String location;

    private String gubun; // 홈/원정 구분
    private int round; // 라운드

    // GWO 기준
    public static LeagueDto toDto(League league){

        // method를 static으로 선언하니가 this.필드명으로 필드 접근 불가능
        // -> method 내에서 변수 새로 선언해서 builder로 넣기

        // 홈/원정 구분
        String gubun = "";
        if(league.getHome().equals("GWO")){
            gubun = "HOME";
        } else if(league.getAway().equals("GWO")){
            gubun = "AWAY";
        }

        // 라운드
        int round = 0; // 초기치 0? 1?
        if((league.getId() % 6) == 0){
            round = league.getId() / 6;
        } else {
            round = (league.getId() / 6) + 1;
        }

        return LeagueDto.builder()
                .id(league.getId())
                .date(league.getDate())
                .home(league.getHome())
                .away(league.getAway())
                .location(league.getLocation())
                .gubun(gubun)
                .round(round)
                .build();
    }

}
