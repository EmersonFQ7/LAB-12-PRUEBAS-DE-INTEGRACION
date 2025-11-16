# Ejercicio 1: Pruebas de Integración - Entidad Vet

## 📋 Descripción

Se implementaron **pruebas de integración** para la entidad **Vet (Veterinario)** del sistema PetClinic, desarrollando un CRUD completo con arquitectura en capas (Controller → Service → Repository → Entity).

## 📦 Archivos Creados

1. `Vet.java` - Entidad JPA (entities)
2. `VetDTO.java` - Data Transfer Object (dtos)
3. `VetNotFoundException.java` - Excepción personalizada (exceptions)
4. `VetMapper.java` - Mapper MapStruct (mapper)
5. `VetRepository.java` - Repository JPA (repositories)
6. `VetService.java` - Interface del servicio (services)
7. `VetServiceImpl.java` - Implementación del servicio (services)
8. `VetController.java` - REST Controller (webs)
9. `VetControllerTest.java` - **Pruebas de integración** (test/webs)
10. `schema-h2.sql` - Script SQL para pruebas (test/resources)

## ✅ Resultados de las Pruebas

**7/7 pruebas ejecutadas exitosamente:**

1. ✅ `testFindAllVets()` - Listar veterinarios
2. ✅ `testFindVetOK()` - Buscar por ID existente
3. ✅ `testFindVetKO()` - Buscar ID inexistente (404)
4. ✅ `testCreateVet()` - Crear veterinario
5. ✅ `testDeleteVet()` - Eliminar veterinario
6. ✅ `testDeleteVetKO()` - Eliminar inexistente (404)
7. ✅ `testUpdateVet()` - Actualizar veterinario

## 🖼️ Evidencia de Ejecución

![Ejecución exitosa de tests en IntelliJ IDEA](evidencias/vet-tests-passed.png)

---

**Tecnologías:** Spring Boot, JPA, H2, Lombok, MapStruct, JUnit 5, MockMvc