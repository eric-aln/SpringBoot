package com.example.com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.com.generation.blogpessoal.model.Tema;
import com.example.com.generation.blogpessoal.repository.TemaRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tema")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	@PostMapping
	public ResponseEntity<Tema> postTema(@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(repository.save(tema));
	}
	@PutMapping
	public ResponseEntity<Tema> putTema(@Valid @RequestBody Tema tema){
		return repository.findById(tema.getId())
				.map(resp -> {
					return ResponseEntity.ok().body(repository.save(tema));
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable Long id) {
		return repository.findById(id)
				.map(resp -> {
					repository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
}
