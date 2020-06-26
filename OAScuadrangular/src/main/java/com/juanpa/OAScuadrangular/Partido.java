package com.juanpa.OAScuadrangular;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partido {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private Integer equipo1_id;

  private Integer equipo2_id;

  private Integer goles_1;

  private Integer goles_2;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getEquipo1() {
    return this.equipo1_id;
  }

  public void setEquipo1(Integer equipo1) {
    this.equipo1_id = equipo1;
  }

  public Integer getEquipo2() {
    return this.equipo2_id;
  }

  public void setEquipo2(Integer equipo2) {
    this.equipo2_id = equipo2;
  }

  public Integer getGoles1() {
    return this.goles_1;
  }

  public void setGoles1(Integer goles1) {
    this.goles_1 = goles1;
  }

  public Integer getGoles2() {
    return this.goles_2;
  }

  public void setGoles2(Integer goles2) {
    this.goles_2 = goles2;
  }
  
}