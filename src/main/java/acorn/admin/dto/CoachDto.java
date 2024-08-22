package acorn.admin.dto;

import java.sql.Date;
import acorn.admin.entity.Coach;
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
public class CoachDto {

    private int coachId;
    private String coachImage;
    private String coachName;
    private String coachPosition;
    private String coachCertification;
    private Date contractStartDate;
    private Date contractEndDate;

    public static CoachDto toDto(Coach coach) {
        return CoachDto.builder()
                .coachId(coach.getCoachId())
                .coachImage(coach.getCoachImage())
                .coachName(coach.getCoachName())
                .coachPosition(coach.getCoachPosition())
                .coachCertification(coach.getCoachCertification())
                .contractStartDate(coach.getContractStartDate())
                .contractEndDate(coach.getContractEndDate())
                .build();
    }
}
