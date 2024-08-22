package acorn.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import acorn.admin.entity.Coach;

public interface CoachRepository extends JpaRepository<Coach, Integer> {

}
