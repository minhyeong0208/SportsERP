package acorn.admin.dto;

import acorn.admin.entity.IncomeList;
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
public class IncomeListDto {
	private int id;
	private String name;
	
	public static IncomeListDto toDto(IncomeList incomeList) {
		return IncomeListDto.builder()
				.id(incomeList.getId())
				.name(incomeList.getName())	
				.build();
	}
}
