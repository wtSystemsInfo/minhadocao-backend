package com.minhaadocao.minhadocao.services;

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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.minhaadocao.minhadocao.models.entities.Especie;
import com.minhaadocao.minhadocao.models.entities.Gato;
import com.minhaadocao.minhadocao.models.entities.RacaGato;
import com.minhaadocao.minhadocao.models.entities.StatusAnimal;
import com.minhaadocao.minhadocao.models.repositories.GatoRepository;
import com.minhaadocao.minhadocao.models.requestDTO.GatoRequestDTO;
import com.minhaadocao.minhadocao.models.responseDTO.GatoResponseDTO;
import com.minhaadocao.minhadocao.models.services.GatoService;



@ExtendWith(MockitoExtension.class)
public class GatoServiceTest {
	
	@Mock
	private GatoRepository repository;
	
	@InjectMocks
	private GatoService service;
	
	private GatoRequestDTO gatoRight;
	
	private GatoRequestDTO gatoWrong;
	
	
	@BeforeEach
	public void setup() {
		
		gatoRight = new GatoRequestDTO(
        "Rex", 
        Especie.GATO,
        4, 
        LocalDate.of(2020, 5, 10), 
        StatusAnimal.DISPONIVEL, 
        "Gato amigável e bem treinado.",
        "http://example.com/imagem_rex.jpg", 
        "Carlos Silva", 
        "12345678901", 
        "carlos.silva@gmail.com", 
        RacaGato.SEM_RAÇA_DEFINIDA );
		
		
		gatoWrong = new GatoRequestDTO(
		        "Rex", 
		        Especie.GATO,
		        -1, 
		        LocalDate.of(2025, 5, 10), 
		        StatusAnimal.DISPONIVEL, 
		        "",
		        "", 
		        "", 
		        "", 
		        "", 
		        RacaGato.SEM_RAÇA_DEFINIDA );
		
		
	}
	
	
	@Test
	@DisplayName("Given valid Gato data, when save Gato then Return Gato JSON")
	public void testSaveGatoSuccess() {
		
		//Given
	    Gato gato = new Gato(gatoRight);
	    
	    //When
	    when(repository.save(any(Gato.class))).thenReturn(gato);
	    
	    Gato result = service.save(gatoRight);

	    //Then
	    assertNotNull(result);
	    assertEquals("Rex", result.getNome());
	    assertEquals(StatusAnimal.DISPONIVEL, result.getStatus());
	    verify(repository, times(1)).save(any(Gato.class));
	}

	@Test
	@DisplayName("Given invalid Gato data, when save Gato then Return Exception")
	public void testSaveGatoFailure() {
		//When
	    when(repository.save(any(Gato.class))).thenThrow(new RuntimeException("Erro ao salvar"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.save(gatoWrong);
	    });

	    //Then
	    assertEquals("Erro ao salvar gato", thrown.getMessage());
	}
	
	@Test
	@DisplayName("Given valid Gato data, when update Gato then Return Gato updated JSON")
	public void testUpdateGatoSuccess() {
	    //Given
		Gato gatoExistente = new Gato();
	    gatoExistente.setId(1L);
	    Gato gatoAtualizado = new Gato(gatoRight);
	    gatoAtualizado.setId(1L);

	    //When
	    when(repository.findById(1L)).thenReturn(Optional.of(gatoExistente));
	    when(repository.save(any(Gato.class))).thenReturn(gatoAtualizado);

	    Gato result = service.update(gatoRight, 1L);

	    
	    //Then
	    assertNotNull(result);
	    assertEquals(1L, result.getId());
	    verify(repository, times(1)).findById(1L);
	    verify(repository, times(1)).save(any(Gato.class));
	}
	
	@Test
	@DisplayName("Given invalid Gato id, when try update Gato then Return Exception")
	public void testUpdateGatoFailure() {
	    //When
		when(repository.findById(1L)).thenThrow(new RuntimeException("Erro ao buscar gato"));	    

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.update(gatoWrong, 1L);
	    });

	    //Then
	    assertEquals("Erro ao atualizar gato", thrown.getMessage());
	}
	
	
	@Test
	@DisplayName("Given valid Gato id and Status, update status Gato then Return Gato updated")
	public void testUpdateStatusSuccess() {
	    //Given
		Gato gato = new Gato();
	    gato.setId(1L);
	    gato.setStatus(StatusAnimal.DISPONIVEL);

	    //When
	    when(repository.findById(1L)).thenReturn(Optional.of(gato));
	    when(repository.save(any(Gato.class))).thenReturn(gato);

	    Gato result = service.updateStatus(StatusAnimal.ADOTADO, 1L);
	    
	    
	    //Then
	    assertNotNull(result);
	    assertEquals(StatusAnimal.ADOTADO, result.getStatus());
	    verify(repository, times(1)).findById(1L);
	    verify(repository, times(1)).save(any(Gato.class));
	}
	
	@Test
	@DisplayName("Given invalid Data, when try update Gato then Return Exception")
	public void testUpdateStatusFailure() {
		//When
		when(repository.findById(1L)).thenThrow(new RuntimeException("Erro ao buscar gato"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.updateStatus(StatusAnimal.ADOTADO, 1L);
	    });

	    //Then
	    assertEquals("Erro ao atualizar gato", thrown.getMessage());
	}
	
	
	@Test
	@DisplayName("Given de ID, then find the Gato data that correspond the ID")
	public void testFindByIdSuccess() {
	    //Given
		Gato gato = new Gato();
	    gato.setId(1L);

	    //When
	    when(repository.findById(1L)).thenReturn(Optional.of(gato));

	    Gato result = service.findById(1L);

	    //Then
	    assertNotNull(result);
	    assertEquals(1L, result.getId());
	    verify(repository, times(1)).findById(1L);
	}
	
	
	@Test
	@DisplayName("Given de ID, then throws an Exception when try to find Gato data")
	public void testFindByIdFailure() {
	    //When
		when(repository.findById(1L)).thenThrow(new RuntimeException("Erro ao buscar gato"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.findById(1L);
	    });

	    //Then
	    assertEquals("Erro ao buscar gato por ID: 1", thrown.getMessage());
	}
	
	@Test
	@DisplayName("Test that list all data from Gato table and return the list ")
	public void testListAllSuccess() {
	    //Given
		Gato gato = new Gato();
	    gato.setId(1L);

	    //When
	    when(repository.findAll()).thenReturn(List.of(gato));

	    List<GatoResponseDTO> result = service.listAll();

	    //Then
	    assertNotNull(result);
	    assertEquals(1, result.size());
	    verify(repository, times(1)).findAll();
	}
	
	@Test
	@DisplayName("Test that list all data from Gato table but return an Exception ")
	public void testListAllFailure() {
		//When
	    when(repository.findAll()).thenThrow(new RuntimeException("Erro ao listar gatos"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.listAll();
	    });

	    //Then
	    assertEquals("Erro ao listar todos os gatos", thrown.getMessage());
	}
	
	@Test
	@DisplayName("Test that list all data from Gato table filter by Raca and return the list ")
	public void testFindByRacaSuccess() {
	    Gato gato = new Gato();
	    gato.setRaca(RacaGato.SEM_RAÇA_DEFINIDA);

	    when(repository.findByRaca(RacaGato.SEM_RAÇA_DEFINIDA)).thenReturn(List.of(gato));
	    List<GatoResponseDTO> responseList = List.of(new GatoResponseDTO(gato));

	    List<GatoResponseDTO> result = service.findByRaca(RacaGato.SEM_RAÇA_DEFINIDA);

	    assertNotNull(result);
	    assertEquals(1, result.size());
	    verify(repository, times(1)).findByRaca(RacaGato.SEM_RAÇA_DEFINIDA);
	}
	
	
	@Test
	@DisplayName("Test that list all data from Gato table filter by Raca but return an Exception ")
	public void testFindByRacaFailure() {
		//When
	    when(repository.findByRaca(RacaGato.SEM_RAÇA_DEFINIDA)).thenThrow(new RuntimeException("Erro ao buscar gatos por raça"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.findByRaca(RacaGato.SEM_RAÇA_DEFINIDA);
	    });

	    //Then
	    assertEquals("Erro ao buscar gatos por raça: SEM_RAÇA_DEFINIDA", thrown.getMessage());
	}
	
	
	@Test
	@DisplayName("Test that list all data from Gato table filter by date and return the list ")
	public void testFindByDataNascimentoSuccess() {
		//Given
	    Gato gato = new Gato();
	    gato.setDataNascimento(LocalDate.of(2020, 5, 10));

	    //When
	    when(repository.findByDataNascimento(LocalDate.of(2020, 5, 10))).thenReturn(List.of(gato));
	    List<GatoResponseDTO> responseList = List.of(new GatoResponseDTO(gato));

	    List<GatoResponseDTO> result = service.findByDataNascimento(LocalDate.of(2020, 5, 10));

	    //Then
	    assertNotNull(result);
	    assertEquals(1, result.size());
	    verify(repository, times(1)).findByDataNascimento(LocalDate.of(2020, 5, 10));
	}
	
	
	@Test
	@DisplayName("Test that list all data from Gato table filter by date but return an Exception ")
	public void testFindByDataNascimentoFailure() {
		//When
	    when(repository.findByDataNascimento(LocalDate.of(2020, 5, 10))).thenThrow(new RuntimeException("Erro ao buscar gatos por data de nascimento"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.findByDataNascimento(LocalDate.of(2020, 5, 10));
	    });

	    //Then
	    assertEquals("Erro ao buscar gatos por data de nascimento: 2020-05-10", thrown.getMessage());
	}
	
	@Test
	@DisplayName("Test that list all data from Gato table filter by Descricao and return the list ")
	public void testFindByDescricaoSuccess() {
		//Given
	    Gato gato = new Gato();
	    gato.setDescricao("Gato amigável");
	    GatoResponseDTO responseDTO = new GatoResponseDTO(gato);


	    //When
	    when(repository.findByDescricao("Gato amigável")).thenReturn(List.of(gato));

	    List<GatoResponseDTO> result = service.findByDescricao("Gato amigável");

	    //Then
	    assertNotNull(result);
	    assertEquals(1, result.size());
	    assertEquals("Gato amigável", result.get(0).descricao());
	    verify(repository, times(1)).findByDescricao("Gato amigável");
	}
	
	
	@Test
	@DisplayName("Test that list all data from Gato table filter by Descricao but return an Exception ")
	public void testFindByDescricaoFailure() {
		//When
	    when(repository.findByDescricao("Gato amigável")).thenThrow(new RuntimeException("Erro ao buscar gatos por descrição"));

	    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
	        service.findByDescricao("Gato amigável");
	    });

	    //Then
	    assertEquals("Erro ao buscar gatos por descrição: Gato amigável", thrown.getMessage());
	}

}
