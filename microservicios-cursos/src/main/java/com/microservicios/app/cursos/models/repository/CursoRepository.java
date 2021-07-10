package com.microservicios.app.cursos.models.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.microservicios.app.cursos.models.entity.Curso;

public interface CursoRepository  extends PagingAndSortingRepository<Curso, Long>{
	
	@Query("select c from Curso c join fetch c.cursoAlumnos a where a.alumnoId=?1")
	public Curso findCursoByAlumno(Long id);
	
//	Delete, put, insert
	@Modifying
	@Query("delete from CursoAlumno ca where ca.alumnoId=?1")
	public void eliminarCursoAlumndoPorId(Long id);
}
