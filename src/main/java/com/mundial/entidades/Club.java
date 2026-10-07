package com.mundial.entidades;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "club")
public class Club {

    @Id
    private String id;

    private String nombre;

    // Uno a uno (@OneToOne): un club tiene un entrenador.
    // En MongoDB solo se guarda el _id del entrenador dentro del documento del club.
    @DocumentReference
    private Entrenador entrenador;

    // Uno a muchos (@OneToMany): un club tiene una lista de jugadores.
    // Se guarda un arreglo con los _id de los jugadores.
    @DocumentReference
    private List<Jugador> jugadores = new ArrayList<>();

    // Muchos a uno (@ManyToOne): muchos clubes pertenecen a una asociacion.
    // Varios clubes guardan el mismo _id de asociacion.
    @DocumentReference
    private Asociacion asociacion;

    // Muchos a muchos (@ManyToMany): un club juega varias competiciones y
    // una competicion tiene varios clubes. Se guarda un arreglo de _id de competiciones.
    @DocumentReference
    private List<Competicion> competiciones = new ArrayList<>();

    public Club() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public List<Competicion> getCompeticiones() {
        return competiciones;
    }

    public void setCompeticiones(List<Competicion> competiciones) {
        this.competiciones = competiciones;
    }
}
