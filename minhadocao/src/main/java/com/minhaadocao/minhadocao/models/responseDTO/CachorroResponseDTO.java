package com.minhaadocao.minhadocao.models.responseDTO;

import java.time.LocalDate;

import com.minhaadocao.minhadocao.models.entities.Cachorro;
import com.minhaadocao.minhadocao.models.entities.Especie;
import com.minhaadocao.minhadocao.models.entities.RacaCachorro;
import com.minhaadocao.minhadocao.models.entities.StatusAnimal;

public record CachorroResponseDTO (Long id, String nome, Especie categoria, int idade, LocalDate dataNascimento,
									StatusAnimal status, String descricao, String urlImagem, String adotante,
									String docAdotante, String emailAdotante, RacaCachorro raca) {
	
	 
    
    public CachorroResponseDTO(Cachorro cachorro) {
    	this(
                cachorro.getId(),
                cachorro.getNome(),
                cachorro.getCategoria(),
                cachorro.getIdade(),
                cachorro.getDataNascimento(),
                cachorro.getStatus(),
                cachorro.getDescricao(),
                cachorro.getUrlImagem(),
                cachorro.getAdotante(),
                cachorro.getDocAdotante(),
                cachorro.getEmailAdotante(),
                cachorro.getRaca()
            );
    }
}
