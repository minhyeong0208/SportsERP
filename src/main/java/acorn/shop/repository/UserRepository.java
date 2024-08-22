package acorn.shop.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import acorn.shop.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByCustomerId(String customerId);
}
