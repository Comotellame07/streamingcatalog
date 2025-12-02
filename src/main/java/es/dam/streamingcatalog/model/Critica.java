package es.dam.streamingcatalog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "criticas")
public class Critica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int puntuacion;

    @Column(length = 500)
    private String comentario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;

    public Critica() {}

    public Critica(int puntuacion, String comentario, Pelicula pelicula) {
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.pelicula = pelicula;
    }

    public Long getId() { return id; }
    public int getPuntuacion() { return puntuacion; }
    public void setPuntuacion(int p) { this.puntuacion = p; }

    @Override
    public String toString() {
        return "Critica{id=" + id + ", puntuación=" + puntuacion + "}";
    }
}

