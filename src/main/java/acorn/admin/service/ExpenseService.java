package acorn.admin.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import acorn.admin.dto.ExpenseDto;
import acorn.admin.dto.IncomeDto;
import acorn.admin.entity.Expense;
import acorn.admin.entity.ExpenseList;
import acorn.admin.repository.ExpenseListRepository;
import acorn.admin.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseListRepository expenseListRepository;

    public Page<ExpenseDto> getAllExpenses(Pageable pageable) {
        return expenseRepository.findAll(pageable).map(ExpenseDto::toDto);
    }

    public Page<ExpenseDto> getExpensesByDateRange(Date startDate, Date endDate, Pageable pageable) {
        return expenseRepository.findByExpenseDateBetween(startDate, endDate, pageable)
                                 .map(ExpenseDto::toDto);
    }

    public Page<ExpenseDto> searchExpenses(String query, Pageable pageable) {
        return expenseRepository.findByExpensePurposeContainingOrExpenseList_NameContaining(query, query, pageable)
                                .map(ExpenseDto::toDto);
    }

    public List<ExpenseDto> getExpensesByDate(Date date) {
    	return expenseRepository.findByExpenseDate(date).stream().map(ExpenseDto::toDto).toList();
    }
    
    public List<Map<String, Object>> getExpensesByMonth(int year, int month) {
    	return expenseRepository.findExpenseByYearAndMonth(year, month);
    }
    
    public void addExpense(ExpenseDto dto) {
        ExpenseList expenseList = expenseListRepository.findByName(dto.getExpense_item());
        Expense expense = Expense.toEntity(dto, expenseList);
        expenseRepository.save(expense);
    }

    public void updateExpense(int id, ExpenseDto dto) {
        Expense expense = expenseRepository.findById(id);
        ExpenseList expenseList = expenseListRepository.findByName(dto.getExpense_item());
        expense.setExpenseAmount(dto.getExpense_amount());
        expense.setExpenseDate(dto.getExpense_date());
        expense.setExpensePurpose(dto.getExpense_purpose());
        expense.setExpenseList(expenseList);
        expenseRepository.save(expense);
    }

    public void deleteExpense(int id) {
        Expense expense = expenseRepository.findById(id);
        expenseRepository.delete(expense);
    }
}
