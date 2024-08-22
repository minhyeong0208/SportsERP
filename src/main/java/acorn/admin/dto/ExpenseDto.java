package acorn.admin.dto;

import java.sql.Date;

import acorn.admin.entity.Expense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {
	private int expense_id, expense_amount;
	private String expense_item, expense_purpose;
	private Date expense_date;
	
	public static ExpenseDto toDto(Expense expense) {
		return ExpenseDto.builder()
				.expense_id(expense.getExpenseId())
				.expense_amount(expense.getExpenseAmount())
				.expense_item(expense.getExpenseList().getName())
				.expense_purpose(expense.getExpensePurpose())
				.expense_date(expense.getExpenseDate())
				.build();
	}
	
}
