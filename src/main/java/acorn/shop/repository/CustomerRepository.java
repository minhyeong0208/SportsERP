package acorn.shop.repository;

import acorn.shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    // 추가적인 쿼리 메서드를 정의할 수 있습니다.
}
