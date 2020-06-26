package com.juanpa.OAScuadrangular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping(path="/equipo")
public class EquipoController {
  @Autowired
  private EquipoRepo equipoRepo;

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping(path="/cargar")
  public @ResponseBody Iterable<Equipo> obtenerEquipos() {
    return equipoRepo.findAll();
  }

  public Equipo obtenerUno(Integer id) {  
    return equipoRepo.findById(id).get();
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(path="/registrar")
  public @ResponseBody String registrarEquipo(@RequestParam(value="nombreq") String nombreEquipo) {
    String resp = "";
    long cupos = 4-equipoRepo.count();
    
    if (cupos > 0) {
      Equipo e = new Equipo();
      e.setNombre(nombreEquipo);
      equipoRepo.save(e);
      resp = "Equipo Registrado, quedan " + (cupos-1) + " cupos.";
    } else {
      resp = "No hay mas cupos";
    }

    return resp;
  }
  
}