package com.minhaadocao.minhadocao.models.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhaadocao.minhadocao.models.entities.Gato;
import com.minhaadocao.minhadocao.models.entities.RacaGato;
import com.minhaadocao.minhadocao.models.entities.StatusAnimal;
import com.minhaadocao.minhadocao.models.repositories.GatoRepository;
import com.minhaadocao.minhadocao.models.requestDTO.GatoRequestDTO;
import com.minhaadocao.minhadocao.models.responseDTO.GatoResponseDTO;


@Service
public class GatoService implements GatoInterface{
 	
	private static final Logger LOGGER = Logger.getLogger(CachorroService.class.getName());
	
	@Autowired
    private GatoRepository repository;
	

	@Override
	public Gato save(GatoRequestDTO gatoRequestDTO) {
		try {
            Gato gato = new Gato(gatoRequestDTO);
            repository.save(gato);
            LOGGER.log(Level.INFO, "Gato salvo com sucesso");
            return gato;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao salvar gato", e);
            throw new RuntimeException("Erro ao salvar gato", e);
        }
	}
	
	@Override
	 public Gato update(GatoRequestDTO gatoRequestDTO, Long id) {
	    try {
	    	
	        Gato gatoExistente = repository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Gato não encontrado com ID: " + id));
	
			Gato gatoAtualizado = new Gato(gatoRequestDTO);
			gatoAtualizado.setId(gatoExistente.getId());
			gatoAtualizado = repository.save(gatoAtualizado);
	
			LOGGER.log(Level.INFO, "Gato atualizado com sucesso com o id: " + gatoAtualizado.getId());
		    return gatoAtualizado;
	    } catch (Exception e) {
	    	LOGGER.log(Level.SEVERE, "Erro ao atualizar gato", e);
	    	throw new RuntimeException("Erro ao atualizar gato", e);
	    }
	}
	
	@Override
	public Gato updateStatus(StatusAnimal status, Long id) {
		try {
			Gato gato = repository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Gato não encontrado com ID: " + id));
			gato.setStatus(status);
			gato = repository.save(gato);
			
			LOGGER.log(Level.INFO, "Gato atualizado com sucesso com o id: " + gato.getId());
		    return gato;
			
		} catch (Exception e) {
	    	LOGGER.log(Level.SEVERE, "Erro ao atualizar gato", e);
	    	throw new RuntimeException("Erro ao atualizar gato", e);
	    }
	}
	

	@Override
	public Gato findById(Long id) {
		try {
			Optional<Gato> gatoBusca = repository.findById(id);
			if (gatoBusca != null) {
				Gato gato = gatoBusca.orElse(new Gato());
                LOGGER.log(Level.INFO, "Gato encontrado para o ID: " + id);
                return gato;
            } else {
                LOGGER.log(Level.INFO, "Gato não encontrado para o ID: " + id);
                return null;
            }
			
		} catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar gato por ID: " + id, e);
            throw new RuntimeException("Erro ao buscar gato por ID: " + id, e);
        }
	}

	@Override
	public List<GatoResponseDTO> listAll() {
		try {
			List<GatoResponseDTO> gatoList = repository.findAll().stream()
					.map(GatoResponseDTO::new)
					.toList();
			LOGGER.log(Level.INFO, "Listagem de todos os gatos realizada com sucesso");
            return gatoList;
			
		} catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar todos os gatos", e);
            throw new RuntimeException("Erro ao listar todos os gatos", e);
        }
	}

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
            LOGGER.log(Level.INFO, "Gato deletado com sucesso para o ID: " + id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar gato por ID: " + id, e);
            throw new RuntimeException("Erro ao deletar gato por ID: " + id, e);
        }
		
	}

	@Override
	public List<GatoResponseDTO> findByRaca(RacaGato raca) {
		try {
			List<GatoResponseDTO> gatoList = repository.findByRaca(raca).stream()
					.map(GatoResponseDTO::new)
					.toList();
			LOGGER.log(Level.INFO, "Busca de gatos por raça realizada com sucesso para a raça: " + raca);
            return gatoList;
		} catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar gatos por raça: " + raca, e);
            throw new RuntimeException("Erro ao buscar gatos por raça: " + raca, e);
        }
	}

	@Override
	public List<GatoResponseDTO> findByDataNascimento(LocalDate dataNascimento) {
		try {
			List<GatoResponseDTO> gatoList = repository.findByDataNascimento(dataNascimento).stream()
					.map(GatoResponseDTO::new)
					.toList();
            LOGGER.log(Level.INFO, "Busca de gatos por data de nascimento realizada com sucesso para a data: " + dataNascimento);
            return gatoList;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar gatos por data de nascimento: " + dataNascimento, e);
            throw new RuntimeException("Erro ao buscar gatos por data de nascimento: " + dataNascimento, e);
        }
	}

	@Override
	public List<GatoResponseDTO> findByDescricao(String descricao) {
		try {
			List<GatoResponseDTO> gatoList = repository.findByDescricao(descricao).stream()
					.map(GatoResponseDTO::new)
					.toList();
            LOGGER.log(Level.INFO, "Busca de gatos por descrição realizada com sucesso para a descrição: " + descricao);
            return gatoList;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar gatos por descrição: " + descricao, e);
            throw new RuntimeException("Erro ao buscar gatos por descrição: " + descricao, e);
        }
	}

	
	
	
}
