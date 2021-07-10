package com.microservicios.app.respuestas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.microservicios.app.respuestas.models.entity.Respuesta;
import com.microservicios.app.respuestas.services.RespuestaService;

@RestController
public class RespuestasController {
	
	@Autowired
	private RespuestaService service;
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas){
		respuestas = ((List<Respuesta>)respuestas).stream().map(r -> {
			r.setAlumnoId(r.getAlumno().getId());
			r.setPreguntaId(r.getPregunta().getId());
			return r;
		}).collect(Collectors.toList());
		Iterable<Respuesta> respuestasDb = service.saveAll(respuestas);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestasDb);
	}
	
	@GetMapping("/alumno/{alumnoId}/examen/{examenId}")
	public ResponseEntity<?> obtenerRespuestaPorAlumnoPorExamen(@PathVariable Long alumnoId, @PathVariable long examenId){
		Iterable<Respuesta> respuestas = service.findRespuestasByAlumnoByExamen(alumnoId, examenId);
		return ResponseEntity.ok(respuestas);
	}
	
	@GetMapping("/alumno/{id}/examenes-respondidos")
	public ResponseEntity<?> obtenerExamenesIdsConRespuestasAlumno(@PathVariable Long id){
		Iterable<Long> examenes = service.findExamenesIdsConRespuestasByAlumno(id);
		return ResponseEntity.ok(examenes);
	}
}
