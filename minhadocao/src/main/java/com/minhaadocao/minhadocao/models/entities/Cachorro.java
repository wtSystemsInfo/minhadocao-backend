package com.minhaadocao.minhadocao.models.entities;


import com.minhaadocao.minhadocao.models.requestDTO.CachorroRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString(callSuper = true)
@Entity
@Table(name = "cahorros")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cachorro extends Animal{
	
	@Enumerated(EnumType.STRING)
	@Column(name = "raca")
	private RacaCachorro raca;
	
	public Cachorro(CachorroRequestDTO data) {
		super(
	            null,
	            data.nome(),
	            data.categoria(),
	            data.idade(),
	            data.dataNascimento(),
	            data.status(),
	            data.descricao(),
	            data.urlImagem(),
	            data.adotante(),
	            data.docAdotante(),
	            data.emailAdotante()
	        );
	        this.raca = data.raca();
	}
}
