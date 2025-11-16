package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.dtos.VetDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Pruebas de integración para VetController
 * 
 * @author jgomezm
 */
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class VetControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test: Obtener todos los veterinarios
     * Verifica que el endpoint GET /vets retorne la lista de veterinarios
     */
    @Test
    public void testFindAllVets() throws Exception {

        final int ID_FIRST_RECORD = 1;

        this.mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));
    }

    /**
     * Test: Obtener un veterinario por ID
     * Verifica que el endpoint GET /vets/{id} retorne un veterinario específico
     */
    @Test
    public void testFindVetOK() throws Exception {

        String FIRST_NAME = "James";
        String LAST_NAME = "Carter";
        String EMAIL = "james.carter@petclinic.com";

        this.mockMvc.perform(get("/vets/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.email", is(EMAIL)));
    }

    /**
     * Test: Obtener un veterinario con ID inexistente
     * Verifica que retorne status 404 cuando el ID no existe
     */
    @Test
    public void testFindVetKO() throws Exception {

        mockMvc.perform(get("/vets/666"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test: Crear un nuevo veterinario
     * Verifica que el endpoint POST /vets cree correctamente un veterinario
     */
    @Test
    public void testCreateVet() throws Exception {

        String FIRST_NAME = "Juan";
        String LAST_NAME = "Pérez";
        String EMAIL = "juan.perez@petclinic.com";
        String PHONE = "987654321";
        Boolean ACTIVE = true;

        VetDTO newVetDTO = VetDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .phone(PHONE)
                .active(ACTIVE)
                .build();

        this.mockMvc.perform(post("/vets")
                        .content(om.writeValueAsString(newVetDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.email", is(EMAIL)))
                .andExpect(jsonPath("$.phone", is(PHONE)))
                .andExpect(jsonPath("$.active", is(ACTIVE)));
    }

    /**
     * Test: Eliminar un veterinario
     * Verifica que el endpoint DELETE /vets/{id} elimine correctamente
     */
    @Test
    public void testDeleteVet() throws Exception {

        String FIRST_NAME = "ToDelete";
        String LAST_NAME = "Vet";
        String EMAIL = "todelete@petclinic.com";
        String PHONE = "111111111";
        Boolean ACTIVE = true;

        VetDTO newVetDTO = VetDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .phone(PHONE)
                .active(ACTIVE)
                .build();

        ResultActions mvcActions = mockMvc.perform(post("/vets")
                        .content(om.writeValueAsString(newVetDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        String response = mvcActions.andReturn().getResponse().getContentAsString();

        Integer id = JsonPath.parse(response).read("$.id");

        mockMvc.perform(delete("/vets/" + id))
                .andExpect(status().isOk());
    }

    /**
     * Test: Eliminar un veterinario inexistente
     * Verifica que retorne error cuando se intenta eliminar un ID que no existe
     */
    @Test
    public void testDeleteVetKO() throws Exception {

        mockMvc.perform(delete("/vets/1000"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test: Actualizar un veterinario
     * Verifica que el endpoint PUT /vets/{id} actualice correctamente
     */
    @Test
    public void testUpdateVet() throws Exception {

        String FIRST_NAME = "Update";
        String LAST_NAME = "Test";
        String EMAIL = "update@petclinic.com";
        String PHONE = "222222222";
        Boolean ACTIVE = true;

        String UP_FIRST_NAME = "Updated";
        String UP_LAST_NAME = "TestUpdated";
        String UP_EMAIL = "updated@petclinic.com";
        String UP_PHONE = "333333333";

        VetDTO newVetDTO = VetDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .phone(PHONE)
                .active(ACTIVE)
                .build();

        // CREATE
        ResultActions mvcActions = mockMvc.perform(post("/vets")
                        .content(om.writeValueAsString(newVetDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        String response = mvcActions.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");

        // UPDATE
        VetDTO upVetDTO = VetDTO.builder()
                .id(id)
                .firstName(UP_FIRST_NAME)
                .lastName(UP_LAST_NAME)
                .email(UP_EMAIL)
                .phone(UP_PHONE)
                .active(ACTIVE)
                .build();

        mockMvc.perform(put("/vets/" + id)
                        .content(om.writeValueAsString(upVetDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // FIND
        mockMvc.perform(get("/vets/" + id))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.firstName", is(UP_FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(UP_LAST_NAME)))
                .andExpect(jsonPath("$.email", is(UP_EMAIL)))
                .andExpect(jsonPath("$.phone", is(UP_PHONE)));

        // DELETE
        mockMvc.perform(delete("/vets/" + id))
                .andExpect(status().isOk());
    }

}