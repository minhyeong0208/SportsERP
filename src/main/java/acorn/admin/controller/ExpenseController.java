package acorn.admin.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acorn.admin.dto.ExpenseDto;
import acorn.admin.dto.ExpenseListDto;
import acorn.admin.dto.IncomeDto;
import acorn.admin.repository.ExpenseListRepository;
import acorn.admin.service.ExpenseService;

@RestController
@CrossOrigin("*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseListRepository expenseListRepository;

    // 지출 항목 리스트 조회
    @GetMapping("/expenselist")
    public List<ExpenseListDto> getExpenseList() {
        List<ExpenseListDto> expenseList = expenseListRepository.findAll()
                                            .stream()
                                            .map(ExpenseListDto::toDto)
                                            .toList();
        return expenseList;
    }

    // 전체 지출 내역 조회
    @GetMapping("/expenses")
    public Page<ExpenseDto> getExpenseAll(Pageable pageable) {
        return expenseService.getAllExpenses(pageable);
    }

    // 기간별 지출 내역 조회
    @GetMapping("/expenses/{startDate}/{endDate}")
    public Page<ExpenseDto> getExpensesByDateRange(@PathVariable("startDate") Date startDate,
                                                   @PathVariable("endDate") Date endDate,
                                                   Pageable pageable) {
        return expenseService.getExpensesByDateRange(startDate, endDate, pageable);
    }

    // 지출 항목 검색
    @GetMapping("/expenses/search")
    public Page<ExpenseDto> searchExpenses(@RequestParam("query") String query, Pageable pageable) {
        return expenseService.searchExpenses(query, pageable);
    }

    // 해당 날짜의 지출 데이터 추출
    @GetMapping("/expenses/{date}")
    public List<ExpenseDto> getExpensesByDate(@PathVariable("date") Date date) {
    	return expenseService.getExpensesByDate(date);
    }
    
    // 해당 달의 지출 데이터 추출
    @GetMapping("/expenses/month/{year}/{month}")
    public List<Map<String, Object>> getExpensesByMonth(@PathVariable("year")int year, @PathVariable("month")int month) {
    	return expenseService.getExpensesByMonth(year, month);
    }
    
    // 새로운 지출 추가
    @PostMapping("/expenses")
    public Map<String, Object> insertExpense(@RequestBody ExpenseDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            expenseService.addExpense(dto);
            response.put("status", "success");
            response.put("message", "Expense data added successfully");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to add expense data: " + e.getMessage());
        }
        return response;
    }

    // 지출 수정
    @PutMapping("/expenses/{id}")
    public Map<String, Object> updateExpense(@PathVariable("id") int id, @RequestBody ExpenseDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            expenseService.updateExpense(id, dto);
            response.put("status", "success");
            response.put("message", "Expense data updated successfully");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to update expense data: " + e.getMessage());
        }
        return response;
    }

    // 지출 삭제
    @DeleteMapping("/expenses/{id}")
    public Map<String, Object> deleteExpense(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            expenseService.deleteExpense(id);
            response.put("status", "success");
            response.put("message", "Expense data deleted successfully");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to delete expense data: " + e.getMessage());
        }
        return response;
    }

}
