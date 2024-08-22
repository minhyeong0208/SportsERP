package acorn.admin.entity;

import java.sql.Date;

import acorn.admin.dto.IncomeDto;
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
public class Income {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int incomeId;
   
   private int incomeAmount;
   private String incomePurpose;
   private Date incomeDate;
   
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "income_item", referencedColumnName = "id")
   private IncomeList incomeList;
   
   public static Income toEntity(IncomeDto dto, IncomeList incomeList) {
      return Income.builder()
            .incomeAmount(dto.getIncome_amount())
            .incomePurpose(dto.getIncome_purpose())
            .incomeDate(dto.getIncome_date())
            .incomeList(incomeList)
               .build();
   }
}
