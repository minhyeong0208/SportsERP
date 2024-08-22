package acorn.admin.dto;

import acorn.admin.entity.ExpenseList;
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
public class ExpenseListDto {
	private int id;
	private String name;
	
	public static ExpenseListDto toDto(ExpenseList expenseList) {
		return ExpenseListDto.builder()
				.id(expenseList.getId())
				.name(expenseList.getName())
				.build();
	}
}
