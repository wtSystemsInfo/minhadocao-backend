package com.minhaadocao.minhadocao.models.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.minhaadocao.minhadocao.models.entities.Gato;
import com.minhaadocao.minhadocao.models.entities.RacaGato;

@Repository
public interface GatoRepository extends JpaRepository<Gato, Long>{

	@Query("SELECT g FROM gatos g WHERE g.raca = :raca")
    List<Gato> findByRaca(@Param("raca") RacaGato raca);

    @Query("SELECT g FROM gatos g WHERE g.dataNascimento = :dataNascimento")
    List<Gato> findByDataNascimento(@Param("dataNascimento") LocalDate dataNascimento);

    @Query("SELECT g FROM gatos g WHERE LOWER(g.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))")
    List<Gato> findByDescricao(@Param("descricao") String descricao);
}
