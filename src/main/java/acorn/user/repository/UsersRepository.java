package acorn.user.repository;

import acorn.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String> {

    //
    Optional<Users> findById(String id);
}
