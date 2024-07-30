package com.minhaadocao.minhadocao.controllers;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minhaadocao.minhadocao.models.entities.Cachorro;
import com.minhaadocao.minhadocao.models.entities.RacaCachorro;
import com.minhaadocao.minhadocao.models.entities.StatusAnimal;
import com.minhaadocao.minhadocao.models.requestDTO.CachorroRequestDTO;
import com.minhaadocao.minhadocao.models.responseDTO.CachorroResponseDTO;
import com.minhaadocao.minhadocao.models.services.CachorroService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/adocao/cachorros")
public class CachorroController {
	
	@Autowired
    private CachorroService service;
	
	@PostMapping
    public ResponseEntity<Cachorro> saveCachorro(@RequestBody CachorroRequestDTO cachorroRequestDTO) {
        Cachorro cachorro = service.save(cachorroRequestDTO);
        return new ResponseEntity<>(cachorro, HttpStatus.CREATED);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Cachorro> updateCachorro(@PathVariable Long id, @RequestBody CachorroRequestDTO cachorroRequestDTO) {
        try {
            Cachorro cachorroAtualizado = service.update(cachorroRequestDTO, id);
            return ResponseEntity.ok(cachorroAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
	
	@PutMapping("/{id}/status")
    public ResponseEntity<Cachorro> updateStatus(@PathVariable("id") Long id, @RequestParam("status") StatusAnimal status) {
        
        try {
            Cachorro cachorroAtualizado = service.updateStatus(status, id);
            return ResponseEntity.ok(cachorroAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
	@GetMapping("/{id}")
    public ResponseEntity<Cachorro> findCachorroById(@PathVariable Long id) {
        Cachorro cachorro = service.findById(id);
        if (cachorro != null) {
            return new ResponseEntity<>(cachorro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@GetMapping
    public ResponseEntity<List<CachorroResponseDTO>> listAllCachorros() {
        List<CachorroResponseDTO> cachorros = service.listAll();
        return new ResponseEntity<>(cachorros, HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCachorroPorId(@PathVariable Long id) {
		service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@GetMapping("/raca/{raca}")
    public ResponseEntity<List<CachorroResponseDTO>> buscarPorRaca(@PathVariable RacaCachorro raca) {
        List<CachorroResponseDTO> cachorros = service.findByRaca(raca);
        return new ResponseEntity<>(cachorros, HttpStatus.OK);
    }

    @GetMapping("/data-nascimento/{data}")
    public ResponseEntity<List<CachorroResponseDTO>> buscarPorDataNascimento(@PathVariable LocalDate data) {
        List<CachorroResponseDTO> cachorros = service.findByDataNascimento(data);
        return new ResponseEntity<>(cachorros, HttpStatus.OK);
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<CachorroResponseDTO>> buscarPorDescricao(@PathVariable String descricao) {
        List<CachorroResponseDTO> cachorros = service.findByDescricao(descricao);
        return new ResponseEntity<>(cachorros, HttpStatus.OK);
    }
}
