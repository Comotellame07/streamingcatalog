package es.dam.streamingcatalog.model;

import jakarta.persistence.*;
import es.dam.streamingcatalog.model.Estudio;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudios")
public class Estudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @OneToMany(
            mappedBy = "estudio",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Pelicula> peliculas = new ArrayList<>();

    public Estudio() {}

    public Estudio(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Pelicula> getPeliculas() { return peliculas; }

    @Override
    public String toString() {
        return "Estudio{id=" + id + ", nombre='" + nombre + "'}";
    }
}
