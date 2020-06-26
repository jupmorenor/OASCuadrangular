package com.juanpa.OAScuadrangular;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import com.juanpa.OAScuadrangular.Equipo;

public interface EquipoRepo extends CrudRepository<Equipo, Integer>{

  @Query("select * from Equipo where nombre like ?1")
  Equipo findByNombre(String nombre);
  
}