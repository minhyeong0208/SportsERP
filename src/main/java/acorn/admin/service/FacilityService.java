package acorn.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import acorn.admin.dto.FacilityDto;
import acorn.admin.repository.FacilityRepository;

@Service
public class FacilityService {
	
	@Autowired
	public FacilityRepository facilityRepository;
	
	public Page<FacilityDto> getAllFacilities(Pageable pageable) {
        return facilityRepository.findAll(pageable).map(FacilityDto::toDto);
    }
}
