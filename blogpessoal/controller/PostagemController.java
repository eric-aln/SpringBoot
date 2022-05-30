package com.example.com.generation.blogpessoal.controller;

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

import com.example.com.generation.blogpessoal.model.Postagem;
import com.example.com.generation.blogpessoal.repository.PostagemRepository;

import java.util.List;


@RestController //indica que a classe é uma classe controladora da api(onde ficam os endpoints)
@CrossOrigin("*") // permite que requisições de outras portas sejam aceitas na minha aplicação.

	// cria ou indica um endpoint, sempre minúsculo e sem espaço/caracteres especiais.
@RequestMapping("/postagens") 


public class PostagemController {

	// funciona como injeção de dependencia, transferindo a responsabilidade 
	@Autowired 
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> buscaPostagem(){ //buscaPostagem: nome da função
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> buscaPostagemPorId(@PathVariable Long id){
		return repository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> buscaPostagemPorTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> adicionarPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}