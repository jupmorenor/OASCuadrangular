package com.juanpa.OAScuadrangular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@RequestMapping(path="/partido")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartidoController {
  @Autowired

  private PartidoRepo partidoRepo;
  private EquipoRepo equipoRepo;

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(path="/registrar")
  public @ResponseBody String registrarPartido(
    @RequestParam(value="goles1") String goles1,
    @RequestParam(value="goles2") String goles2,
    @RequestParam(value="equipo1") String equipo1,
    @RequestParam(value="equipo2") String equipo2
  ) {
    Partido p = new Partido();
    Equipo eq1 = equipoRepo.findByNombre(equipo1);
    Equipo eq2 = equipoRepo.findByNombre(equipo2);

    p.setEquipo1(eq1.getId());
    p.setEquipo2(eq2.getId());
    p.setGoles1(new Integer(goles1));
    p.setGoles2(new Integer(goles2));

    partidoRepo.save(p);
    return "Marcador registrado con exito";
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping(path="/actualizar")
  public @ResponseBody String actualizarTabla() {
    HashMap<String, Integer> tabla = new HashMap<String, Integer>();
    Iterable<Partido> partidos = partidoRepo.findAll();
    partidos.forEach((partido) -> {
      Equipo eq = equipoRepo.findById(partido.getEquipo1()).get();
      Integer puntos = 0;
      if (partido.getGoles1() > partido.getGoles2()) {
        puntos += 3;
      } else if (partido.getGoles1() == partido.getGoles2()) {
        puntos += 1;
      }
      if (tabla.containsKey(eq.getNombre())) {
        puntos += tabla.get(eq.getNombre());
      }
      tabla.put(eq.getNombre(), puntos);
    });

    return tabla.toString();
  } 
}