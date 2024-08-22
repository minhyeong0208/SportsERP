package acorn.admin.dto;

import acorn.admin.entity.Facility;
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
public class FacilityDto {
	private int facility_id;
	private Integer facility_capacity;
	private String facility_name, facility_location, facility_year, facility_image;
	
	public static FacilityDto toDto(Facility facility) {
		return FacilityDto.builder()
				.facility_id(facility.getFacilityId())
				.facility_name(facility.getFacilityName())
				.facility_capacity(facility.getFacilityCapacity())
				.facility_location(facility.getFacilityLocation())
				.facility_year(facility.getFacilityYear())
				.facility_image(facility.getFacilityImage())
				.build();
	}
}
