package com.minhaadocao.minhadocao.models.services;

import java.time.LocalDate;
import java.util.List;

import com.minhaadocao.minhadocao.models.entities.Cachorro;
import com.minhaadocao.minhadocao.models.entities.RacaCachorro;
import com.minhaadocao.minhadocao.models.entities.StatusAnimal;
import com.minhaadocao.minhadocao.models.requestDTO.CachorroRequestDTO;
import com.minhaadocao.minhadocao.models.responseDTO.CachorroResponseDTO;

public interface CachorroInterface {
	
	Cachorro save(CachorroRequestDTO cachorroRequestDTO);
	
	Cachorro update(CachorroRequestDTO cachorroRequestDTO, Long id);
	
	Cachorro updateStatus(StatusAnimal status, Long id);
	
	Cachorro findById(Long id);
	
	void delete(Long id);
	
	List<CachorroResponseDTO> listAll();	
	
	List<CachorroResponseDTO> findByRaca(RacaCachorro raca);

    List<CachorroResponseDTO> findByDataNascimento(LocalDate dataNascimento);

    List<CachorroResponseDTO> findByDescricao(String descricao);
	 
}
