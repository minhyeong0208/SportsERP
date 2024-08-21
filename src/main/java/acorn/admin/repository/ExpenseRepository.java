package acorn.admin.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import acorn.admin.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer>{

	Expense findById(int id);

    Page<Expense> findAll(Pageable pageable);

    // 일정 기간의 지출 내역 확인 쿼리 메소드
    Page<Expense> findByExpenseDateBetween(Date startDate, Date endDate, Pageable pageable);

    // 새로운 검색 쿼리 메서드 추가
    Page<Expense> findByExpensePurposeContainingOrExpenseList_NameContaining(String expensePurpose, String expenseItem, Pageable pageable);

    // 특정 날짜의 지출 내역을 가져오는 쿼리
    List<Expense> findByExpenseDate(Date expenseDate);
    
    // 특정 달의 지출 내역을 가져오는 쿼리
    @Query(value = "SELECT el.name AS expense_item_name, SUM(e.expense_amount) AS total_amount " +
            "FROM Expense e " +
            "JOIN ExpenseList el ON e.expense_item = el.id " +
            "WHERE YEAR(e.expense_date) = :year AND MONTH(e.expense_date) = :month " +
            "GROUP BY el.name", nativeQuery = true)
    List<Map<String, Object>> findExpenseByYearAndMonth(@Param("year") int year, @Param("month") int month);


}