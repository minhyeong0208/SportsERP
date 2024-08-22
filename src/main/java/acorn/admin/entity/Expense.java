package acorn.admin.entity;

import java.sql.Date;

import acorn.admin.dto.ExpenseDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
@Entity
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expenseId;
	
	private int expenseAmount;
	private String expensePurpose;
	private Date expenseDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "expense_item", referencedColumnName = "id")
	private ExpenseList expenseList;
	
	public static Expense toEntity(ExpenseDto dto, ExpenseList expenseList) {
		return Expense.builder()
				.expenseAmount(dto.getExpense_amount())
				.expensePurpose(dto.getExpense_purpose())
				.expenseDate(dto.getExpense_date())
				.expenseList(expenseList)
	            .build();
	}
}
