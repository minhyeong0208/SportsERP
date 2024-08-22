package acorn.admin.entity;

import acorn.admin.dto.FacilityDto;
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
public class Facility {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int facilityId;
	
	private String facilityName;
	private String facilityLocation;
	private Integer facilityCapacity;
	private String facilityYear;
	private String facilityImage;
	
	public static Facility toEntity(FacilityDto dto) {
		return Facility.builder()
				.facilityName(dto.getFacility_name())
				.facilityLocation(dto.getFacility_location())
				.facilityCapacity(dto.getFacility_capacity())
				.facilityYear(dto.getFacility_year())
				.facilityImage(dto.getFacility_image())
	            .build();
	}
}
