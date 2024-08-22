package acorn.admin.entity;

import java.sql.Date;
import acorn.admin.dto.CoachDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coachId;

    private String coachImage;
    private String coachName;
    private String coachPosition;
    private String coachCertification;
    private Date contractStartDate;
    private Date contractEndDate;

    public static Coach toEntity(CoachDto coachDto) {
        return Coach.builder()
                .coachId(coachDto.getCoachId())
                .coachImage(coachDto.getCoachImage())
                .coachName(coachDto.getCoachName())
                .coachPosition(coachDto.getCoachPosition())
                .coachCertification(coachDto.getCoachCertification())
                .contractStartDate(coachDto.getContractStartDate())
                .contractEndDate(coachDto.getContractEndDate())
                .build();
    }

    public void updateFromDto(CoachDto coachDto) {
        this.coachName = coachDto.getCoachName();
        this.coachPosition = coachDto.getCoachPosition();
        this.coachCertification = coachDto.getCoachCertification();
        this.contractStartDate = coachDto.getContractStartDate();
        this.contractEndDate = coachDto.getContractEndDate();
    }
}
