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

import acorn.admin.dto.IncomeDto;
import acorn.admin.dto.IncomeListDto;
import acorn.admin.entity.Income;
import acorn.admin.entity.IncomeList;
import acorn.admin.repository.IncomeListRepository;
import acorn.admin.repository.IncomeRepository;

@RestController
@CrossOrigin("*")
public class IncomeController {
	
	@Autowired
	private IncomeRepository incomeRepository;
	
	@Autowired
	private IncomeListRepository incomeListRepository;

	@GetMapping("/incomelist")
	public List<IncomeListDto> getIncomeList() {
		List<IncomeListDto> incomeList = incomeListRepository.findAll()
											.stream()
											.map(IncomeListDto::toDto)
											.toList();
	    return incomeList;
	}
	
	@GetMapping("/incomes")
	public Page<IncomeDto> getIncomeAll(Pageable pageable){
		
		Page<IncomeDto> incomeList = incomeRepository.findAll(pageable)
				.map(IncomeDto::toDto);

		return incomeList;
	}
	
	@GetMapping("/incomes/{startDate}/{endDate}")
	public Page<IncomeDto> getIncomesByDateRange(@PathVariable("startDate") Date startDate,
	        @PathVariable("endDate") Date endDate, Pageable pageable) {
		
		// 리포지토리 메서드를 통해 기간 내 데이터 조회
	    Page<Income> incomes = incomeRepository.findByIncomeDateBetween(startDate, endDate, pageable);
	    
	    // DTO로 변환
	    Page<IncomeDto> incomeList = incomes.map(IncomeDto::toDto);
	    return incomeList;
	}
	
	@PostMapping("/incomes")
	public Map<String, Object> insertIncome(@RequestBody IncomeDto dto) {

	    // IncomeList 조회
	    IncomeList incomeList = incomeListRepository.findByName(dto.getIncome_item());

	    // DTO를 엔티티로 변환하여 저장
	    Income incomeEntity = Income.toEntity(dto, incomeList);
	    incomeRepository.save(incomeEntity);
		
		
	    // 응답 데이터
	    Map<String, Object> response = new HashMap<>();
	    response.put("status", "success");
	    response.put("message", "Income data added successfully");

	    return response;
	}
	
	@PutMapping("/incomes/{id}")
	public Map<String, Object> updateIncome(@PathVariable("id")int id, @RequestBody IncomeDto dto) {
		// 응답 데이터
	    Map<String, Object> response = new HashMap<>();
	    
		try {
			// 수정할 데이터 조회
			Income incomeData = incomeRepository.findById(id);
			
			// IncomeList 조회 (수정된 항목에 따라 다를 수 있음)
	        IncomeList incomeList = incomeListRepository.findByName(dto.getIncome_item());
	        
	        if (incomeList == null) {
	            response.put("status", "error");
	            response.put("message", "Invalid income item.");
	            return response;
	        }
	        
	        // DTO 데이터를 기존 엔티티에 적용
	        incomeData.setIncomeAmount(dto.getIncome_amount());
	        incomeData.setIncomeDate(dto.getIncome_date());
	        incomeData.setIncomePurpose(dto.getIncome_purpose());
	        incomeData.setIncomeList(incomeList);
		    
	        // 데이터베이스에 수정된 데이터 저장
	        incomeRepository.save(incomeData);
	        
		    response.put("status", "success");
		    response.put("message", "Income data updated successfully");
		} catch (Exception e) {
			response.put("status", "error");
		    response.put("message", "Failed to update income data: " + e.getMessage());
		}

	    return response;
	}
	
	@DeleteMapping("/incomes/{id}")
	public Map<String, Object> deleteIncome(@PathVariable("id")int id) {
		// 응답 데이터
	    Map<String, Object> response = new HashMap<>();
	    
		try {
			// 삭제할 데이터 조회
			Income incomeData = incomeRepository.findById(id);
			
			// 데이터 삭제
	        incomeRepository.delete(incomeData);

	        // 성공 응답 데이터
	        response.put("status", "success");
	        response.put("message", "Income data deleted successfully");
		} catch (Exception e) {
			response.put("status", "error");
		    response.put("message", "Failed to delete income data: " + e.getMessage());
		}

	    return response;
	}
	
}
