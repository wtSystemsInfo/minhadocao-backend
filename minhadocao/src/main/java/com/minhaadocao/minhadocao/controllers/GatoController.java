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
import com.minhaadocao.minhadocao.models.entities.Gato;
import com.minhaadocao.minhadocao.models.entities.RacaGato;
import com.minhaadocao.minhadocao.models.entities.StatusAnimal;
import com.minhaadocao.minhadocao.models.requestDTO.GatoRequestDTO;
import com.minhaadocao.minhadocao.models.responseDTO.GatoResponseDTO;
import com.minhaadocao.minhadocao.models.services.GatoService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/adocao/gatos")
public class GatoController {
	
	@Autowired
    private GatoService service;
	
	@PostMapping
    public ResponseEntity<Gato> saveGato(@RequestBody GatoRequestDTO gatoRequestDTO) {
        Gato gato = service.save(gatoRequestDTO);
        return new ResponseEntity<>(gato, HttpStatus.CREATED);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Gato> updateGato(@PathVariable Long id, @RequestBody GatoRequestDTO gatoRequestDTO) {
        try {
            Gato gatoAtualizado = service.update(gatoRequestDTO, id);
            return ResponseEntity.ok(gatoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
	
	@PutMapping("/{id}/status")
    public ResponseEntity<Gato> updateStatus(@PathVariable("id") Long id, @RequestParam("status") StatusAnimal status) {
        
        try {
        	Gato gatoAtualizado = service.updateStatus(status, id);
            return ResponseEntity.ok(gatoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Gato> findGatoById(@PathVariable Long id) {
        Gato gato = service.findById(id);
        if (gato != null) {
            return new ResponseEntity<>(gato, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@GetMapping
    public ResponseEntity<List<GatoResponseDTO>> listAllGatos() {
        List<GatoResponseDTO> gatos = service.listAll();
        return new ResponseEntity<>(gatos, HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGatoPorId(@PathVariable Long id) {
		service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@GetMapping("/raca/{raca}")
    public ResponseEntity<List<GatoResponseDTO>> buscarPorRaca(@PathVariable RacaGato raca) {
        List<GatoResponseDTO> gatos = service.findByRaca(raca);
        return new ResponseEntity<>(gatos, HttpStatus.OK);
    }

    @GetMapping("/data-nascimento/{data}")
    public ResponseEntity<List<GatoResponseDTO>> buscarPorDataNascimento(@PathVariable LocalDate data) {
        List<GatoResponseDTO> gatos = service.findByDataNascimento(data);
        return new ResponseEntity<>(gatos, HttpStatus.OK);
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<GatoResponseDTO>> buscarPorDescricao(@PathVariable String descricao) {
        List<GatoResponseDTO> gatos = service.findByDescricao(descricao);
        return new ResponseEntity<>(gatos, HttpStatus.OK);
    }
}
