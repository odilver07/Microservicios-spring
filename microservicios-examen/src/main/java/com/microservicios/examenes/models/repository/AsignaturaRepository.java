package com.microservicios.examenes.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.microservicios.commons.examenes.models.entity.Asignatura;

public interface AsignaturaRepository extends PagingAndSortingRepository<Asignatura, Long> {

}
