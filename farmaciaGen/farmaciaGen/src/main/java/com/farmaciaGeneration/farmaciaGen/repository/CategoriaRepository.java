package com.farmaciaGeneration.farmaciaGen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmaciaGeneration.farmaciaGen.model.Categoria;
import com.farmaciaGeneration.farmaciaGen.model.Produto;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	public List<Produto> findAllByTarjaContainingIgnoreCase (String tarja);

}
