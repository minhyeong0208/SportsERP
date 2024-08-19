package acorn.admin.repository;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import acorn.admin.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer>{
	
	Page<Expense> findAll(Pageable pageable);
	
	// 팀별 일정 기간의 수입 내역 확인 쿼리 메소드
	Page<Expense> findByExpenseDateBetween(Date startDate, Date endDate, Pageable pageable);
	
	// 수입 항목별 필터링 쿼리 메소드
	//List<Expense> findByExpenseItem(String expenseItem);
	
	Expense findById(int id);
}
