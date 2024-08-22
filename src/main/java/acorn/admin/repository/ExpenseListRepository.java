package acorn.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import acorn.admin.entity.ExpenseList;


public interface ExpenseListRepository extends JpaRepository<ExpenseList, Integer>{
	// Name으로 IncomeList를 조회하는 메서드 선언
	ExpenseList findByName(String name);
}
