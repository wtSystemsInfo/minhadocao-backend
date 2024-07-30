package com.minhaadocao.minhadocao.models.services;

import java.time.LocalDate;
import java.util.List;
import com.minhaadocao.minhadocao.models.entities.Gato;
import com.minhaadocao.minhadocao.models.entities.RacaGato;
import com.minhaadocao.minhadocao.models.entities.StatusAnimal;
import com.minhaadocao.minhadocao.models.requestDTO.GatoRequestDTO;
import com.minhaadocao.minhadocao.models.responseDTO.GatoResponseDTO;

public interface GatoInterface {

	Gato save(GatoRequestDTO gatoRequestDTO);
	
	Gato update(GatoRequestDTO gatoRequestDTO, Long id);
	
	Gato updateStatus(StatusAnimal status, Long id);
	
	Gato findById(Long id);
	
	void delete(Long id);
	
	List<GatoResponseDTO> listAll();	
	
	List<GatoResponseDTO> findByRaca(RacaGato raca);

    List<GatoResponseDTO> findByDataNascimento(LocalDate dataNascimento);

    List<GatoResponseDTO> findByDescricao(String descricao);
}
