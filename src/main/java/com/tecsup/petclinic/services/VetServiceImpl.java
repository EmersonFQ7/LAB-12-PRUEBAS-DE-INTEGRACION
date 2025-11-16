package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import com.tecsup.petclinic.mapper.VetMapper;
import com.tecsup.petclinic.repositories.VetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de Vet
 * 
 * @author jgomezm
 *
 */
@Service
@Slf4j
public class VetServiceImpl implements VetService {

    VetRepository vetRepository;
    VetMapper vetMapper;

    public VetServiceImpl(VetRepository vetRepository, VetMapper vetMapper) {
        this.vetRepository = vetRepository;
        this.vetMapper = vetMapper;
    }

    /**
     * Crear un nuevo veterinario
     * @param vetDTO
     * @return VetDTO creado
     */
    @Override
    public VetDTO create(VetDTO vetDTO) {

        Vet newVet = vetRepository.save(vetMapper.mapToEntity(vetDTO));

        return vetMapper.mapToDto(newVet);
    }

    /**
     * Actualizar un veterinario existente
     * @param vetDTO
     * @return VetDTO actualizado
     */
    @Override
    public VetDTO update(VetDTO vetDTO) {

        Vet updatedVet = vetRepository.save(vetMapper.mapToEntity(vetDTO));

        return vetMapper.mapToDto(updatedVet);
    }

    /**
     * Eliminar un veterinario por ID
     * @param id
     * @throws VetNotFoundException
     */
    @Override
    public void delete(Integer id) throws VetNotFoundException {

        VetDTO vet = findById(id);

        vetRepository.delete(this.vetMapper.mapToEntity(vet));
    }

    /**
     * Buscar veterinario por ID
     * @param id
     * @return VetDTO
     * @throws VetNotFoundException
     */
    @Override
    public VetDTO findById(Integer id) throws VetNotFoundException {

        Optional<Vet> vet = vetRepository.findById(id);

        if (!vet.isPresent())
            throw new VetNotFoundException("Record not found...!");

        return this.vetMapper.mapToDto(vet.get());
    }

    /**
     * Buscar veterinarios por nombre
     * @param firstName
     * @return Lista de VetDTO
     */
    @Override
    public List<VetDTO> findByFirstName(String firstName) {

        List<Vet> vets = vetRepository.findByFirstName(firstName);

        vets.forEach(vet -> log.info("" + vet));

        return vets
                .stream()
                .map(this.vetMapper::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Buscar veterinarios por apellido
     * @param lastName
     * @return Lista de Vet
     */
    @Override
    public List<Vet> findByLastName(String lastName) {

        List<Vet> vets = vetRepository.findByLastName(lastName);

        vets.forEach(vet -> log.info("" + vet));

        return vets;
    }

    /**
     * Buscar veterinarios por email
     * @param email
     * @return Lista de Vet
     */
    @Override
    public List<Vet> findByEmail(String email) {

        List<Vet> vets = vetRepository.findByEmail(email);

        vets.forEach(vet -> log.info("" + vet));

        return vets;
    }

    /**
     * Buscar veterinarios por estado activo
     * @param active
     * @return Lista de Vet
     */
    @Override
    public List<Vet> findByActive(Boolean active) {

        List<Vet> vets = vetRepository.findByActive(active);

        vets.forEach(vet -> log.info("" + vet));

        return vets;
    }

    /**
     * Obtener todos los veterinarios
     * @return Lista de Vet
     */
    @Override
    public List<Vet> findAll() {
        
        return vetRepository.findAll();
    }
}