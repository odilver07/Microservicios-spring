package com.microservicios.app.cursos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.app.cursos.clients.AlumnoFeignClient;
import com.microservicios.app.cursos.clients.RespuestaFeignClient;
import com.microservicios.app.cursos.models.entity.Curso;
import com.microservicios.app.cursos.models.repository.CursoRepository;
import com.microservicios.commons.alumnos.models.entity.Alumno;
import com.microservicios.commons.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {
	
	@Autowired
	private RespuestaFeignClient client;
	
	@Autowired
	private AlumnoFeignClient clientAlumno;
	
	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumno(Long id) {
		return repository.findCursoByAlumno(id);
	}

	@Override
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long id) {
		return client.obtenerExamenesIdsConRespuestasAlumno(id);
	}

	@Override
	public List<Alumno> obtenerAlumnosPorCurso(List<Long> ids) {
		return clientAlumno.obtenerAlumnosPorCurso(ids);
	}

	@Override
	@Transactional
	public void eliminarCursoAlumndoPorId(Long id) {
		repository.eliminarCursoAlumndoPorId(id);
	}


}
