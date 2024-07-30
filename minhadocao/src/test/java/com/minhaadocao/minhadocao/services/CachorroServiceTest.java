package com.minhaadocao.minhadocao.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;



import com.minhaadocao.minhadocao.models.entities.Cachorro;
import com.minhaadocao.minhadocao.models.entities.Especie;
import com.minhaadocao.minhadocao.models.entities.RacaCachorro;
import com.minhaadocao.minhadocao.models.entities.StatusAnimal;
import com.minhaadocao.minhadocao.models.repositories.CachorroRepository;
import com.minhaadocao.minhadocao.models.requestDTO.CachorroRequestDTO;
import com.minhaadocao.minhadocao.models.responseDTO.CachorroResponseDTO;
import com.minhaadocao.minhadocao.models.services.CachorroService;

@ExtendWith(MockitoExtension.class)
public class CachorroServiceTest {

	@Mock
	private CachorroRepository repository;
	
	@InjectMocks
	private CachorroService service;
	
	private CachorroRequestDTO cachorroRight;
	
	private CachorroRequestDTO cachorroWrong;
	
	
	@BeforeEach
	public void setup() {
		
		cachorroRight = new CachorroRequestDTO(
        "Rex", 
        Especie.CACHORRO,
        4, 
        LocalDate.of(2020, 5, 10), 
        StatusAnimal.DISPONIVEL, 
        "Cachorro amigável e bem treinado.",
        "http://example.com/imagem_rex.jpg", 
        "Carlos Silva", 
        "12345678901", 
        "carlos.silva@gmail.com", 
        RacaCachorro.LABRADOR );
		
		
		cachorroWrong = new CachorroRequestDTO(
		        "Rex", 
		        Especie.CACHORRO,
		        -1, 
		        LocalDate.of(2025, 5, 10), 
		        StatusAnimal.DISPONIVEL, 
		        "",
		        "", 
		        "", 
		        "", 
		        "", 
		        RacaCachorro.LABRADOR );
		
		
	}
	
	
	@Test
	@DisplayName("Given valid Cachorro data, when save Cachorro then Return Cachorro JSON")
	public void testSaveCachorroSuccess() {
		
		//Given
	    Cachorro cachorro = new Cachorro(cachorroRight);
	    
	    //When
	    when(repository.save(any(Cachorro.class))).thenReturn(cachorro);
	    
	    Cachorro result = service.save(cachorroRight);

	    //Then
	    assertNotNull(result);
	    assertEquals("Rex", result.getNome());
	    assertEquals(StatusAnimal.DISPONIVEL, result.getStatus());
	    verify(repository, times(1)).save(any(Cachorro.class));
	}

	@Test
	@DisplayName("Given invalid Cachorro data, when save Cachorro then Return Exception")
	public void testSaveCachorroFailure() {
		//When
	    when(repository.save(any(Cachorro.class))).thenThrow(new RuntimeException("Erro ao salvar"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.save(cachorroWrong);
	    });

	    //Then
	    assertEquals("Erro ao salvar cachorro", thrown.getMessage());
	}
	
	@Test
	@DisplayName("Given valid Cachorro data, when update Cachorro then Return Cachorro updated JSON")
	public void testUpdateCachorroSuccess() {
	    //Given
		Cachorro cachorroExistente = new Cachorro();
	    cachorroExistente.setId(1L);
	    Cachorro cachorroAtualizado = new Cachorro(cachorroRight);
	    cachorroAtualizado.setId(1L);

	    //When
	    when(repository.findById(1L)).thenReturn(Optional.of(cachorroExistente));
	    when(repository.save(any(Cachorro.class))).thenReturn(cachorroAtualizado);

	    Cachorro result = service.update(cachorroRight, 1L);

	    
	    //Then
	    assertNotNull(result);
	    assertEquals(1L, result.getId());
	    verify(repository, times(1)).findById(1L);
	    verify(repository, times(1)).save(any(Cachorro.class));
	}
	
	@Test
	@DisplayName("Given invalid Cachorro id, when try update Cachorro then Return Exception")
	public void testUpdateCachorroFailure() {
	    //When
		when(repository.findById(1L)).thenThrow(new RuntimeException("Erro ao buscar cachorro"));	    

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.update(cachorroWrong, 1L);
	    });

	    //Then
	    assertEquals("Erro ao atualizar cachorro", thrown.getMessage());
	}
	
	
	@Test
	@DisplayName("Given valid Cachorro id and Status, update status Cachorro then Return Cachorro updated")
	public void testUpdateStatusSuccess() {
	    //Given
		Cachorro cachorro = new Cachorro();
	    cachorro.setId(1L);
	    cachorro.setStatus(StatusAnimal.DISPONIVEL);

	    //When
	    when(repository.findById(1L)).thenReturn(Optional.of(cachorro));
	    when(repository.save(any(Cachorro.class))).thenReturn(cachorro);

	    Cachorro result = service.updateStatus(StatusAnimal.ADOTADO, 1L);
	    
	    
	    //Then
	    assertNotNull(result);
	    assertEquals(StatusAnimal.ADOTADO, result.getStatus());
	    verify(repository, times(1)).findById(1L);
	    verify(repository, times(1)).save(any(Cachorro.class));
	}
	
	@Test
	@DisplayName("Given invalid Data, when try update Cachorro then Return Exception")
	public void testUpdateStatusFailure() {
		//When
		when(repository.findById(1L)).thenThrow(new RuntimeException("Erro ao buscar cachorro"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.updateStatus(StatusAnimal.ADOTADO, 1L);
	    });

	    //Then
	    assertEquals("Erro ao atualizar cachorro", thrown.getMessage());
	}
	
	
	@Test
	@DisplayName("Given de ID, then find the Cachorro data that correspond the ID")
	public void testFindByIdSuccess() {
	    //Given
		Cachorro cachorro = new Cachorro();
	    cachorro.setId(1L);

	    //When
	    when(repository.findById(1L)).thenReturn(Optional.of(cachorro));

	    Cachorro result = service.findById(1L);

	    //Then
	    assertNotNull(result);
	    assertEquals(1L, result.getId());
	    verify(repository, times(1)).findById(1L);
	}
	
	
	@Test
	@DisplayName("Given de ID, then throws an Exception when try to find Cachorro data")
	public void testFindByIdFailure() {
	    //When
		when(repository.findById(1L)).thenThrow(new RuntimeException("Erro ao buscar cachorro"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.findById(1L);
	    });

	    //Then
	    assertEquals("Erro ao buscar cachorro por ID: 1", thrown.getMessage());
	}
	
	@Test
	@DisplayName("Test that list all data from Cachorro table and return the list ")
	public void testListAllSuccess() {
	    //Given
		Cachorro cachorro = new Cachorro();
	    cachorro.setId(1L);

	    //When
	    when(repository.findAll()).thenReturn(List.of(cachorro));

	    List<CachorroResponseDTO> result = service.listAll();

	    //Then
	    assertNotNull(result);
	    assertEquals(1, result.size());
	    verify(repository, times(1)).findAll();
	}
	
	@Test
	@DisplayName("Test that list all data from Cachorro table but return an Exception ")
	public void testListAllFailure() {
		//When
	    when(repository.findAll()).thenThrow(new RuntimeException("Erro ao listar cachorros"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.listAll();
	    });

	    //Then
	    assertEquals("Erro ao listar todos os cachorros", thrown.getMessage());
	}
	
	@Test
	@DisplayName("Test that list all data from Cachorro table filter by Raca and return the list ")
	public void testFindByRacaSuccess() {
	    Cachorro cachorro = new Cachorro();
	    cachorro.setRaca(RacaCachorro.LABRADOR);

	    when(repository.findByRaca(RacaCachorro.LABRADOR)).thenReturn(List.of(cachorro));
	    List<CachorroResponseDTO> responseList = List.of(new CachorroResponseDTO(cachorro));

	    List<CachorroResponseDTO> result = service.findByRaca(RacaCachorro.LABRADOR);

	    assertNotNull(result);
	    assertEquals(1, result.size());
	    verify(repository, times(1)).findByRaca(RacaCachorro.LABRADOR);
	}
	
	
	@Test
	@DisplayName("Test that list all data from Cachorro table filter by Raca but return an Exception ")
	public void testFindByRacaFailure() {
		//When
	    when(repository.findByRaca(RacaCachorro.LABRADOR)).thenThrow(new RuntimeException("Erro ao buscar cachorros por raça"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.findByRaca(RacaCachorro.LABRADOR);
	    });

	    //Then
	    assertEquals("Erro ao buscar cachorros por raça: LABRADOR", thrown.getMessage());
	}
	
	
	@Test
	@DisplayName("Test that list all data from Cachorro table filter by date and return the list ")
	public void testFindByDataNascimentoSuccess() {
		//Given
	    Cachorro cachorro = new Cachorro();
	    cachorro.setDataNascimento(LocalDate.of(2020, 5, 10));

	    //When
	    when(repository.findByDataNascimento(LocalDate.of(2020, 5, 10))).thenReturn(List.of(cachorro));
	    List<CachorroResponseDTO> responseList = List.of(new CachorroResponseDTO(cachorro));

	    List<CachorroResponseDTO> result = service.findByDataNascimento(LocalDate.of(2020, 5, 10));

	    //Then
	    assertNotNull(result);
	    assertEquals(1, result.size());
	    verify(repository, times(1)).findByDataNascimento(LocalDate.of(2020, 5, 10));
	}
	
	
	@Test
	@DisplayName("Test that list all data from Cachorro table filter by date but return an Exception ")
	public void testFindByDataNascimentoFailure() {
		//When
	    when(repository.findByDataNascimento(LocalDate.of(2020, 5, 10))).thenThrow(new RuntimeException("Erro ao buscar cachorros por data de nascimento"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.findByDataNascimento(LocalDate.of(2020, 5, 10));
	    });

	    //Then
	    assertEquals("Erro ao buscar cachorros por data de nascimento: 2020-05-10", thrown.getMessage());
	}
	
	@Test
	@DisplayName("Test that list all data from Cachorro table filter by Descricao and return the list ")
	public void testFindByDescricaoSuccess() {
		//Given
	    Cachorro cachorro = new Cachorro();
	    cachorro.setDescricao("Cachorro amigável");
	    CachorroResponseDTO responseDTO = new CachorroResponseDTO(cachorro);


	    //When
	    when(repository.findByDescricao("Cachorro amigável")).thenReturn(List.of(cachorro));

	    List<CachorroResponseDTO> result = service.findByDescricao("Cachorro amigável");

	    //Then
	    assertNotNull(result);
	    assertEquals(1, result.size());
	    assertEquals("Cachorro amigável", result.get(0).descricao());
	    verify(repository, times(1)).findByDescricao("Cachorro amigável");
	}
	
	
	@Test
	@DisplayName("Test that list all data from Cachorro table filter by Descricao but return an Exception ")
	public void testFindByDescricaoFailure() {
		//When
	    when(repository.findByDescricao("Cachorro amigável")).thenThrow(new RuntimeException("Erro ao buscar cachorros por descrição"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.findByDescricao("Cachorro amigável");
	    });

	    //Then
	    assertEquals("Erro ao buscar cachorros por descrição: Cachorro amigável", thrown.getMessage());
	}
}
