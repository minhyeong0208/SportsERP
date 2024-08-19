package acorn.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import acorn.admin.entity.IncomeList;


public interface IncomeListRepository extends JpaRepository<IncomeList, Integer>{
	// Name으로 IncomeList를 조회하는 메서드 선언
    IncomeList findByName(String name);
}
