package com.minhaadocao.minhadocao.models.responseDTO;

import java.time.LocalDate;

import com.minhaadocao.minhadocao.models.entities.Especie;
import com.minhaadocao.minhadocao.models.entities.Gato;
import com.minhaadocao.minhadocao.models.entities.RacaGato;
import com.minhaadocao.minhadocao.models.entities.StatusAnimal;


public record GatoResponseDTO (Long id, String nome, Especie categoria, int idade, LocalDate dataNascimento,
		StatusAnimal status, String descricao, String urlImagem, String adotante,
		String docAdotante, String emailAdotante, RacaGato raca) {



	public GatoResponseDTO(Gato gato) {
	this(
			gato.getId(),
			gato.getNome(),
			gato.getCategoria(),
			gato.getIdade(),
			gato.getDataNascimento(),
			gato.getStatus(),
			gato.getDescricao(),
			gato.getUrlImagem(),
			gato.getAdotante(),
			gato.getDocAdotante(),
			gato.getEmailAdotante(),
			gato.getRaca()
		);
	}
	
	
}
