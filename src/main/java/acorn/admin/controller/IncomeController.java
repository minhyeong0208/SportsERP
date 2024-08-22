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

import acorn.admin.dto.IncomeDto;
import acorn.admin.dto.IncomeListDto;
import acorn.admin.repository.IncomeListRepository;
import acorn.admin.service.IncomeService;

@RestController
@CrossOrigin("*")
public class IncomeController {
   
   @Autowired
   private IncomeService incomeService;

   @Autowired
   private IncomeListRepository incomeListRepository;
   
   // 수입 항목 데이터
   @GetMapping("/incomelist")
   public List<IncomeListDto> getIncomeList() {
      List<IncomeListDto> incomeList = incomeListRepository.findAll()
                                 .stream()
                                 .map(IncomeListDto::toDto)
                                 .toList();
       return incomeList;
   }

   // 전체 수입 데이터 반환
   @GetMapping("/incomes")
   public Page<IncomeDto> getIncomeAll(Pageable pageable){
      return incomeService.getAllIncomes(pageable);
   }

   // 해당 기간의 수입 항목 데이터 반환
   @GetMapping("/incomes/{startDate}/{endDate}")
   public Page<IncomeDto> getIncomesByDateRange(@PathVariable("startDate") Date startDate,
           @PathVariable("endDate") Date endDate, Pageable pageable) {
      return incomeService.getIncomesByDateRange(startDate, endDate, pageable);
   }
   
   // 키워드 검색을 통해 추출한 데이터 반환
    @GetMapping("/incomes/search")
    public Page<IncomeDto> searchIncomes(@RequestParam("query") String query, Pageable pageable) {
        return incomeService.searchIncomes(query, pageable);
    }
    
    // 해당 날짜의 수입 데이터 추출
    @GetMapping("/incomes/{date}")
    public List<IncomeDto> getIncomesByDate(@PathVariable("date") Date date) {
       return incomeService.getIncomesByDate(date);
    }
    
    // 한달 수입 데이터 추출
    @GetMapping("/incomes/month/{year}/{month}")
    public List<Map<String, Object>> getIncomesByMonth(@PathVariable("year") int year, @PathVariable("month") int month) {
       return incomeService.getIncomesByMonth(year, month);
    }
    
    // 수입 데이터 추가
   @PostMapping("/incomes")
   public Map<String, Object> insertIncome(@RequestBody IncomeDto dto) {
       Map<String, Object> response = new HashMap<>();
       try {
           incomeService.addIncome(dto);
           response.put("status", "success");
           response.put("message", "Income data added successfully");
       } catch (Exception e) {
           response.put("status", "error");
           response.put("message", "Failed to add income data: " + e.getMessage());
       }
       return response;
   }

   // 데이터 변경
   @PutMapping("/incomes/{id}")
   public Map<String, Object> updateIncome(@PathVariable("id") int id, @RequestBody IncomeDto dto) {
       Map<String, Object> response = new HashMap<>();
       try {
           incomeService.updateIncome(id, dto);
           response.put("status", "success");
           response.put("message", "Income data updated successfully");
       } catch (Exception e) {
           response.put("status", "error");
           response.put("message", "Failed to update income data: " + e.getMessage());
       }
       return response;
   }

   // 데이터 삭제
   @DeleteMapping("/incomes/{id}")
   public Map<String, Object> deleteIncome(@PathVariable("id") int id) {
       Map<String, Object> response = new HashMap<>();
       try {
           incomeService.deleteIncome(id);
           response.put("status", "success");
           response.put("message", "Income data deleted successfully");
       } catch (Exception e) {
           response.put("status", "error");
           response.put("message", "Failed to delete income data: " + e.getMessage());
       }
       return response;
   }
   
}
