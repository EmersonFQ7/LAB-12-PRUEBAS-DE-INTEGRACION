package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;

import java.util.List;

/**
 * Service interface para Vet
 * 
 * @author jgomezm
 *
 */
public interface VetService {

    /**
     * Crear un nuevo veterinario
     * @param vetDTO
     * @return VetDTO creado
     */
    VetDTO create(VetDTO vetDTO);

    /**
     * Actualizar un veterinario existente
     * @param vetDTO
     * @return VetDTO actualizado
     */
    VetDTO update(VetDTO vetDTO);

    /**
     * Eliminar un veterinario por ID
     * @param id
     * @throws VetNotFoundException
     */
    void delete(Integer id) throws VetNotFoundException;

    /**
     * Buscar veterinario por ID
     * @param id
     * @return VetDTO
     * @throws VetNotFoundException
     */
    VetDTO findById(Integer id) throws VetNotFoundException;

    /**
     * Buscar veterinarios por nombre
     * @param firstName
     * @return Lista de VetDTO
     */
    List<VetDTO> findByFirstName(String firstName);

    /**
     * Buscar veterinarios por apellido
     * @param lastName
     * @return Lista de Vet
     */
    List<Vet> findByLastName(String lastName);

    /**
     * Buscar veterinarios por email
     * @param email
     * @return Lista de Vet
     */
    List<Vet> findByEmail(String email);

    /**
     * Buscar veterinarios por estado activo
     * @param active
     * @return Lista de Vet
     */
    List<Vet> findByActive(Boolean active);

    /**
     * Obtener todos los veterinarios
     * @return Lista de Vet
     */
    List<Vet> findAll();
}