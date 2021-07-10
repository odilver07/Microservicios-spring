package com.microservicios.app.respuestas.services;

import com.microservicios.app.respuestas.models.entity.Respuesta;

public interface RespuestaService {
	
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);
	
	public Iterable<Respuesta> findRespuestasByAlumnoByExamen(Long alumnoId, Long examenId);

	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(long alumnoId);

	public Iterable<Respuesta> findByAlumnoId(Long Id);
}
