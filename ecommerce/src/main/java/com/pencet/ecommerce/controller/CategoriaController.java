package com.pencet.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pencet.ecommerce.exception.CategoriaNotFoundException;
import com.pencet.ecommerce.exception.ParametroObrigatorioException;
import com.pencet.ecommerce.model.Categoria;
import com.pencet.ecommerce.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody Categoria categoria) {
		categoriaService.inserir(categoria);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(categoriaService.listar(), headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> listarPorId(@PathVariable Integer id) throws CategoriaNotFoundException {
		Categoria categoria = categoriaService.listarPorId(id);

		if (categoria != null) {
			return ResponseEntity.ok(categoria);
		}
		return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> substituir(@PathVariable Integer id, @RequestBody(required = true) Categoria categoria)
			throws CategoriaNotFoundException, ParametroObrigatorioException {
		categoriaService.substituir(id, categoria);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws CategoriaNotFoundException {
		categoriaService.deletar(id);
	}

}