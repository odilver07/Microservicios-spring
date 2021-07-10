package com.microservicios.app.respuestas.models.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.microservicios.app.respuestas.models.entity.Respuesta;

public interface RespuestaRepository extends MongoRepository<Respuesta, String> {
	
	@Query("{'alumnoId': ?0, 'preguntaId':{$in: ?1}}")
	public Iterable<Respuesta> findRespuestasByAlumnoByRespuestaIds(Long alumnoId,Iterable<Long> preguntasId);
	
	@Query("{'alumnoId': ?0}")
	public Iterable<Respuesta> findByAlumnoId(Long Id);
	
//		@Query("select r from Respuesta r join fetch r.pregunta p join fetch p.examen e where r.alumnoId=?1 and e.id=?2")
//		public Iterable<Respuesta> findRespuestasByAlumnoByExamen(Long alumnoId, Long examenId);
//		
//		@Query("select e.id from Respuesta r join r.pregunta p join p.examen e where r.alumnoId=?1 group by e.id")
//		public Iterable<Long> findExamenesIdsConRespuestasByAlumno(long alumnoId);
	
	@Query("{'alumnodId': ?0, 'pregunta.examen.id' : ?1}")
	public Iterable<Respuesta> findRespuestasByAlumnoByExamen(Long alumnoId, Long examenId);

	@Query(value = "{'alumnoId': ?0}", fields = "{'pregunta.examen.id': 1}")
	public Iterable<Respuesta> findExamenesIdsConRespuestasByAlumno(Long Id);
}
