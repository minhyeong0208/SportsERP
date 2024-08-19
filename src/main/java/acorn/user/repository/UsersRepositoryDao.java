package acorn.user.repository;

import acorn.user.dto.UsersDto;
import acorn.user.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepositoryDao {

    @Autowired
    private UsersRepository usersRepository;

    public Users getUserInfo(String id){
        // usersRepository.findById(id) 리턴 타입 Optional 인데
        // 관련해서 따로 작업해야 할 것 있는지 체크하기
        Users user = usersRepository.findById(id).get();
        return user;
    }

    public void update(UsersDto usersDto){
        usersRepository.save(Users.toentity(usersDto));
    }
}
