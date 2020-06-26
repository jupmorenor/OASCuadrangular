/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     25/06/2020 10:27:48 a.m.                     */
/*==============================================================*/

create database cuadrangular;

use cuadrangular;

drop table if exists EQUIPO;

drop table if exists PARTIDO;

/*==============================================================*/
/* Table: EQUIPO                                                */
/*==============================================================*/
create table EQUIPO
(
   ID                   smallint not null,
   NOMBRE               varchar(32) not null,
   primary key (ID)
);

/*==============================================================*/
/* Table: PARTIDO                                               */
/*==============================================================*/
create table PARTIDO
(
   ID                   smallint not null,
   EQUIPO1_ID           smallint not null,
   EQUIPO2_ID           smallint not null,
   GOLES_1              smallint,
   GOLES_2              smallint,
   primary key (ID)
);

alter table PARTIDO add constraint FK_REFERENCE_1 foreign key (EQUIPO1_ID)
      references EQUIPO (ID) on delete restrict on update restrict;

alter table PARTIDO add constraint FK_REFERENCE_2 foreign key (EQUIPO2_ID)
      references EQUIPO (ID) on delete restrict on update restrict;

