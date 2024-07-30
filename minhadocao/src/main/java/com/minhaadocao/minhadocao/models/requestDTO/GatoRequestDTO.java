package com.minhaadocao.minhadocao.models.requestDTO;

import java.time.LocalDate;

import com.minhaadocao.minhadocao.models.entities.Especie;
import com.minhaadocao.minhadocao.models.entities.RacaGato;
import com.minhaadocao.minhadocao.models.entities.StatusAnimal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GatoRequestDTO ( @NotBlank String nome, @NotNull Especie categoria, @NotNull int idade,
								@NotNull LocalDate dataNascimento, @NotNull StatusAnimal status, String descricao,
								String urlImagem, String adotante, String docAdotante, @Email String emailAdotante,
								@NotNull RacaGato raca){
	
}