package acorn.admin.dto;

import java.sql.Date;

import acorn.admin.entity.Income;
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
public class IncomeDto {
	private int income_id, income_amount;
	private String income_item, income_purpose;
	private Date income_date;
	
	public static IncomeDto toDto(Income income) {
		return IncomeDto.builder()
				.income_id(income.getIncomeId())
				.income_amount(income.getIncomeAmount())
				.income_item(income.getIncomeList().getName())
				.income_purpose(income.getIncomePurpose())
				.income_date(income.getIncomeDate())
				.build();
	}
	
}
