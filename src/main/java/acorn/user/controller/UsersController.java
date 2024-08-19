package acorn.user.controller;

import acorn.user.dto.UsersDto;
import acorn.user.repository.UsersRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepositoryDao usersRepositoryDao;

//    @GetMapping("/editPage")
//    public String editPage(){
//        return "editPage";
//    }

    @GetMapping("/userInfo")
    @ResponseBody
    public Map<String, Object> userInfo(@RequestParam(name="id") String id){
        UsersDto dto = UsersDto.toDto(usersRepositoryDao.getUserInfo(id));
        HashMap<String, Object> hash = new HashMap<>();
        hash.put("id", dto.getId());
        hash.put("name", dto.getName());
        hash.put("password", dto.getPassword());
        hash.put("birthDate", dto.getBirthDate());
        hash.put("email", dto.getEmail());
        hash.put("accCrtDate", dto.getAccCrtDate());
        return hash;
    }

    // PUT
    @PutMapping("/updateUserInfo/{id}")
    @ResponseBody
    public Map<String, Object> updateUserInfo(@PathVariable("id") String id,
                                            @RequestBody UsersDto usersdto){
        usersdto.setId(id);
        usersRepositoryDao.update(usersdto);

        return Map.of("isSuccess", true);
    }
}
