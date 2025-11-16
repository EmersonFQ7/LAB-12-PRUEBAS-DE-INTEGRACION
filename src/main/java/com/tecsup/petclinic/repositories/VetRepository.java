package com.tecsup.petclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.petclinic.entities.Vet;

/**
 * Repository para la entidad Vet
 * 
 * @author jgomezm
 *
 */
@Repository
public interface VetRepository 
	extends JpaRepository<Vet, Integer> {

	// Fetch vets by firstName
	List<Vet> findByFirstName(String firstName);

	// Fetch vets by lastName
	List<Vet> findByLastName(String lastName);

	// Fetch vets by email
	List<Vet> findByEmail(String email);

	// Fetch vets by active status
	List<Vet> findByActive(Boolean active);

	// Fetch all vets
	@Override
	List<Vet> findAll();

}