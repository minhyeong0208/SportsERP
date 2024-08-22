package acorn.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import acorn.admin.entity.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Integer>{
	
}
