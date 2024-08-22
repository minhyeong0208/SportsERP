package acorn.admin.repository;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import acorn.admin.entity.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer>{
	
	Page<Income> findAll(Pageable pageable);
	
	// 팀별 일정 기간의 수입 내역 확인 쿼리 메소드
	Page<Income> findByIncomeDateBetween(Date startDate, Date endDate, Pageable pageable);
	
	// 수입 항목별 필터링 쿼리 메소드
	//List<Income> findByIncomeItem(String incomeItem);
	
	Income findById(int id);
}
