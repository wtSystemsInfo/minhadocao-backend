package com.minhaadocao.minhadocao.models.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Animal {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Especie categoria;

    @Column(nullable = false)
    private int idade;
    
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAnimal status;

    @Column
    private String descricao;

    @Column(name = "url_imagem")
    private String urlImagem;
    
    @Column
    private String adotante;
    
    @Column(name = "doc_adotante")
    private String docAdotante;
    
    @Column(name = "email_adotante")
    @Email
    private String emailAdotante;
    
}
