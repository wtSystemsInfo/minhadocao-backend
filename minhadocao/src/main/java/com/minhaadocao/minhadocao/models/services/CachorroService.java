package com.minhaadocao.minhadocao.models.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhaadocao.minhadocao.models.entities.Cachorro;
import com.minhaadocao.minhadocao.models.entities.RacaCachorro;
import com.minhaadocao.minhadocao.models.entities.StatusAnimal;
import com.minhaadocao.minhadocao.models.repositories.CachorroRepository;
import com.minhaadocao.minhadocao.models.requestDTO.CachorroRequestDTO;
import com.minhaadocao.minhadocao.models.responseDTO.CachorroResponseDTO;

@Service
public class CachorroService implements CachorroInterface{

	private static final Logger LOGGER = Logger.getLogger(CachorroService.class.getName());
	
	@Autowired
    private CachorroRepository repository;
	
	@Override
	public Cachorro save(CachorroRequestDTO cachorroRequestDTO) {
		try {
            Cachorro cachorro = new Cachorro(cachorroRequestDTO);
            repository.save(cachorro);
            LOGGER.log(Level.INFO, "Cachorro salvo com sucesso");
            return cachorro;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao salvar cachorro", e);
            throw new RuntimeException("Erro ao salvar cachorro", e);
        }
	}
	
	
	@Override
	 public Cachorro update(CachorroRequestDTO cachorroRequestDTO, Long id) {
	    try {
	    	
	        Cachorro cachorroExistente = repository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Cachorro não encontrado com ID: " + id));
	
			Cachorro cachorroAtualizado = new Cachorro(cachorroRequestDTO);
			cachorroAtualizado.setId(cachorroExistente.getId());
			cachorroAtualizado = repository.save(cachorroAtualizado);
	
			LOGGER.log(Level.INFO, "Cachorro atualizado com sucesso com o id: " + cachorroAtualizado.getId());
		    return cachorroAtualizado;
	    } catch (Exception e) {
	    	LOGGER.log(Level.SEVERE, "Erro ao atualizar cachorro", e);
	    	throw new RuntimeException("Erro ao atualizar cachorro", e);
	    }
	}
	
	
	@Override
	public Cachorro updateStatus(StatusAnimal status, Long id) {
		try {
			Cachorro cahorro = repository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Cachorro não encontrado com ID: " + id));
			cahorro.setStatus(status);
			cahorro = repository.save(cahorro);
			
			LOGGER.log(Level.INFO, "Cachorro atualizado com sucesso com o id: " + cahorro.getId());
		    return cahorro;
			
		} catch (Exception e) {
	    	LOGGER.log(Level.SEVERE, "Erro ao atualizar cachorro", e);
	    	throw new RuntimeException("Erro ao atualizar cachorro", e);
	    }
	}


	@Override
	public Cachorro findById(Long id) {
		try {
			Optional<Cachorro> cachorroBusca = repository.findById(id);
			if (cachorroBusca != null) {
				Cachorro cachorro = cachorroBusca.orElse(new Cachorro());
                LOGGER.log(Level.INFO, "Cachorro encontrado para o ID: " + id);
                return cachorro;
            } else {
                LOGGER.log(Level.INFO, "Cachorro não encontrado para o ID: " + id);
                return null;
            }
			
		} catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar cachorro por ID: " + id, e);
            throw new RuntimeException("Erro ao buscar cachorro por ID: " + id, e);
        }
	}

	@Override
	public List<CachorroResponseDTO> listAll() {
		try {
			List<CachorroResponseDTO> cachorroList = repository.findAll().stream()
					.map(CachorroResponseDTO::new)
					.toList();
			LOGGER.log(Level.INFO, "Listagem de todos os cachorros realizada com sucesso");
            return cachorroList;
			
		} catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar todos os cachorros", e);
            throw new RuntimeException("Erro ao listar todos os cachorros", e);
        }
	}

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
            LOGGER.log(Level.INFO, "Cachorro deletado com sucesso para o ID: " + id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar cachorro por ID: " + id, e);
            throw new RuntimeException("Erro ao deletar cachorro por ID: " + id, e);
        }
		
	}

	@Override
	public List<CachorroResponseDTO> findByRaca(RacaCachorro raca) {
		try {
			List<CachorroResponseDTO> cachorroList = repository.findByRaca(raca).stream()
					.map(CachorroResponseDTO::new)
					.toList();					
			LOGGER.log(Level.INFO, "Busca de cachorros por raça realizada com sucesso para a raça: " + raca);
            return cachorroList;
		} catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar cachorros por raça: " + raca, e);
            throw new RuntimeException("Erro ao buscar cachorros por raça: " + raca, e);
        }
	}

	@Override
	public List<CachorroResponseDTO> findByDataNascimento(LocalDate dataNascimento) {
		try {
			List<CachorroResponseDTO> cachorroList = repository.findByDataNascimento(dataNascimento).stream()
					.map(CachorroResponseDTO::new)
					.toList();
            LOGGER.log(Level.INFO, "Busca de cachorros por data de nascimento realizada com sucesso para a data: " + dataNascimento);
            return cachorroList;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar cachorros por data de nascimento: " + dataNascimento, e);
            throw new RuntimeException("Erro ao buscar cachorros por data de nascimento: " + dataNascimento, e);
        }
	}

	@Override
	public List<CachorroResponseDTO> findByDescricao(String descricao) {
		try {
			List<CachorroResponseDTO> cachorroList = repository.findByDescricao(descricao).stream()
					.map(CachorroResponseDTO::new)
					.toList();
            LOGGER.log(Level.INFO, "Busca de cachorros por descrição realizada com sucesso para a descrição: " + descricao);
            return cachorroList;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar cachorros por descrição: " + descricao, e);
            throw new RuntimeException("Erro ao buscar cachorros por descrição: " + descricao, e);
        }
	}


	
}
