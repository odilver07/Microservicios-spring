package com.microservicios.app.cursos.services;

import java.util.List;

import com.microservicios.app.cursos.models.entity.Curso;
import com.microservicios.commons.alumnos.models.entity.Alumno;
import com.microservicios.commons.services.CommonService;

public interface CursoService extends CommonService<Curso> {
	public Curso findCursoByAlumno(Long id);
	
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long id);
	
	public List<Alumno> obtenerAlumnosPorCurso(List<Long> ids);
	
	public void eliminarCursoAlumndoPorId(Long id);
}
