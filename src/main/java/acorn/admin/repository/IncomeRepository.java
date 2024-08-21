package acorn.admin.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import acorn.admin.entity.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer>{
	
	Income findById(int id);
	
	Page<Income> findAll(Pageable pageable);
	
	// 팀별 일정 기간의 수입 내역 확인 쿼리 메소드
	Page<Income> findByIncomeDateBetween(Date startDate, Date endDate, Pageable pageable);
	
    // 새로운 검색 쿼리 메서드 추가
    Page<Income> findByIncomePurposeContainingOrIncomeList_NameContaining(String incomePurpose, String incomeItem, Pageable pageable);

    // 특정 날짜의 수입 내역을 가져오는 쿼리
    List<Income> findByIncomeDate(Date incomeDate);
    
    // 특정 달의 수입 내역을 가져오는 쿼리(수입 항목별)
    @Query(value = "SELECT il.name AS income_item_name, SUM(i.income_amount) AS total_amount " +
            "FROM Income i " +
            "JOIN IncomeList il ON i.income_item = il.id " +  // IncomeList 테이블과 조인
            "WHERE YEAR(i.income_date) = :year AND MONTH(i.income_date) = :month " +
            "GROUP BY il.name", nativeQuery = true)
    List<Map<String, Object>> findIncomeByYearAndMonth(@Param("year") int year, @Param("month") int month);





    
}
