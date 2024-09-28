package com.example.usuario_service.api;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuario_service.domain.Estado;
import com.example.usuario_service.repository.EstadoRepository;
import com.example.usuario_service.service.EstadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="api/estados", produces="application/json")
@CrossOrigin(origins="*")
@Tag(name="estado",description="API del Recurso estado")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	@Operation(summary="Obtiene todos los estados")
	public List<Estado> obtenerEstados() {
		return estadoRepository.findAll();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary="Crea un estado")
	public Estado crearEstado(@RequestBody Estado estado) {
		return estadoRepository.save(estado);
	}
	
	@GetMapping("{id}")
	@Operation(summary="Obtiene un estado con el identificador dado")
	@Parameter(name="id",description="Identificador del Estado",required=true)
	public ResponseEntity<Estado> obtenerEstadoPorId(@PathVariable("id") String id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		if (estado.isPresent()) {
			return new ResponseEntity<>(estado.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(path="{id}", consumes="application/json")
	@Operation(summary="Actualiza un estado con el identificador dado")
	@Parameter(name="id",description="Identificador del Estado",required=true)
	public ResponseEntity<Estado> actualizarEstado(
			@PathVariable("id") String id, @RequestBody Estado estado) {
		Estado estadoActualizado = estadoService.actualizarEstado(id, estado);
		if (estadoActualizado != null) {
			return new ResponseEntity<>(estadoActualizado, HttpStatus.OK) ;
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary="Elimina un estado con el identificador dado")
	@Parameter(name="id",description="Identificador del Estado",required=true)
	public void eliminarEstado(@PathVariable("id") String id) {
		estadoRepository.deleteById(id);
	}

}
