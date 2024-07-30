package com.minhaadocao.minhadocao.models.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.minhaadocao.minhadocao.models.entities.Cachorro;
import com.minhaadocao.minhadocao.models.entities.RacaCachorro;

@Repository
public interface CachorroRepository extends JpaRepository<Cachorro, Long>{
	
	@Query("SELECT c FROM Cachorro c WHERE c.raca = :raca")
    List<Cachorro> findByRaca(@Param("raca") RacaCachorro raca);

    @Query("SELECT c FROM Cachorro c WHERE c.dataNascimento = :dataNascimento")
    List<Cachorro> findByDataNascimento(@Param("dataNascimento") LocalDate dataNascimento);

    @Query("SELECT c FROM Cachorro c WHERE LOWER(c.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))")
    List<Cachorro> findByDescricao(@Param("descricao") String descricao);
}
