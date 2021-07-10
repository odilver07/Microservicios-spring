package com.microservicios.app.respuestas.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.app.respuestas.clients.ExamenFeighClient;
import com.microservicios.app.respuestas.models.entity.Respuesta;
import com.microservicios.app.respuestas.models.respository.RespuestaRepository;
import com.microservicios.commons.examenes.models.entity.Examen;
import com.microservicios.commons.examenes.models.entity.Pregunta;

@Service
public class RespuestasServiceImpl implements RespuestaService{

	@Autowired
	private RespuestaRepository repository;
	
	@Autowired
	private ExamenFeighClient examenRepo;
	
	@Override
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		return repository.saveAll(respuestas);
	}

	@Override
	public Iterable<Respuesta> findRespuestasByAlumnoByExamen(Long alumnoId, Long examenId) {
//		Examen examen = examenRepo.obtenerExamenPorId(alumnoId);
//		List<Pregunta> preguntas = examen.getPreguntas();
//		List<Long> preguntasIds = preguntas.stream().map(p -> 
//			p.getId()).collect(Collectors.toList());
//		List<Respuesta> respuestas = (List<Respuesta>) repository.findRespuestasByAlumnoByRespuestaIds(alumnoId, preguntasIds);
//		respuestas = respuestas.stream().map(r -> {
//			preguntas.forEach(p -> {
//				if(p.getId() == r.getPreguntaId()) {
//					r.setPregunta(p);
//				}
//			});
//			return r;
//		}).collect(Collectors.toList());
		List<Respuesta> respuestas = (List<Respuesta>) repository.findRespuestasByAlumnoByExamen(alumnoId, examenId);
		return respuestas;
	}

	@Override
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(long alumnoId) {
//		List<Respuesta> respuestaAlumno = (List<Respuesta>) repository.findByAlumnoId(alumnoId);
//		List<Long> examenIds = Collections.emptyList();
//		if(respuestaAlumno.size() > 0) {
//			List<Long> preguntaIds =  respuestaAlumno.stream().map(c -> c.getPreguntaId()).collect(Collectors.toList());
//			examenIds =  examenRepo.obtenerExamenesIdsPorPreguntasIdsRespondidas(preguntaIds);
//		}
		List<Respuesta> respuestaAlumno = (List<Respuesta>) repository.findExamenesIdsConRespuestasByAlumno(alumnoId);
		List<Long> examenIds = respuestaAlumno.stream().map(r -> r.getPregunta().getExamen().getId()).distinct()
				.collect(Collectors.toList());
		return examenIds;
	}

	@Override
	public Iterable<Respuesta> findByAlumnoId(Long Id) {
		return repository.findByAlumnoId(Id);
	}

}
