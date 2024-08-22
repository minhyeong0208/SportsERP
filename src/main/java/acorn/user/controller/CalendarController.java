package acorn.user.controller;

import acorn.user.dto.LeagueDto;
import acorn.user.repository.LeagueRepositoryDao;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")

@RequiredArgsConstructor
public class CalendarController {

    @Autowired
    private LeagueRepositoryDao leagueRepositoryDao;

//    @GetMapping("/league")
//    @ResponseBody
//    public List<LeagueDto> league(){
//        List<LeagueDto> list = leagueRepositoryDao.getLeagueAll()
//                                                    .stream().map(LeagueDto::toDto)
//                                                    .toList();
//        return list;
//    }

    @GetMapping("/league")
    @ResponseBody
    public List<Map<String, Object>> leaguePlan(){
        List<LeagueDto> list = leagueRepositoryDao.getLeagueAll()
                                                    .stream().map(LeagueDto::toDto)
                                                    .toList();

        JSONArray jsonArr = new JSONArray();

        for(LeagueDto dto : list){
            // 꼭 JSONObject로 넘겨야 하는건지...?
            // @ResponseBody 붙이면 JSON 형태로 넘어가는거 아닌감..?
            JSONObject jsonObj = new JSONObject();

            jsonObj.put("title", dto.getRound() + "ROUND");
            jsonObj.put("start", dto.getDate());

            jsonArr.add(jsonObj);
        }
        
        /*
        달력에 표시되면 좋을 것 같은 정보
        - 몇번째 라운드 경기인지
        - 홈/원정 구분
        => LeagueDto에 필드로 추가
        */
        

        return jsonArr;

    }

}
