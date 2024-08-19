package acorn.admin.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "incomelist")
public class IncomeList {
	@Id
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "incomeList", fetch = FetchType.EAGER)
	private List<Income> incomes;
	
}
