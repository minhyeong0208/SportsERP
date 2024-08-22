package acorn.admin.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import acorn.admin.dto.IncomeDto;
import acorn.admin.entity.Income;
import acorn.admin.entity.IncomeList;
import acorn.admin.repository.IncomeListRepository;
import acorn.admin.repository.IncomeRepository;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private IncomeListRepository incomeListRepository;

    // 모든 수입 데이터 가져오기
    public Page<IncomeDto> getAllIncomes(Pageable pageable) {
        return incomeRepository.findAll(pageable).map(IncomeDto::toDto);
    }

    // 기간 내의 수입 데이터 가져오기
    public Page<IncomeDto> getIncomesByDateRange(Date startDate, Date endDate, Pageable pageable) {
        return incomeRepository.findByIncomeDateBetween(startDate, endDate, pageable).map(IncomeDto::toDto);
    }

    // 검색 기능 추가
    public Page<IncomeDto> searchIncomes(String query, Pageable pageable) {
        Page<Income> incomes = incomeRepository.findByIncomePurposeContainingOrIncomeList_NameContaining(query, query, pageable);
        return incomes.map(IncomeDto::toDto);
    }
    
    // 하루 수입 데이터 추출
    public List<IncomeDto> getIncomesByDate(Date date) {
    	return incomeRepository.findByIncomeDate(date).stream().map(IncomeDto::toDto).toList();
    }
    
    // 한 달 수입 데이터 추출
    public List<Map<String, Object>> getIncomesByMonth(int year, int month) {
    	return incomeRepository.findIncomeByYearAndMonth(year, month);
    }
    
    // 수입 데이터 추가
    public Income addIncome(IncomeDto dto) {
        IncomeList incomeList = incomeListRepository.findByName(dto.getIncome_item());
        Income income = Income.toEntity(dto, incomeList);
        return incomeRepository.save(income);
    }

    // 수입 데이터 수정
    public Income updateIncome(int id, IncomeDto dto) throws Exception {
        Income incomeData = incomeRepository.findById(id);
        if (incomeData == null) {
            throw new Exception("Income not found");
        }

        IncomeList incomeList = incomeListRepository.findByName(dto.getIncome_item());
        if (incomeList == null) {
            throw new Exception("Invalid income item");
        }

        incomeData.setIncomeAmount(dto.getIncome_amount());
        incomeData.setIncomeDate(dto.getIncome_date());
        incomeData.setIncomePurpose(dto.getIncome_purpose());
        incomeData.setIncomeList(incomeList);

        return incomeRepository.save(incomeData);
    }

    // 수입 데이터 삭제
    public void deleteIncome(int id) throws Exception {
        Income incomeData = incomeRepository.findById(id);
        if (incomeData == null) {
            throw new Exception("Income not found");
        }

        incomeRepository.delete(incomeData);
    }
}
