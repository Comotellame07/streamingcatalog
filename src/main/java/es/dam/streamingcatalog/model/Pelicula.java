package es.dam.streamingcatalog.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    private int anioLanzamiento;
    private String genero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estudio_id")
    private Estudio estudio;

    @OneToMany(
            mappedBy = "pelicula",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Critica> criticas = new ArrayList<>();

    public Pelicula() {}

    public Pelicula(String titulo, int anioLanzamiento, String genero, Estudio estudio) {
        this.titulo = titulo;
        this.anioLanzamiento = anioLanzamiento;
        this.genero = genero;
        this.estudio = estudio;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String t) { this.titulo = t; }
    public int getAnioLanzamiento() { return anioLanzamiento; }
    public void setAnioLanzamiento(int anio) { this.anioLanzamiento = anio; }
    public Estudio getEstudio() { return estudio; }
    public List<Critica> getCriticas() { return criticas; }

    @Override
    public String toString() {
        return "Pelicula{id=" + id + ", titulo='" + titulo + "', año=" + anioLanzamiento + "}";
    }
}
