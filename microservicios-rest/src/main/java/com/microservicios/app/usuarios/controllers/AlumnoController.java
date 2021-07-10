package com.microservicios.app.usuarios.controllers;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.microservicios.app.usuarios.services.AlumnoService;
import com.microservicios.commons.alumnos.models.entity.Alumno;
import com.microservicios.commons.controller.CommonController;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService>{

	@GetMapping("/alumnos-por-curso")
	public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids){
		return ResponseEntity.ok(service.findAllById(ids));
	}
	
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id){
		Optional<Alumno> o = service.findById(id);
		
		if(o.isEmpty() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource imagen = new ByteArrayResource(o.get().getFoto());
		
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(imagen);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alu, BindingResult resultado, @PathVariable Long id){
		
		if(resultado.hasErrors()) {
			return validar(resultado);
		}
		
		Optional<Alumno> alumno = service.findById(id);
		
		if(alumno.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnodb =  alumno.get();
		alumnodb.setNombre(alu.getNombre());
		alumnodb.setApellido(alu.getApellido());
		alumnodb.setEmail(alu.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnodb));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombreOrApellido(term));
	}

	
	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult resultado,
			@RequestParam MultipartFile archivo) throws IOException {
		if(!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		return super.crear(alumno, resultado);
	}
	
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(@Valid Alumno alu, BindingResult resultado,
			@PathVariable Long id, @RequestParam MultipartFile archivo) throws IOException{
		
		if(resultado.hasErrors()) {
			return validar(resultado);
		}
		
		Optional<Alumno> alumno = service.findById(id);
		
		if(alumno.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnodb =  alumno.get();
		alumnodb.setNombre(alu.getNombre());
		alumnodb.setApellido(alu.getApellido());
		alumnodb.setEmail(alu.getEmail());
		
		if(!archivo.isEmpty()) {
			alumnodb.setFoto(archivo.getBytes());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnodb));
	}
	

}
