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
import org.springframework.web.bind.annotation.RestController;

import acorn.admin.dto.ExpenseDto;
import acorn.admin.dto.ExpenseListDto;
import acorn.admin.dto.IncomeDto;
import acorn.admin.dto.IncomeListDto;
import acorn.admin.entity.Expense;
import acorn.admin.entity.ExpenseList;
import acorn.admin.entity.Income;
import acorn.admin.entity.IncomeList;
import acorn.admin.repository.ExpenseListRepository;
import acorn.admin.repository.ExpenseRepository;
import acorn.admin.repository.IncomeListRepository;
import acorn.admin.repository.IncomeRepository;

@RestController
@CrossOrigin("*")
public class ExpenseController {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private ExpenseListRepository expenseListRepository;

	@GetMapping("/expenselist")
	public List<ExpenseListDto> getExpenseList() {
		List<ExpenseListDto> expenseList = expenseListRepository.findAll()
											.stream()
											.map(ExpenseListDto::toDto)
											.toList();
	    return expenseList;
	}
	
	@GetMapping("/expenses")
	public Page<ExpenseDto> getExpenseAll(Pageable pageable){
		
		Page<ExpenseDto> expenseList = expenseRepository.findAll(pageable)
				.map(ExpenseDto::toDto);

		return expenseList;
	}
	
	@GetMapping("/expenses/{startDate}/{endDate}")
	public Page<ExpenseDto> getExpensesByDateRange(@PathVariable("startDate") Date startDate,
	        @PathVariable("endDate") Date endDate, Pageable pageable) {
		
		// 리포지토리 메서드를 통해 기간 내 데이터 조회
	    Page<Expense> expenses = expenseRepository.findByExpenseDateBetween(startDate, endDate, pageable);
	    
	    // DTO로 변환
	    Page<ExpenseDto> expenseList = expenses.map(ExpenseDto::toDto);
	    return expenseList;
	}
	
	@PostMapping("/expenses")
	public Map<String, Object> insertExpense(@RequestBody ExpenseDto dto) {

	    // IncomeList 조회
		ExpenseList expenseList = expenseListRepository.findByName(dto.getExpense_item());

	    // DTO를 엔티티로 변환하여 저장
	    Expense expenseEntity = Expense.toEntity(dto, expenseList);
	    expenseRepository.save(expenseEntity);
		
		
	    // 응답 데이터
	    Map<String, Object> response = new HashMap<>();
	    response.put("status", "success");
	    response.put("message", "Expense data added successfully");

	    return response;
	}
	
	@PutMapping("/expenses/{id}")
	public Map<String, Object> updateExpense(@PathVariable("id")int id, @RequestBody ExpenseDto dto) {
		// 응답 데이터
	    Map<String, Object> response = new HashMap<>();
	    
		try {
			// 수정할 데이터 조회
			Expense expenseData = expenseRepository.findById(id);
			
			// ExpenseList 조회 (수정된 항목에 따라 다를 수 있음)
			ExpenseList expenseList = expenseListRepository.findByName(dto.getExpense_item());
	        
	        if (expenseList == null) {
	            response.put("status", "error");
	            response.put("message", "Invalid expense item.");
	            return response;
	        }
	        
	        // DTO 데이터를 기존 엔티티에 적용
	        expenseData.setExpenseAmount(dto.getExpense_amount());
	        expenseData.setExpenseDate(dto.getExpense_date());
	        expenseData.setExpensePurpose(dto.getExpense_purpose());
	        expenseData.setExpenseList(expenseList);
		    
	        // 데이터베이스에 수정된 데이터 저장
	        expenseRepository.save(expenseData);
	        
		    response.put("status", "success");
		    response.put("message", "Expense data updated successfully");
		} catch (Exception e) {
			response.put("status", "error");
		    response.put("message", "Failed to update expense data: " + e.getMessage());
		}

	    return response;
	}
	
	@DeleteMapping("/expenses/{id}")
	public Map<String, Object> deleteExpense(@PathVariable("id")int id) {
		// 응답 데이터
	    Map<String, Object> response = new HashMap<>();
	    
		try {
			// 삭제할 데이터 조회
			Expense expenseData = expenseRepository.findById(id);
			
			// 데이터 삭제
			expenseRepository.delete(expenseData);

	        // 성공 응답 데이터
	        response.put("status", "success");
	        response.put("message", "Expense data deleted successfully");
		} catch (Exception e) {
			response.put("status", "error");
		    response.put("message", "Failed to delete expense data: " + e.getMessage());
		}

	    return response;
	}
	
}
