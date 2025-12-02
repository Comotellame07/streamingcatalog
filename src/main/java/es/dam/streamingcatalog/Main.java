package es.dam.streamingcatalog;

import es.dam.streamingcatalog.model.*;
import es.dam.streamingcatalog.repository.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Estudio.class)
                .addAnnotatedClass(Pelicula.class)
                .addAnnotatedClass(Critica.class)
                .buildSessionFactory();

        EstudioRepository estudioRepo = new EstudioRepository(factory);
        PeliculaRepository peliRepo = new PeliculaRepository(factory);
        CriticaRepository criticaRepo = new CriticaRepository(factory);

        System.out.println("\n=== TEST CRUD STREAMING CATALOG ===\n");

        // 1. Crear un Estudio

        System.out.println("\n=== 1. Crear un Estudio ===\n");
        Estudio est = new Estudio("Paramount");
        Long idEst = estudioRepo.crear(est);
        System.out.println("Estudio creado con el id: " + idEst);

        // 2. Crear dos Películas
        System.out.println("\n=== 2. Crear dos Películas ===\n");
        Pelicula p1 = new Pelicula("Interstellar", 2014, "Sci-Fi", est);
        Pelicula p2 = new Pelicula("Dune", 2021, "Sci-Fi", est);
        peliRepo.crear(p1);
        peliRepo.crear(p2);
        System.out.println("Películas creadas:\n" + p1 + "\n" + p2);

        // 3. Actualizar el año de una Película
        System.out.println("\n=== 3. Actualizar el año de una Película ===\n");
        System.out.println("Pelicula antes de actualizar: " + peliRepo.get(p1.getId()));;
        peliRepo.actualizarAnio(p1.getId(), 2015);
        Pelicula peliActualizada = peliRepo.get(p1.getId());
        System.out.println("Película actualizada: " + peliActualizada);

        // 4. Buscar películas posteriores a un año
        System.out.println("\n=== 4. Buscar películas posteriores a un año ===\n");
        List<Pelicula> posteriores = peliRepo.buscarPorAnioMayor(2018);
        posteriores.forEach(System.out::println);

        // 5. Eliminar Estudio y observar cascadas
        System.out.println("\n=== 5. Eliminar Estudio y observar cascadas ===\n");
        System.out.println("Estudios antes de eliminar: \n" + estudioRepo.listar());
        boolean eliminado = estudioRepo.eliminar(idEst);
        System.out.println("Estudio eliminado: " + eliminado);
        System.out.println("Estudios después de eliminar: \n" + estudioRepo.listar());

        factory.close();
    }
}
