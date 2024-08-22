package acorn.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import acorn.admin.dto.FacilityDto;
import acorn.admin.service.FacilityService;

@RestController
public class FacilityController {
	
	@Autowired
	private FacilityService facilityService;
	
	@GetMapping("/facilities")
	public Page<FacilityDto> getAllFacilities(Pageable pageable) {
		return facilityService.getAllFacilities(pageable);
	}
}
